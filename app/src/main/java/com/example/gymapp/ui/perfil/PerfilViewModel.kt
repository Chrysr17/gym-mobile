package com.example.gymapp.ui.perfil

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymapp.data.api.RetrofitClient
import com.example.gymapp.data.model.ClienteDTO
import com.example.gymapp.data.service.ClienteService
import kotlinx.coroutines.launch

class PerfilViewModel : ViewModel(){
    val cliente = MutableLiveData<ClienteDTO?>()
    val error = MutableLiveData<String?>()

    fun cargarCliente(clienteId: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.retrofit
                    .create(ClienteService::class.java)
                    .getClienteById(clienteId)

                if (response.isSuccessful) {
                    cliente.postValue(response.body())
                } else {
                    error.postValue("No se encontró información del cliente")
                }
            } catch (e: Exception) {
                error.postValue("Error: ${e.message}")
            }
        }
    }
}