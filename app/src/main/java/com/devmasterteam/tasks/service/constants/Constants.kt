package com.devmasterteam.tasks.service.constants

/**
 * Constantes usadas na aplicação
 */
class Constants private constructor() {

    // SharedPreferences
    object SHARED {
        const val TOKEN_KEY = "tokenkey"
        const val PERSON_KEY = "personkey"
        const val PERSON_NAME = "personname"
    }

    // Requisições API
    object HEADER {
        const val TOKEN_KEY = "token"
        const val PERSON_KEY = "personkey"
    }

    object HTTP {
        const val SUCCESS = 200
    }

    object FILTER {
        const val ALL = 0
        const val NEXT = 1
        const val EXPIRED = 2
    }

    object Attributs {
        const val CPF = "cpf"
        const val NAME = "name"
        const val SPORT = "sport"
        const val DAY = "day"
    }

}