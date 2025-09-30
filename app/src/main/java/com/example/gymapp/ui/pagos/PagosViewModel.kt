package com.example.gymapp.ui.pagos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymapp.data.api.RetrofitClient
import com.example.gymapp.data.model.PagoDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class PagosViewModel : ViewModel(){
    val pagos = MutableLiveData<List<PagoDTO>>()
    val error = MutableLiveData<String?>()

    fun cargarPagos(clienteId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.pagoService.listarPorCliente(clienteId).awaitResponse()
                if (response.isSuccessful) {
                    pagos.postValue(response.body())
                } else {
                    error.postValue("No se pudo obtener pagos")
                }
            } catch (e: Exception) {
                error.postValue("Error: ${e.message}")
            }
        }
    }
}