package com.rushikesh.tasks.data.model

data class Task(
    val id: Long? = null,
    val title: String = "",
    val description: String = "",
    val isCompleted: Boolean = false,
    val createdAt: Long? = null
)
