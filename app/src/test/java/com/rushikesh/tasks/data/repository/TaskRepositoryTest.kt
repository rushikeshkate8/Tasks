package com.rushikesh.tasks.data.repository

import com.rushikesh.tasks.data.api.TasksApiService
import com.rushikesh.tasks.data.model.Task
import com.rushikesh.tasks.data.utils.NetworkResponse
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class TaskRepositoryTest {
    @Mock
    lateinit var tasksApi: TasksApiService

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testGetAllTasks_expectedEmptyTaskList() = runTest {
        Mockito.`when`(tasksApi.getAllTasks()).thenReturn(Response.success(emptyList()))
        val response = TaskRepository().getAllTasks()
        Assert.assertEquals(response.message, true, response is NetworkResponse.Success)
        Assert.assertEquals(response.data.toString(),0, response.data?.size)
    }
    @Test
    fun testGetAllTasks_expectedTaskList() = runTest {
        val taskList = listOf(Task(1, "Task 1", "Do it", false, 12345), Task(2, "Task 2", "Important", true, 45678))
        Mockito.`when`(tasksApi.getAllTasks()).thenReturn(Response.success(taskList))
        val response = TaskRepository().getAllTasks()
        Assert.assertEquals(true, response is NetworkResponse.Success)
        Assert.assertEquals(2, response.data?.size)
        Assert.assertEquals("Task 1", response?.data?.get(0)?.title)
    }

    @Test
    fun testGetAllTasks_expectedError() = runTest {
        Mockito.`when`(tasksApi.getAllTasks()).thenReturn(Response.error(503, "Internet not available".toResponseBody()))
        val response = TaskRepository().getAllTasks()
        Assert.assertEquals(true, response is NetworkResponse.Error)
        Assert.assertEquals("Internet not available", response.message)
    }

}