package com.devmasterteam.tasks.service.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class Aluno(
    @SerializedName("cpf")
    val cpf: String,

    @SerializedName("nome")
    val nome: String,

    @SerializedName("esporte")
    val esporte: String,

    @SerializedName("dataPagamento")
    var dataPagamento: String = "",

    @SerializedName("pagamentoAtrasado")
    var pagamentoAtrasado: Boolean = false,
)