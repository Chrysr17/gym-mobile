package com.example.gymapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

            when (currentScreen) {
                "login" -> LoginScreen { loginCliente ->
                    cliente = loginCliente
                    currentScreen = "perfil"
                }

                "perfil" -> {
                    cliente?.let {
                        PerfilScreen(it.clienteId)
                    } ?: run {
                        // opcional: mostrar loading o mensaje de error
                        Text("Cargando información del cliente...")
                    }
                }

                "pagos" -> {
                    cliente?.let {
                        PagosScreen(it.clienteId)
                    } ?: run {
                        Text("Cargando información de pagos...")
                    }
                }
            }
        }
    }
}