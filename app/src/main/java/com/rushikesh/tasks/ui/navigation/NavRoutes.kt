package com.rushikesh.tasks.ui.navigation

sealed class NavRoutes(val route: String) {
    object TaskList: NavRoutes("task_list")
    object AddTask: NavRoutes("add_task")
}
