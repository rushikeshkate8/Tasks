package com.rushikesh.tasks.ui.viewmodels

import com.rushikesh.tasks.MainCoroutineRule
import com.rushikesh.tasks.data.repository.TaskRepository
import com.rushikesh.tasks.data.utils.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TaskListViewModelTest {
    private val testDispatcher = StandardTestDispatcher()

    @Mock
    lateinit var tasksRepository: TaskRepository

    @Test
    fun test_GetAllTasks() = runTest {
        Mockito.`when`(tasksRepository.getAllTasks()).thenReturn(NetworkResponse.Success(emptyList()))
        val sut = TaskListViewModel(tasksRepository)
        sut.fetchTasks()
        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.tasks.value
        Assert.assertEquals(0, result.data?.size)
    }
    @Test
    fun test_GetAllTasks_expectedError() = runTest {
        Mockito.`when`(tasksRepository.getAllTasks()).thenReturn(NetworkResponse.Error("Something went wrong"))
        val sut = TaskListViewModel(tasksRepository)
        sut.fetchTasks()
        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.tasks.value
        Assert.assertEquals(true, result is NetworkResponse.Error)
        Assert.assertEquals("Something went wrong", result.message)
    }
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }
    @After
    fun cleanUp() {
        Dispatchers.resetMain()
    }
}