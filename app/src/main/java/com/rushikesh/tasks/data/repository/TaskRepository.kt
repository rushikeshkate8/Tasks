package com.rushikesh.tasks.data.repository

import com.rushikesh.tasks.data.api.TasksApiInstance
import com.rushikesh.tasks.data.api.TasksApiService
import com.rushikesh.tasks.data.model.Task
import com.rushikesh.tasks.data.utils.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskRepository @Inject constructor(val tasksApiInstance: TasksApiInstance) {

    suspend fun getAllTasks(): NetworkResponse<List<Task>> = withContext(Dispatchers.IO) {
        val response = tasksApiInstance.tasksApi.getAllTasks()
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

    suspend fun deleteTask(id: Long) = withContext(Dispatchers.IO) {
        val response = tasksApiInstance.tasksApi.deleteTask(id)
    }

    suspend fun addTask(task: Task) = withContext(Dispatchers.IO) {
        val response = tasksApiInstance.tasksApi.addTask(task)
        return@withContext if(response.isSuccessful) {
            val responseBody = response.body()
            NetworkResponse.Success(responseBody)
        } else {
            NetworkResponse.Error(response.errorBody()?.string())
        }
    }
}