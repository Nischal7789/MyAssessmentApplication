package com.example.myassssmentapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.myassssmentapplication.model.LoginRequest
import com.example.myassssmentapplication.model.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import okhttp3.ResponseBody
import org.junit.*
import org.junit.Assert.*
import org.mockito.kotlin.*
import retrofit2.Response
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: LoginViewModel
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        apiService = mock()
        viewModel = LoginViewModel(apiService)
    }

    @Test
    fun `login success returns keypass`() = runTest {
        val expectedKey = "topic_flight"
        whenever(apiService.login("sydney", LoginRequest("Tanvir", "s8136414")))
            .thenReturn(Response.success(LoginResponse(expectedKey)))

        viewModel.login("Tanvir", "s8136414", "sydney")

        val result = viewModel.loginResult.getOrAwaitValue()
        assertTrue(result.isSuccess)
        assertEquals(expectedKey, result.getOrNull())
    }

    @Test
    fun `login failure returns error`() = runTest {
        whenever(apiService.login(any(), any()))
            .thenReturn(Response.error(401, ResponseBody.create(null, "")))

        viewModel.login("Tanvir", "wrongpass", "sydney")

        val result = viewModel.loginResult.getOrAwaitValue()
        assertTrue(result.isFailure)
    }

    // 👇 LiveData utility for testing
    private fun <T> LiveData<T>.getOrAwaitValue(): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(t: T) {
                data = t
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }

        observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)
        return data ?: throw IllegalStateException("LiveData value was null")
    }
}
