package com.rushikesh.tasks.ui.screens

import android.R.attr.text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rushikesh.tasks.ui.viewmodels.TaskListViewModel

@Composable
fun TaskListScreen(viewModel: TaskListViewModel = viewModel()) {
    val tasks = viewModel.tasks.collectAsState()

    LazyColumn {
        items(tasks.value) { task ->
            Text(task.title, modifier = Modifier.clickable {
                viewModel.deleteTask(task.id!!)
            })
        }
    }
}