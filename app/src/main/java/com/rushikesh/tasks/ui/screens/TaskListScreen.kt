package com.rushikesh.tasks.ui.screens

import android.util.Log.v
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rushikesh.tasks.data.model.Task
import com.rushikesh.tasks.data.utils.NetworkResponse
import com.rushikesh.tasks.ui.viewmodels.TaskListViewModel

@Composable
fun TaskListScreen(viewModel: TaskListViewModel = viewModel(), padding: PaddingValues) {
    val tasks = viewModel.tasks.collectAsState()
    when(tasks?.value) {
        is NetworkResponse.Success -> {
            if(tasks?.value?.data != null)
                TaskList(tasks.value.data!!, { task -> viewModel.deleteTask(task.id!!) }, padding)
        }
        is NetworkResponse.Error -> {}
        else -> {}
    }
}

@Composable
fun TaskList(taskList: List<Task>, clickListener: (Task) -> Unit, padding: PaddingValues) {
    LazyColumn(modifier = Modifier.padding(padding)) {
        items(taskList) { task ->
            TaskItem(task, clickListener)
        }
    }
}

@Composable
fun TaskItem(task: Task, clickListener: (Task) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(16.dp, 8.dp), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(task.isCompleted, onCheckedChange = {
            }, colors = CheckboxDefaults.colors(uncheckedColor = Color.Blue, checkedColor = Color.Gray))
            Text(task.title, modifier = Modifier.clickable {
                clickListener(task)
            })
        }
    }
}
