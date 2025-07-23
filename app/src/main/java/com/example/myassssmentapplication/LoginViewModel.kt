package com.example.myassssmentapplication

import androidx.lifecycle.*
import com.example.myassssmentapplication.model.LoginRequest
import com.example.myassssmentapplication.model.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _loginResult = MutableLiveData<Result<String>>() // Holds keypass or error
    val loginResult: LiveData<Result<String>> = _loginResult

    fun login(username: String, password: String, location: String) {
        viewModelScope.launch {
            try {
                val response: Response<LoginResponse> =
                    apiService.login(location, LoginRequest(username, password))

                if (response.isSuccessful) {
                    val key = response.body()?.keypass.orEmpty()
                    _loginResult.value = Result.success(key)
                } else {
                    _loginResult.value = Result.failure(Exception("Login failed: ${response.code()}"))
                }
            } catch (e: Exception) {
                _loginResult.value = Result.failure(e)
            }
        }
    }
}

