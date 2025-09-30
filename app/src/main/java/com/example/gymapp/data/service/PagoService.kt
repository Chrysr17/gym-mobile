package com.example.gymapp.data.service

import com.example.gymapp.data.model.PagoDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PagoService {
    @GET("/api/pagos/cliente/{id}")
    fun listarPorCliente(@Path("id") id: Int): Call<List<PagoDTO>>
}