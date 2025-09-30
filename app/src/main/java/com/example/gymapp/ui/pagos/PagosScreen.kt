package com.example.gymapp.ui.pagos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import com.example.gymapp.ui.colors.BlackPrimary
import com.example.gymapp.ui.colors.GrayBackground
import com.example.gymapp.ui.colors.YellowPrimary
import com.example.gymapp.ui.perfil.InfoRow


@Composable
fun PagosScreen(idCliente: Int) {
    val viewModel: PagosViewModel = viewModel()
    val pagos by viewModel.pagos.observeAsState(emptyList())
    val error by viewModel.error.observeAsState()

    LaunchedEffect(idCliente) { viewModel.cargarPagos(idCliente) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBackground)
            .padding(16.dp)
    ) {
        Text(
            "Historial de Pagos",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = BlackPrimary
        )

        Spacer(Modifier.height(12.dp))

        error?.let { Text(it, color = MaterialTheme.colorScheme.error) }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(pagos) { pago ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = YellowPrimary),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        InfoRow(label = "Fecha", value = pago.fecha)
                        InfoRow(label = "Monto", value = pago.monto.toString())
                        InfoRow(label = "Estado", value = pago.estado)
                    }
                }
            }
        }
    }
}
