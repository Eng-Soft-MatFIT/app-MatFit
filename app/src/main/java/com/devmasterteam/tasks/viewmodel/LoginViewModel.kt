package com.devmasterteam.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devmasterteam.tasks.service.constants.TaskConstants
import com.devmasterteam.tasks.service.model.ValidationModel
import com.devmasterteam.tasks.service.repository.SecurityPreferences
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    // Instancias
    private val securityPreferences = SecurityPreferences(application.applicationContext)
  //   private val priorityRepository = PriorityRepository(application.applicationContext)

    // Variáveis a serem observadas
    private val _login = MutableLiveData<ValidationModel>()
    val login: LiveData<ValidationModel> = _login

    private val _loggedUser = MutableStateFlow(true)
    val loggedUser = _loggedUser.asStateFlow()


    // Verifica se usuário está logado
    fun verifyLoggedUser() {
        val token = securityPreferences.get(TaskConstants.SHARED.TOKEN_KEY)
        val person = securityPreferences.get(TaskConstants.SHARED.PERSON_KEY)
        RetrofitClient.addHeaders(token, person)

        val logged = (token != "" && person != "")
        _loggedUser.value = logged

    }
}