package com.devmasterteam.tasks.view.ui.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.service.listener.APIListener
import com.devmasterteam.tasks.service.model.Aluno
import com.devmasterteam.tasks.service.model.ValidationModel
import com.devmasterteam.tasks.service.repository.AlunoRepository

class AlunoViewModel(application: Application) : AndroidViewModel(application) {

    // Instancia do repositorio
    private val repository = AlunoRepository(application.applicationContext)

    private val _saveAluno = MutableLiveData<ValidationModel>()
    val saveAluno: MutableLiveData<ValidationModel> = _saveAluno

    private val _deleteAluno = MutableLiveData<ValidationModel>()
    val deleteAluno: MutableLiveData<ValidationModel> = _deleteAluno

    private val _listAlunos = MutableLiveData<List<Aluno>>()
    val listAlunos: MutableLiveData<List<Aluno>> = _listAlunos

    private val _aluno = MutableLiveData<Aluno>()
    val aluno: MutableLiveData<Aluno> = _aluno

    private val _alunoFailure = MutableLiveData<ValidationModel>()
    val alunoFailure: MutableLiveData<ValidationModel> = _alunoFailure

    // Verifica se os campos estão preenchidos corretamente
    fun saveAluno(context: Context, aluno: Aluno): Boolean {
        return if (!validateCpf(aluno.cpf)) {
            Toast.makeText(context, R.string.textErrorCpf, Toast.LENGTH_SHORT).show()
            false
        } else if (!validateName(aluno.name)) {
            Toast.makeText(context, R.string.textErrorName, Toast.LENGTH_SHORT).show()
            false
        } else if (!validateSport(aluno.sport)) {
            Toast.makeText(context, R.string.textErrorSport, Toast.LENGTH_SHORT).show()
            false
        } else {
            repository.saveAluno(aluno, object : APIListener<Boolean> {
                override fun onSuccess(result: Boolean) {
                    _saveAluno.value = ValidationModel()
                }

                override fun onFailure(message: String) {
                    _saveAluno.value = ValidationModel(message)
                }
            })
            true
        }
    }

    fun listAllAlunos(){
        repository.listAllAlunos( object : APIListener<List<Aluno>> {
            override fun onSuccess(result: List<Aluno>) {
                _listAlunos.value = result
            }

            override fun onFailure(message: String) {
                val s = message
            }
        })
    }

    fun deleteAluno(cpf: String){
        repository.deleteAluno(cpf, object : APIListener<Boolean> {
            override fun onSuccess(result: Boolean) {
               listAllAlunos()
            }

            override fun onFailure(message: String) {
                _deleteAluno.value = ValidationModel(message)
            }
        })
    }

    fun getAluno(cpf: String){
        repository.getAluno(cpf, object : APIListener<Aluno> {
            override fun onSuccess(result: Aluno) {
                _aluno.value = result
            }

            override fun onFailure(message: String) {
                _alunoFailure.value = ValidationModel(message)
            }
        })
    }

    // Faz a validação do cpf
    private fun validateCpf(cpfOld: String): Boolean {
        // Remove caracteres não numéricos do CPF
        var cpf = cpfOld
        cpf = cpf.replace("[^0-9]".toRegex(), "")
            .replace("-", "").replace(".", "")

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length != 11) {
            return false
        }

        // Verifica se todos os dígitos são iguais
        var digitosIguais = true
        for (i in 1..10) {
            if (cpf[i] != cpf[0]) {
                digitosIguais = false
                break
            }
        }
        if (digitosIguais) {
            return false
        }

        // Validação dos dígitos verificadores
        var soma = 0
        for (i in 0..8) {
            soma += (10 - i) * (cpf[i].code - '0'.code)
        }
        var digito1 = 11 - soma % 11
        if (digito1 > 9) {
            digito1 = 0
        }
        soma = 0
        for (i in 0..9) {
            soma += (11 - i) * (cpf[i].code - '0'.code)
        }
        var digito2 = 11 - soma % 11
        if (digito2 > 9) {
            digito2 = 0
        }
        return cpf[9].code - '0'.code == digito1 && cpf[10].code - '0'.code == digito2
    }

    // Faz a validação do nome
    private fun validateName(name: String) =
        (name.isNotBlank() && name.isNotEmpty() && name.length >= 4)

    // Faz a validação do esporte
    private fun validateSport(sport: String) =
        (sport.length >= 3 && sport.isNotBlank() && sport.isNotEmpty())
}