package com.devmasterteam.tasks.service.repository

import android.content.Context
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.service.listener.APIListener
import com.devmasterteam.tasks.service.model.Aluno
import com.devmasterteam.tasks.service.repository.remote.AlunoService
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient

class AlunoRepository(context: Context) : BaseRepository(context) {

    private val remote = RetrofitClient.getService(AlunoService::class.java)

    fun saveAluno(aluno: Aluno, listener: APIListener<Boolean>) {
        if (!isConnectionAvaliable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        executeCall(remote.saveAluno(aluno.cpf, aluno.name, aluno.sport), listener)
    }

    fun updateAluno(aluno: Aluno, listener: APIListener<Boolean>) {
        if (!isConnectionAvaliable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        executeCall(remote.updateAluno(aluno.cpf, aluno.name, aluno.sport), listener)
    }

    fun deleteAluno(cpf: String ,listener: APIListener<Boolean>){
        if (!isConnectionAvaliable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        executeCall(remote.deleteAluno(cpf), listener)
    }

    fun doPaymentAluno(cpf: String, listener: APIListener<Boolean>){
        if (!isConnectionAvaliable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        executeCall(remote.doPaymentAluno(cpf), listener)
    }

    fun verifyPaymentAluno(cpf: String, listener: APIListener<Boolean>){
        if (!isConnectionAvaliable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        executeCall(remote.verifyPaymentAluno(cpf), listener)
    }

    fun getAluno(cpf: String, listener: APIListener<Aluno>) {
        if (!isConnectionAvaliable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        executeCall(remote.getAluno(cpf), listener)
    }

    fun listAllAlunos(listener: APIListener<List<Aluno>>) {
        if (!isConnectionAvaliable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        executeCall(remote.getAllAlunos(), listener)
    }


}
