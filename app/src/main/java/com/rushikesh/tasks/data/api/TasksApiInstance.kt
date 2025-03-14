package com.rushikesh.tasks.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class TasksApiInstance @Inject constructor() {
    private val BASE_URL = "https://67b9b7b351192bd378de38b8.mockapi.io/"

    val tasksApi: TasksApiService by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(
                TasksApiService::class.java
            )
    }
}