package com.rushikesh.tasks.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rushikesh.tasks.data.repository.TaskRepository

class TasksListViewModelFactory(private val tasksRepository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TaskListViewModel(tasksRepository) as T
    }
}