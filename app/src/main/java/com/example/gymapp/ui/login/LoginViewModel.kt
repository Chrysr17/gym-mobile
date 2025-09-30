package com.example.gymapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gymapp.data.api.RetrofitClient
import com.example.gymapp.data.model.ClienteDTO
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var cliente = MutableLiveData<ClienteDTO?>()
    val error = MutableLiveData<String?>()
    fun login(nombreUsuario: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.authService.login(nombreUsuario, password)
                if (response.isSuccessful) {
                    cliente.postValue(response.body()) // devuelve ClienteDTO
                } else {
                    error.postValue("Credenciales incorrectas")
                }
            } catch (e: Exception) {
                error.postValue("Error: ${e.message}")
            }
        }
    }

}