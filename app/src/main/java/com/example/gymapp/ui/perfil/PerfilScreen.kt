package com.example.gymapp.ui.perfil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gymapp.ui.colors.BlackPrimary
import com.example.gymapp.ui.colors.GrayBackground
import com.example.gymapp.ui.colors.YellowPrimary


@Composable
fun PerfilScreen(clienteId: Int) {
    val viewModel: PerfilViewModel = viewModel()
    val cliente by viewModel.cliente.observeAsState()
    val error by viewModel.error.observeAsState()

    LaunchedEffect(clienteId) {
        viewModel.cargarCliente(clienteId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBackground)
            .padding(16.dp)
    ) {
        Text(
            "Mi Perfil",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = BlackPrimary
        )

        Spacer(Modifier.height(16.dp))

        error?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
            Spacer(Modifier.height(16.dp))
        }

        cliente?.let { c ->
            // Tarjeta de resumen
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = YellowPrimary),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Usuario",
                        tint = BlackPrimary,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(c.nombre, style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold), color = BlackPrimary)
                    Text("DNI: ${c.dni}", style = MaterialTheme.typography.bodyMedium, color = BlackPrimary)
                }
            }

            Spacer(Modifier.height(16.dp))

            // Información de contacto
            InfoSection(title = "Contacto") {
                InfoRowWithIcon(icon = Icons.Default.Phone, label = "Teléfono", value = c.telefono ?: "No registrado")
                InfoRowWithIcon(icon = Icons.Default.Email, label = "Correo", value = c.correo ?: "No registrado")
                InfoRowWithIcon(icon = Icons.Default.LocationOn, label = "Dirección", value = c.direccion ?: "No registrada")
            }

            Spacer(Modifier.height(16.dp))

            // Información adicional
            InfoSection(title = "Detalles de Membresía") {
                InfoRowWithIcon(icon = Icons.Default.LocationOn, label = "Sede", value = c.sede?.nombre ?: "No asignada")
                InfoRowWithIcon(icon = Icons.Default.DateRange, label = "Fecha de pago", value = c.fechaPago ?: "No registrado")
                InfoRowWithIcon(icon = Icons.Default.Check, label = "Mensualidad", value = c.mensualidad?.toString() ?: "0.0")
                InfoRowWithIcon(icon = Icons.Default.Info, label = "Descripción", value = c.descripcion ?: "N/A")
            }
        }
    }
}

@Composable
fun InfoSection(title: String, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BlackPrimary.copy(alpha = 0.05f), shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Text(title, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = BlackPrimary)
        Spacer(Modifier.height(8.dp))
        content()
    }
}

@Composable
fun InfoRowWithIcon(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = label, tint = YellowPrimary, modifier = Modifier.size(24.dp))
        Spacer(Modifier.width(12.dp))
        Column {
            Text(label, style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold), color = BlackPrimary)
            Text(value, style = MaterialTheme.typography.bodyMedium, color = BlackPrimary)
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        Text(label, style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold), color = BlackPrimary)
        Text(value, style = MaterialTheme.typography.bodyMedium, color = BlackPrimary)
    }
}
