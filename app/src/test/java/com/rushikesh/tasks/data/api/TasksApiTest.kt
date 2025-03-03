package com.rushikesh.tasks.data.api

import com.rushikesh.tasks.Helper
import com.rushikesh.tasks.data.repository.TaskRepository
import com.rushikesh.tasks.data.utils.NetworkResponse
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.mock
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TasksApiTest {
    lateinit var mockWebServer: MockWebServer
    lateinit var tasksApiService: TasksApiService


    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        tasksApiService = Retrofit.Builder().baseUrl(mockWebServer.url("/")).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(TasksApiService::class.java)
    }

    @Test
    fun testGetTasksApi() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)
        val result = tasksApiService.getAllTasks()
        mockWebServer.takeRequest()
        Assert.assertEquals(0, result.body()?.size)
    }
    @Test
    fun testGetTasks_returnTaskList() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(200)
        mockResponse.setBody(Helper.readFile("/response.json"))
        mockWebServer.enqueue(mockResponse)
        val result = tasksApiService.getAllTasks()
        mockWebServer.takeRequest()
        Assert.assertEquals(3, result.body()?.size)
    }

    @Test
    fun testGetTasks_returnError() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(404)
        mockResponse.setBody("Something went wrong")
        mockWebServer.enqueue(mockResponse)
        val result = tasksApiService.getAllTasks()
        mockWebServer.takeRequest()
        Assert.assertEquals(404, result.code())
    }
    @Test
    fun testRepositoryGetTasks_returnTaskList() = runTest{
        val mockResponse = MockResponse()
        mockResponse.setBody(Helper.readFile("/response.json"))
        mockResponse.setResponseCode(200)
        mockWebServer.enqueue(mockResponse)
        val tasksRepository = TaskRepository(tasksApiService)
        val response = tasksRepository.getAllTasks()
        mockWebServer.takeRequest()
        Assert.assertEquals(true, response is NetworkResponse.Success)
        Assert.assertEquals(3, response.data?.size)
    }

}