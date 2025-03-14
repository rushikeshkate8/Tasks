package com.rushikesh.tasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.rushikesh.tasks.data.repository.TaskRepository
import com.rushikesh.tasks.ui.navigation.AppNavHost
import com.rushikesh.tasks.ui.theme.TasksTheme
import com.rushikesh.tasks.ui.viewmodels.TaskViewModel
import com.rushikesh.tasks.ui.viewmodels.TaskViewModelFactory
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
                val taskViewModel =
                    ViewModelProvider(this, TaskViewModelFactory(tasksRepository)).get(
                        TaskViewModel::class.java
                    )
                //TaskListScreen(taskListViewModel, padding = innerPadding)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    AppNavHost(taskViewModel)
                }

            }
        }
    }
}
