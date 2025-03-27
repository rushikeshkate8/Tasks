package com.rushikesh.tasks.data.repository

import com.rushikesh.tasks.data.model.Task
import com.rushikesh.tasks.data.utils.NetworkResponse
import retrofit2.Response

class RoomRepository: TaskRepository {
    override suspend fun getAllTasks(): NetworkResponse<List<Task>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(id: Long): Response<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun addTask(task: Task): NetworkResponse<Task?> {
        TODO("Not yet implemented")
    }
}