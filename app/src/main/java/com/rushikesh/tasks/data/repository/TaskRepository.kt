package com.rushikesh.tasks.data.repository

import com.rushikesh.tasks.data.api.TasksApiInstance
import com.rushikesh.tasks.data.api.TasksApiService
import com.rushikesh.tasks.data.model.Task
import com.rushikesh.tasks.data.utils.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

interface TaskRepository {

    suspend fun getAllTasks(): NetworkResponse<List<Task>>

    suspend fun deleteTask(id: Long): Response<Unit>

    suspend fun addTask(task: Task): NetworkResponse<Task?>
}