package com.rushikesh.tasks.data.api

import com.rushikesh.tasks.data.model.Task
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TasksApiService {
    @GET("tasks")
    suspend fun getAllTasks(): Response<List<Task>>

    @DELETE("tasks/{id}")
    suspend fun deleteTask(@Path("id") id: Int): Response<Unit>

    @POST("tasks")
    suspend fun addTask(@Body task: Task): Response<Task>
}