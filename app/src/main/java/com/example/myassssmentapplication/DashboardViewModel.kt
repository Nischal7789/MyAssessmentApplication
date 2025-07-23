package com.example.myassssmentapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myassssmentapplication.model.DashboardResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

class DashboardViewModel(
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main // use Unconfined in test
) : ViewModel() {

    private val _dashboardData = MutableLiveData<Result<DashboardResponse>>()
    val dashboardData: LiveData<Result<DashboardResponse>> = _dashboardData

    fun fetchDashboard(keypass: String) {
        CoroutineScope(dispatcher).launch {
            try {
                val response: Response<DashboardResponse> = apiService.getDashboard(keypass)
                if (response.isSuccessful) {
                    _dashboardData.postValue(Result.success(response.body()!!))
                } else {
                    _dashboardData.postValue(Result.failure(Exception("Dashboard fetch failed")))
                }
            } catch (e: Exception) {
                _dashboardData.postValue(Result.failure(e))
            }
        }
    }
}

