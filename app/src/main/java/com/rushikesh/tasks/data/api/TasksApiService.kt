package com.rushikesh.tasks.data.api

import com.rushikesh.tasks.data.model.Task
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface TasksApiService {
    @GET("tasks")
    suspend fun getAllTasks(): List<Task>
    @DELETE("tasks/{id}")
    suspend fun deleteTask(@Path("id") id: Int): Response<Unit>
}