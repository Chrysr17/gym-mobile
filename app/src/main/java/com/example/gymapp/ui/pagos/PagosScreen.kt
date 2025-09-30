package com.example.gymapp.ui.pagos

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*


@Composable
fun PagosScreen(idCliente: Int) {
    val viewModel: PagosViewModel = viewModel()
    val pagos by viewModel.pagos.observeAsState(emptyList())
    val error by viewModel.error.observeAsState()

    LaunchedEffect(idCliente) {
        viewModel.cargarPagos(idCliente)
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Historial de pagos", style = MaterialTheme.typography.titleLarge)

        error?.let { Text(it, color = MaterialTheme.colorScheme.error) }

        LazyColumn {
            items(pagos) { pago ->
                Card(Modifier.fillMaxWidth().padding(8.dp)) {
                    Column(Modifier.padding(16.dp)) {
                        Text("Fecha: ${pago.fecha}")
                        Text("Monto: ${pago.monto}")
                        Text("Estado: ${pago.estado}")
                    }
                }
            }
        }
    }
}
