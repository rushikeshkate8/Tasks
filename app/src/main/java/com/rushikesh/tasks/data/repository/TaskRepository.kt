package com.rushikesh.tasks.data.repository

import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.LiveData
import com.rushikesh.tasks.data.api.TasksApiInstance
import com.rushikesh.tasks.data.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskRepository {
    suspend fun getTasks(): List<Task> = withContext(Dispatchers.IO) {
        TasksApiInstance.tasksApi.getAllTasks()
    }
    suspend fun deleteTask(id: Int) = withContext(Dispatchers.IO) {
        TasksApiInstance.tasksApi.deleteTask(id)
    }
}