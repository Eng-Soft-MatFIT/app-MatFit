package com.devmasterteam.tasks.service.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class Aluno(
    @SerializedName("cpf")
    val cpf: String,

    @SerializedName("nome")
    val name: String,

    @SerializedName("esporte")
    val sport: String,

    @SerializedName("dataPagamento")
    val datePayment: String,

    @SerializedName("pagamentoAtrasado")
    val latePayment: Boolean = false,
)