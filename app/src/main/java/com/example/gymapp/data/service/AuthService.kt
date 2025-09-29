package com.example.gymapp.data.service

import com.example.gymapp.data.model.ClienteDTO
import com.example.gymapp.data.model.LoginRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<ClienteDTO>
}