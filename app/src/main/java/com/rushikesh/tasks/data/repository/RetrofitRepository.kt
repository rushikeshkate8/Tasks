package com.rushikesh.tasks.data.repository

import android.content.Context
import android.widget.Toast
import com.rushikesh.tasks.data.api.TasksApiService
import com.rushikesh.tasks.data.model.Task
import com.rushikesh.tasks.data.utils.NetworkResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.contracts.contract

class RetrofitRepository @Inject constructor(val tasksApiService: TasksApiService) : TaskRepository {
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