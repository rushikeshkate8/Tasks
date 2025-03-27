package com.rushikesh.tasks.data.repository

import android.util.Log
import com.rushikesh.tasks.data.api.TasksApiService
import com.rushikesh.tasks.data.model.Task
import com.rushikesh.tasks.data.utils.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RetrofitRepository @Inject constructor(val tasksApiService: TasksApiService) : TaskRepository {
    private val TAG  = RetrofitRepository::class.simpleName

    override suspend fun getAllTasks(): NetworkResponse<List<Task>> = withContext(Dispatchers.IO) {
        val response = tasksApiService.getAllTasks()
        return@withContext if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) NetworkResponse.Success(responseBody)
            else {
                NetworkResponse.Error("${response.errorBody()?.string()}")
            }
        } else {
            NetworkResponse.Error("${response.errorBody()?.string()}")
        }
    }

    override suspend fun deleteTask(id: Long) = withContext(Dispatchers.IO) {
        val response = tasksApiService.deleteTask(id)
        Log.d(TAG, response.message())
        return@withContext response
    }

    override suspend fun addTask(task: Task) = withContext(Dispatchers.IO) {
        val response = tasksApiService.addTask(task)
        return@withContext if(response.isSuccessful) {
            val responseBody = response.body()
            NetworkResponse.Success(responseBody)
        } else {
            NetworkResponse.Error(response.errorBody()?.string())
        }
    }
}