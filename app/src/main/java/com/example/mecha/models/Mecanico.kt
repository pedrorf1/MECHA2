package com.example.mecha.models

data class Mecanico(
    val IdMecanico: Int,
    val Ubicacion: String,
    val Especialidad: String,
    val Avale: String?,
    val Ranking: Double,
    val IdPersona: Int,
    val Telefono: String
)
