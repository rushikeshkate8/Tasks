package com.rushikesh.tasks

import android.R.attr.padding
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.rushikesh.tasks.data.repository.TaskRepository
import com.rushikesh.tasks.ui.screens.TaskListScreen
import com.rushikesh.tasks.ui.theme.TasksTheme
import com.rushikesh.tasks.ui.viewmodels.TaskListViewModel
import com.rushikesh.tasks.ui.viewmodels.TasksListViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var tasksRepository: TaskRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            TasksTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val taskListViewModel = ViewModelProvider(this, TasksListViewModelFactory(tasksRepository)).get(
                        TaskListViewModel::class.java)
                    TaskListScreen(taskListViewModel, padding = innerPadding)
                }
            }
        }
    }
}
