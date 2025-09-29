package com.example.gymapp.data.api
import com.example.gymapp.data.service.AuthService
import com.example.gymapp.data.service.PagoService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://10.0.2.2:9012/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val authService: AuthService by lazy {
        retrofit.create(AuthService::class.java)
    }

    val pagoService: PagoService by lazy {
        retrofit.create(PagoService::class.java)
    }
}