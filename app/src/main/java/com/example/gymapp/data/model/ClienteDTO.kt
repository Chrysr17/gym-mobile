package com.example.gymapp.data.model

data class ClienteDTO(
    val clienteId: Int,
    val nombre: String,
    val dni: String,
    val telefono: String?,
    val correo: String?,
    val direccion: String?,
    val sede: SedeDTO,
    val fechaPago: String?,
    val mensualidad: Double?,
    val descripcion: String?
)
