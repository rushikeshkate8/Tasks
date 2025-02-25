package com.rushikesh.tasks.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rushikesh.tasks.data.model.Task
import com.rushikesh.tasks.ui.viewmodels.TaskListViewModel

@Composable
fun TaskListScreen(viewModel: TaskListViewModel = viewModel(), padding: PaddingValues) {
    val tasks = viewModel.tasks.collectAsState()
    LazyColumn(modifier = Modifier.padding(padding)) {
        items(tasks.value) { task ->
            TaskItem(task, viewModel)
        }
    }
}

@Composable
fun TaskItem(task: Task, viewModel: TaskListViewModel) {
    Row {
        Text(task.title, modifier = Modifier.clickable {
            viewModel.deleteTask(task.id!!)
        })
        Checkbox(task.isCompleted, onCheckedChange =  {})
    }
}
