package com.rushikesh.tasks.di

import com.rushikesh.tasks.data.api.TasksApiService
import com.rushikesh.tasks.data.repository.RetrofitRepository
import com.rushikesh.tasks.data.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TaskModule {
    private val BASE_URL = "https://67b9b7b351192bd378de38b8.mockapi.io/"

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideTaskApiService(retrofit: Retrofit): TasksApiService {
        return retrofit.create(TasksApiService::class.java)
    }
}