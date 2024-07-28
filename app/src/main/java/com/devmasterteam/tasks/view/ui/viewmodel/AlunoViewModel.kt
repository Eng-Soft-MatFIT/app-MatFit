package com.devmasterteam.tasks.view.ui.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.service.listener.APIListener
import com.devmasterteam.tasks.service.model.Aluno
import com.devmasterteam.tasks.service.repository.AlunoRepository
import kotlinx.coroutines.launch

class AlunoViewModel(application: Application) : AndroidViewModel(application) {

    // Instancia do repositorio
    private val repository = AlunoRepository(application.applicationContext)

    // Verifica se os campos estão preenchidos corretamente
    fun validation(context: Context, aluno: Aluno): Boolean {
        return if (!validateCpf(aluno.cpf)) {
            Toast.makeText(context, R.string.textErrorCpf, Toast.LENGTH_SHORT).show()
            false
        } else if (!validateName(aluno.name)) {
            Toast.makeText(context, R.string.textErrorName, Toast.LENGTH_SHORT).show()
            false
        } else if (!validateSport(aluno.sport)) {
            Toast.makeText(context, R.string.textErrorSport, Toast.LENGTH_SHORT).show()
            false
        } else if (!validateDay(aluno.datePayment)) {
            Toast.makeText(context, R.string.textErrorDay, Toast.LENGTH_SHORT).show()
            false
//        } else if (cpfRegister(aluno.cpf)) {
//            Toast.makeText(context, R.string.textErrorCpfExistent, Toast.LENGTH_SHORT).show()
//            false
        } else {
            viewModelScope.launch {
              //  repository.save(aluno)
                Toast.makeText(context, R.string.textSucessRegister, Toast.LENGTH_SHORT).show()
            }
            true
        }
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

    // Verifica se o cpf a tentar cadastrar ja está sendo utilizado
//    private fun cpfRegister(cpf: String): Boolean {
//        return if (repository.getAluno(cpf, object : APIListener<>) == null)
//            false
//        else
//            true
//    }

    // Faz a validação do nome
    private fun validateName(name: String) =
        (name.isNotBlank() && name.isNotEmpty() && name.length >= 4)

    // Faz a validação do esporte
    private fun validateSport(sport: String) =
        (sport.length >= 3 && sport.isNotBlank() && sport.isNotEmpty())

    // Faz a validação do dia
    private fun validateDay(day: String): Boolean {
        day.replace(".", "").replace("-", "").replace(",", "")
        return (day.toInt() in 1..31 && day.isNotEmpty() && day.isNotBlank())
    }
}