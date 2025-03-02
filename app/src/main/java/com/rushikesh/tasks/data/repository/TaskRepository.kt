package com.rushikesh.tasks.data.repository

import com.rushikesh.tasks.data.api.TasksApiInstance
import com.rushikesh.tasks.data.api.TasksApiService
import com.rushikesh.tasks.data.model.Task
import com.rushikesh.tasks.data.utils.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskRepository(val tasksApiService: TasksApiService) {
    suspend fun getAllTasks(): NetworkResponse<List<Task>> = withContext(Dispatchers.IO) {
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

    suspend fun deleteTask(id: Int) = withContext(Dispatchers.IO) {
        TasksApiInstance.tasksApi.deleteTask(id)
    }
}