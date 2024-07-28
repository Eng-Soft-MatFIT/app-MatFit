package com.devmasterteam.tasks.service.repository.remote

import com.devmasterteam.tasks.service.model.Aluno
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT

interface AlunoService {

    @GET("aluno")
    fun getAllAlunos(): Call<List<Aluno>>

    @GET("aluno/{cpf}")
    @FormUrlEncoded
    fun getAluno(@Field("cpf") cpf: String): Call<Aluno>

    @POST("aluno")
    @FormUrlEncoded
    fun saveAluno(
        @Field("cpf") cpf: String,
        @Field("name") name: String,
        @Field("sport") sport: String,
    ): Call<Boolean>

    @PATCH("aluno/pagar/{cpf}")
    @FormUrlEncoded
    fun payAluno(@Field("cpf") cpf: String): Call<Boolean>

    @PATCH("aluno/pagamento/{cpf}")
    @FormUrlEncoded
    fun paymentAluno(@Field("cpf") cpf: String): Call<Boolean>

    @PUT("aluno/{cpf}")
    @FormUrlEncoded
    fun updateAluno(
        @Field("cpf") cpf: String,
        @Field("name") name: String,
        @Field("sport") sport: String,
    ): Call<Boolean>

    @HTTP(method = "DELETE", path = "aluno/{cpf}", hasBody = true)
    @FormUrlEncoded
    fun deleteAluno(@Field("cpf") cpf: String): Call<Boolean>

}