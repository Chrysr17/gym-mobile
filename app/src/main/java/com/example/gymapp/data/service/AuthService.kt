package com.example.gymapp.data.service

import com.example.gymapp.data.model.ClienteDTO
import com.example.gymapp.data.model.LoginRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {
    @POST("auth/login")
    suspend fun login(
        @Query("nombreUsuario") nombreUsuario: String,
        @Query("password") password: String
    ): Response<ClienteDTO>
}