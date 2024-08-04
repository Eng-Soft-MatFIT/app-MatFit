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
import retrofit2.http.Path

interface AlunoService {

    @GET("aluno")
    fun getAllAlunos(): Call<List<Aluno>>

    @GET("aluno/{cpf}")
    @FormUrlEncoded
    fun getAluno(@Path(value = "cpf", encoded = true) cpf: String): Call<Aluno>

    @POST("aluno")
    @FormUrlEncoded
   suspend fun saveAluno(
        @Field("cpf") cpf: String,
        @Field("nome") nome: String,
        @Field("esporte") esporte: String,
    ): Call<Boolean>

    @PATCH("aluno/pagar/{cpf}")
    @FormUrlEncoded
    fun doPaymentAluno(@Path(value = "cpf", encoded = true) cpf: String): Call<Boolean>

    @PATCH("aluno/pagamento/{cpf}")
    @FormUrlEncoded
    fun verifyPaymentAluno(@Path(value = "cpf", encoded = true) cpf: String): Call<Boolean>

    @PUT("aluno/{cpf}")
    @FormUrlEncoded
    fun updateAluno(
        @Path(value = "cpf", encoded = true) cpf: String,
        @Field("name") name: String,
        @Field("sport") sport: String,
    ): Call<Boolean>

    @HTTP(method = "DELETE", path = "aluno/{cpf}", hasBody = true)
    @FormUrlEncoded
    fun deleteAluno(@Path(value = "cpf", encoded = true) cpf: String): Call<Boolean>

}