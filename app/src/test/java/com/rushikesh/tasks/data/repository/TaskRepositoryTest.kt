package com.rushikesh.tasks.data.repository

import com.rushikesh.tasks.MainCoroutineRule
import com.rushikesh.tasks.data.api.TasksApiInstance
import com.rushikesh.tasks.data.api.TasksApiService
import com.rushikesh.tasks.data.model.Task
import com.rushikesh.tasks.data.utils.NetworkResponse
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class TaskRepositoryTest {
    @get:Rule
    private val mainCoroutineRule: MainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var tasksApi: TasksApiService

    @Mock
    lateinit var TasksApiInstance: TasksApiInstance

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testGetAllTasks() = runTest {
        Mockito.`when`(TasksApiInstance.tasksApi).thenReturn(tasksApi)
        Mockito.`when`(tasksApi.getAllTasks()).thenReturn(Response.success(emptyList()))
        val response = TaskRepository().getAllTasks()
        Assert.assertEquals("", response.data.toString())
    }
}