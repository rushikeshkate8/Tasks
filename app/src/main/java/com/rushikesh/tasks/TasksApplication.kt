package com.rushikesh.tasks

import android.app.Application
import com.rushikesh.tasks.data.api.TasksApiInstance
import com.rushikesh.tasks.data.repository.TaskRepository
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class TasksApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}