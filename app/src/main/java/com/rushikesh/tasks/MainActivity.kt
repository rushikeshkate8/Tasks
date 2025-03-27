package com.rushikesh.tasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.rushikesh.tasks.ui.navigation.AppNavHost
import com.rushikesh.tasks.ui.theme.TasksTheme
import com.rushikesh.tasks.ui.viewmodels.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

     lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        taskViewModel  = ViewModelProvider(this).get(TaskViewModel::class.java)
        setContent {
            TasksTheme {
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
