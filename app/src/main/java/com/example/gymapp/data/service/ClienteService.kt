package com.example.gymapp.data.service

import com.example.gymapp.data.model.ClienteDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ClienteService {
    @GET("/api/clientes/{id}")
    suspend fun getClienteById(@Path("id") id: Int): Response<ClienteDTO>
}