package com.example.gymapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.gymapp.data.model.ClienteDTO
import com.example.gymapp.ui.login.LoginScreen
import com.example.gymapp.ui.pagos.PagosScreen
import com.example.gymapp.ui.perfil.PerfilScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var cliente by remember { mutableStateOf<ClienteDTO?>(null) }
            var currentScreen by remember { mutableStateOf("login") }

            Scaffold(
                bottomBar = {
                    if (currentScreen != "login") {
                        BottomNav(currentScreen) { newScreen ->
                            if (newScreen == "login") cliente = null
                            currentScreen = newScreen
                        }
                    }
                }
            ) { padding ->
                Box(Modifier.padding(padding)) {
                    when (currentScreen) {
                        "login" -> LoginScreen { loginCliente ->
                            cliente = loginCliente
                            currentScreen = "perfil"
                        }
                        "perfil" -> cliente?.let { PerfilScreen(it.clienteId) }
                        "pagos" -> cliente?.let { PagosScreen(it.clienteId) }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNav(currentScreen: String, onNavigate: (String) -> Unit) {
    NavigationBar {
        NavigationBarItem(
            selected = currentScreen == "perfil",
            onClick = { onNavigate("perfil") },
            icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
            label = { Text("Perfil") }
        )
        NavigationBarItem(
            selected = currentScreen == "pagos",
            onClick = { onNavigate("pagos") },
            icon = { Icon(Icons.Default.List, contentDescription = "Pagos") },
            label = { Text("Pagos") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { onNavigate("login") },
            icon = { Icon(Icons.Default.ExitToApp, contentDescription = "Salir") },
            label = { Text("Salir") }
        )
    }
}