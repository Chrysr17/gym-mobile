package com.example.gymapp.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gymapp.data.model.ClienteDTO

@Composable
fun LoginScreen(onLoginSuccess: (ClienteDTO) -> Unit) {
    val viewModel: LoginViewModel = viewModel()
    val cliente by viewModel.cliente.observeAsState()
    val error by viewModel.error.observeAsState()

    var usuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Iniciar sesión", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(16.dp))

        TextField(value = usuario, onValueChange = { usuario = it }, label = { Text("Usuario") })
        TextField(value = password, onValueChange = { password = it }, label = { Text("Contraseña") })

        Spacer(Modifier.height(16.dp))

        Button(onClick = { viewModel.login(usuario, password) }, Modifier.fillMaxWidth()) {
            Text("Entrar")
        }

        error?.let { Text(it, color = MaterialTheme.colorScheme.error) }

        cliente?.let { onLoginSuccess(it) }
    }
}
