package com.rushikesh.tasks

import android.app.Application
import com.rushikesh.tasks.data.api.TasksApiInstance
import com.rushikesh.tasks.data.repository.TaskRepository

class TasksApplication : Application() {
    lateinit var tasksRepository: TaskRepository
    override fun onCreate() {
        super.onCreate()
        tasksRepository = TaskRepository(TasksApiInstance.tasksApi)
    }
}