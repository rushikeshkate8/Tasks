package com.rushikesh.tasks.ui.screens

import android.R.attr.label
import android.R.attr.singleLine
import android.R.attr.text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.I
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rushikesh.tasks.data.model.Task
import com.rushikesh.tasks.ui.viewmodels.TaskViewModel
import java.time.Instant

@Composable
fun AddNewTaskScreen(navController: NavController, taskViewModel: TaskViewModel = viewModel()) {
    var title = remember { mutableStateOf("") }
    var description = remember { mutableStateOf("") }
    Column {
        Text("Add New Task", style = MaterialTheme.typography.headlineMedium)
        OutlinedTextField(
            value = title.value,
            onValueChange = { title.value = it },
            label = { Text("Title") },
            placeholder = { Text("Type your title here") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        OutlinedTextField(
            value = description.value,
            onValueChange = { description.value = it },
            label = { Text("Description") },
            placeholder = { Text("Type tasks description here") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val currentTimeInMilliSec = Instant.now().epochSecond
                val task = Task(null, title.value, description.value, false, currentTimeInMilliSec)
                taskViewModel.addTask(task)
                navController.navigateUp()
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text("Add Task")
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun PreviewAddNewTaskScreen() {
    AddNewTaskScreen(rememberNavController())
}
