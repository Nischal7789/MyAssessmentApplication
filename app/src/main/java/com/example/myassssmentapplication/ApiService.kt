package com.example.myassssmentapplication

import com.example.myassssmentapplication.model.LoginRequest
import com.example.myassssmentapplication.model.LoginResponse
import com.example.myassssmentapplication.model.DashboardResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("{location}/auth") // 👈 Allows footscray, sydney, or ort
    suspend fun login(
        @Path("location") location: String,
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @GET("dashboard/{keypass}")
    suspend fun getDashboard(
        @Path("keypass") keypass: String
    ): Response<DashboardResponse>
}

