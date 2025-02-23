package com.rushikesh.tasks.data.api

import com.rushikesh.tasks.data.model.Task
import retrofit2.http.GET

interface TasksApiService {
    @GET("tasks")
    suspend fun getAllTasks(): List<Task>
}