package com.rushikesh.tasks.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rushikesh.tasks.data.model.Task
import com.rushikesh.tasks.data.utils.NetworkResponse
import com.rushikesh.tasks.ui.navigation.NavRoutes
import com.rushikesh.tasks.ui.viewmodels.TaskViewModel

@Composable
fun TaskListScreen(navController: NavController, viewModel: TaskViewModel = viewModel()) {
    Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
        FloatingActionButton(
            onClick = { navController.navigate(NavRoutes.AddTask.route) }
            ) { Icon(imageVector = Icons.Default.Add, contentDescription = "Add Task")}
    }, floatingActionButtonPosition = FabPosition.EndOverlay) { contentPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)) {
            TaskListScreenContent(viewModel)
        }
    }
}

@Composable
fun TaskListScreenContent(viewModel: TaskViewModel) {
    val tasks = viewModel.tasks.collectAsState()
    when (tasks?.value) {
        is NetworkResponse.Success -> {
            if (tasks?.value?.data != null)
                TaskList(tasks.value.data!!, { task -> viewModel.deleteTask(task) })
        }

        is NetworkResponse.Error -> {
            Error(error = tasks.value.message ?: "")
        }

        else -> {
            EmptyTaskList()
        }
    }

}

@Composable
fun TaskList(taskList: List<Task>, clickListener: (Task) -> Unit) {
    LazyColumn {
        itemsIndexed(items = taskList, key = {_, it -> it.id ?: it }) { index, task ->
            TaskItem(task, clickListener)
        }
    }
}

@Composable
fun TaskItem(task: Task, clickListener: (Task) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                task.isCompleted,
                onCheckedChange = {
                },
                colors = CheckboxDefaults.colors(
                    uncheckedColor = Color.Blue,
                    checkedColor = Color.Gray
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    task.title,
                    style = MaterialTheme.typography.titleMedium)
                Text(task.description, style = MaterialTheme.typography.bodyMedium)
            }
            IconButton(onClick = {clickListener(task)}) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete a Task"
                )
            }
        }
    }
}

@Composable
fun EmptyTaskList() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("No tasks to show", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun Error(error: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("An error while fetching tasks \n$error", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
@Preview(showSystemUi = true)
fun PreviewTaskListScreen() {
    TaskListScreen(rememberNavController())
}


