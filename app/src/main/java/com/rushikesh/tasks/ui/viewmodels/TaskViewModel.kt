package com.rushikesh.tasks.ui.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rushikesh.tasks.data.model.Task
import com.rushikesh.tasks.data.repository.RetrofitRepository
import com.rushikesh.tasks.data.repository.TaskRepository
import com.rushikesh.tasks.data.utils.NetworkResponse
import com.rushikesh.tasks.di.RetrofitQualifier
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(@RetrofitQualifier val repository: TaskRepository): ViewModel() {
   // private val repository = TaskRepository()
    private val _tasks = MutableStateFlow<NetworkResponse<List<Task>>>(NetworkResponse.Loading())
    val tasks: StateFlow<NetworkResponse<List<Task>>> = _tasks.asStateFlow()

    private val TAG = TaskViewModel::class.java.simpleName

    init {
        fetchTasks()
    }

    fun fetchTasks() {
        viewModelScope.launch {
            try {
                _tasks.value = repository.getAllTasks()
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching tasks: ${e.message}")
            }
        }
    }
    fun deleteTask(task: Task) {
        viewModelScope.launch {
            try {
                val response = repository.deleteTask(task.id!!)

                if(response.isSuccessful) {
                    _tasks.value = NetworkResponse.Success(_tasks.value.data!!.minus(task))
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error while deleting the task: ${e.message}")
            }
        }
    }
    fun addTask(task: Task) {
        viewModelScope.launch {
            try {
                _tasks.value = NetworkResponse.Success(_tasks.value.data?.plus(repository.addTask(task).data)) as NetworkResponse<List<Task>>
            } catch (e: Exception) {
                Log.e(TAG, "Error while adding task: ${e.message}")
            }
        }
    }
}