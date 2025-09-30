package com.example.gymapp.data.model

data class UsuarioDTO(
    val usuarioId: Int,
    val nombreUsuario: String,
    val rol: String,
    val cliente: ClienteDTO?
)
