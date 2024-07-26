package com.devmasterteam.tasks.service.model

data class Aluno(
    val cpf: String,
    val name: String,
    val sport: String,
    val datePayment: String,
    val latePayment: Boolean = false
)