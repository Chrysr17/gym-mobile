package com.example.gymapp.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gymapp.data.model.ClienteDTO
import com.example.gymapp.ui.colors.AppTypography
import com.example.gymapp.ui.colors.BlackPrimary
import com.example.gymapp.ui.colors.GrayBackground
import com.example.gymapp.ui.colors.WhiteSecondary
import com.example.gymapp.ui.colors.YellowPrimary

@Composable
fun LoginScreen(onLoginSuccess: (ClienteDTO) -> Unit) {
    val viewModel: LoginViewModel = viewModel()
    val cliente by viewModel.cliente.observeAsState()
    val error by viewModel.error.observeAsState()

    var usuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBackground),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(16.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = BlackPrimary),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Iniciar Sesión", style = AppTypography.headlineMedium, color = YellowPrimary)

                Spacer(Modifier.height(20.dp))

                TextField(
                    value = usuario,
                    onValueChange = { usuario = it },
                    label = { Text("Usuario", color = YellowPrimary) },
                    singleLine = true,
                    textStyle = TextStyle(color = BlackPrimary),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña", color = YellowPrimary) },
                    singleLine = true,
                    textStyle = TextStyle(color = BlackPrimary),
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(24.dp))

                Button(
                    onClick = { viewModel.login(usuario, password) },
                    colors = ButtonDefaults.buttonColors(containerColor = YellowPrimary),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Entrar", color = BlackPrimary, style = AppTypography.bodyMedium)
                }

                error?.let {
                    Spacer(Modifier.height(12.dp))
                    Text(it, color = MaterialTheme.colorScheme.error)
                }

                cliente?.let { onLoginSuccess(it) }
            }
        }
    }
}
