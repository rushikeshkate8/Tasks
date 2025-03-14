package com.rushikesh.tasks.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rushikesh.tasks.data.model.Task
import com.rushikesh.tasks.data.repository.TaskRepository
import com.rushikesh.tasks.data.utils.NetworkResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository): ViewModel() {
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
    fun deleteTask(id: Long) {
        viewModelScope.launch {
            try {
                repository.deleteTask(id)
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