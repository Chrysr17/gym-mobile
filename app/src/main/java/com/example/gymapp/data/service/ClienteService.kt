package com.example.gymapp.data.service

import com.example.gymapp.data.model.ClienteDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ClienteService {
    @GET("cliente/{id}")
    fun getClienteById(@Path("id") id: Int): Call<ClienteDTO>
}