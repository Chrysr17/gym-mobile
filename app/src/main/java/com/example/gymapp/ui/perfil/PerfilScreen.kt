package com.example.gymapp.ui.perfil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
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

    // Hacer scroll en caso de que la información sea larga
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBackground)
            .verticalScroll(rememberScrollState())
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
            // Card de resumen con avatar
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = YellowPrimary),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Avatar circular
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(BlackPrimary.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Usuario",
                            tint = BlackPrimary,
                            modifier = Modifier.size(40.dp)
                        )
                    }

                    Spacer(Modifier.height(12.dp))
                    Text(
                        c.nombre,
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                        color = BlackPrimary
                    )
                    Text("DNI: ${c.dni}", style = MaterialTheme.typography.bodyMedium, color = BlackPrimary)
                }
            }

            Spacer(Modifier.height(16.dp))

            // Sección Contacto
            InfoSection(title = "Contacto") {
                InfoRowWithIcon(Icons.Default.Phone, "Teléfono", c.telefono ?: "No registrado")
                InfoRowWithIcon(Icons.Default.Email, "Correo", c.correo ?: "No registrado")
                InfoRowWithIcon(Icons.Default.Home, "Dirección", c.direccion ?: "No registrada")
            }

            Spacer(Modifier.height(16.dp))

            // Sección Detalles de membresía
            InfoSection(title = "Detalles de Membresía") {
                InfoRowWithIcon(Icons.Default.LocationCity, "Sede", c.sede?.nombre ?: "No asignada")
                InfoRowWithIcon(Icons.Default.DateRange, "Fecha de pago", c.fechaPago ?: "No registrado")
                InfoRowWithIcon(Icons.Default.Money, "Mensualidad", c.mensualidad?.toString() ?: "0.0")

                // Descripción con wrapping y fondo ligero
                Spacer(Modifier.height(8.dp))
                Text(
                    "Descripción",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = BlackPrimary
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(BlackPrimary.copy(alpha = 0.05f), shape = RoundedCornerShape(12.dp))
                        .padding(12.dp)
                ) {
                    Text(
                        text = c.descripcion ?: "No hay descripción",
                        style = MaterialTheme.typography.bodyMedium,
                        color = BlackPrimary
                    )
                }
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
