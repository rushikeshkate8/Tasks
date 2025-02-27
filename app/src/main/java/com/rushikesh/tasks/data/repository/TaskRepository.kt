package com.rushikesh.tasks.data.repository

import com.rushikesh.tasks.data.api.TasksApiInstance
import com.rushikesh.tasks.data.model.Task
import com.rushikesh.tasks.data.utils.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskRepository {
    suspend fun getAllTasks(): NetworkResponse<List<Task>> = withContext(Dispatchers.IO) {
        val response = TasksApiInstance.tasksApi.getAllTasks()
        return@withContext if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) NetworkResponse.Success(responseBody)
            else {
                NetworkResponse.Error("Something went wrong: ${response.message()}")
            }
        } else {
            NetworkResponse.Error("Something went wrong: ${response.message()}")
        }
    }

    suspend fun deleteTask(id: Int) = withContext(Dispatchers.IO) {
        TasksApiInstance.tasksApi.deleteTask(id)
    }
}