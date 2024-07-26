package com.devmasterteam.tasks.service.repository.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface AlunoService {

    @GET("/Aluno/{cpf}")
    fun getAluno(@Path(value = "cpf") cpf: String)


    fun deleteAluno(cpf: String)

}