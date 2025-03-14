package com.rushikesh.tasks.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rushikesh.tasks.ui.screens.AddNewTaskScreen
import com.rushikesh.tasks.ui.screens.TaskListScreen
import com.rushikesh.tasks.ui.viewmodels.TaskViewModel

@Composable
fun AppNavHost(viewModel: TaskViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoutes.TaskList.route) {
        composable(NavRoutes.TaskList.route) {
            TaskListScreen(navController, viewModel)
        }
        composable(NavRoutes.AddTask.route) {
            AddNewTaskScreen(navController, viewModel)
        }
    }

}