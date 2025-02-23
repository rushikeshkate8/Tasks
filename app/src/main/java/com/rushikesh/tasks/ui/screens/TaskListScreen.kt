package com.rushikesh.tasks.ui.screens

import android.R.attr.text
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rushikesh.tasks.ui.viewmodels.TaskListViewModel

@Composable
fun TaskListScreen(viewModel: TaskListViewModel = viewModel()) {
    val tasks = viewModel.tasks.collectAsState()

    LaunchedEffect(Unit)  { viewModel.fetchTasks() }
    Column { Text(text = tasks.value.toString()) }

}