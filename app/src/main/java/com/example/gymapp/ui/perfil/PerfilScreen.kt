package com.example.gymapp.ui.perfil

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun PerfilScreen(clienteId: Int) {
    val viewModel: PerfilViewModel = viewModel()
    val cliente by viewModel.cliente.observeAsState()
    val error by viewModel.error.observeAsState()

    LaunchedEffect(clienteId) {
        if (clienteId > 0) { // 👈 Evita llamadas inválidas
            viewModel.cargarCliente(clienteId)
        }
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Mi información", style = MaterialTheme.typography.titleLarge)

        error?.let { Text(it, color = MaterialTheme.colorScheme.error) }

        cliente?.let {
            Spacer(Modifier.height(8.dp))
            Text("Nombre: ${it.nombre}")
            Text("DNI: ${it.dni}")
            Text("Teléfono: ${it.telefono ?: "No registrado"}")
            Text("Correo: ${it.correo ?: "No registrado"}")
            Text("Dirección: ${it.direccion ?: "No registrada"}")
            Text("Sede: ${it.sede?.nombre ?: "No asignada"}") // 👈 null safe
            Text("Fecha de pago: ${it.fechaPago ?: "No registrado"}")
            Text("Mensualidad: ${it.mensualidad ?: 0.0}")
            Text("Descripción: ${it.descripcion ?: "N/A"}")
        }
    }
}