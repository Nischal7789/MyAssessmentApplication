package com.example.myassssmentapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myassssmentapplication.model.DashboardResponse
import com.example.myassssmentapplication.model.Entity
import com.example.myassssmentapplication.util.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.*
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()
    private val mockApiService = mock<ApiService>()
    private val viewModel = DashboardViewModel(mockApiService, testDispatcher)

    @Test
    fun `fetchDashboard success returns data`() = runTest {
        val entityList = listOf(Entity("prop1", "prop2", "description"))
        val mockResponse = DashboardResponse(entityList, entityList.size)

        whenever(mockApiService.getDashboard("validKey"))
            .thenReturn(Response.success(mockResponse))

        viewModel.fetchDashboard("validKey")

        val result = viewModel.dashboardData.getOrAwaitValue()
        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrNull()?.entityTotal)
    }

    @Test
    fun `fetchDashboard failure returns error`() = runTest {
        val errorResponse = Response.error<DashboardResponse>(
            500,
            ResponseBody.create("application/json".toMediaTypeOrNull(), "Internal Server Error")
        )

        whenever(mockApiService.getDashboard("invalidKey"))
            .thenReturn(errorResponse)

        viewModel.fetchDashboard("invalidKey")

        val result = viewModel.dashboardData.getOrAwaitValue()
        assertTrue(result.isFailure)
    }
}

