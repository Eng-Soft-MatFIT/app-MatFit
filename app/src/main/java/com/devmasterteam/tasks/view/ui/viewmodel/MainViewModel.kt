package com.devmasterteam.tasks.view.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.devmasterteam.tasks.service.constants.Constants
import com.devmasterteam.tasks.service.repository.SecurityPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(application: Application) : AndroidViewModel(application) {

    // Instancia do SecurityPreferences
    private val securityPreferences = SecurityPreferences(application.applicationContext)

    // Variavel a ser observada
    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    // Responsável por fazer o logout do usuário
    fun logout(){
        securityPreferences.remove(Constants.SHARED.TOKEN_KEY)
        securityPreferences.remove(Constants.SHARED.PERSON_KEY)
        securityPreferences.remove(Constants.SHARED.PERSON_NAME)
    }

    // Responsável por buscar o nome salvo do usuário
    fun loadUserName() {
        _name.value = securityPreferences.get(Constants.SHARED.PERSON_NAME)
    }
}