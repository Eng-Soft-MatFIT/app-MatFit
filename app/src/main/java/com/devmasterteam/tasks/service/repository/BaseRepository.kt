package com.devmasterteam.tasks.service.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.service.constants.Constants
import com.devmasterteam.tasks.service.listener.APIListener
import com.devmasterteam.tasks.service.model.ErrorResponse
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseRepository(val context: Context) {

    private fun failResponse(str: String): String {
        return try {
            val errorResponse = Gson().fromJson(str, ErrorResponse::class.java)
            errorResponse.message ?: "Erro desconhecido"
        } catch (e: JsonSyntaxException) {
            "Erro de sintaxe JSON: ${e.localizedMessage}"
        } catch (e: IllegalStateException) {
            "Erro de estado JSON: ${e.localizedMessage}"
        }
    }

    fun <T> executeCall(call: Call<T>, listener: APIListener<T>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                Log.d("BaseRepository", "onResponse: ${response.code()}")
                if (response.isSuccessful)
                    response.body()?.let {
                        listener.onSuccess(it)
                    } ?: run {
                        listener.onFailure("Resposta vazia do servidor.")
                    }
                else {
                    val errorBody = response.errorBody()?.string()
                    if (errorBody != null)
                        listener.onFailure(failResponse(errorBody))
                    else
                        listener.onFailure("Erro desconhecido.")
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                Log.d("BaseRepository", "onFailure: ${t.localizedMessage}")
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED) + " => ${t.localizedMessage}")
            }
        })
    }

    fun isConnectionAvaliable(): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNet = cm.activeNetwork ?: return false
            val networkCapabilities = cm.getNetworkCapabilities(activeNet) ?: return false
            result = when {
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            if (cm.activeNetworkInfo != null) {
                result = when (cm.activeNetworkInfo!!.type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return result
    }

    /*
        private fun failResponse(str: String): String {
        return Gson().fromJson(str, String::class.java)
    }

    fun <T> executeCall(call: Call<T>, listener: APIListener<T>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.code() == Constants.HTTP.SUCCESS)
                    response.body()?.let { listener.onSuccess(it) }
                else
                    listener.onFailure(failResponse(response.errorBody()!!.string()))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }
     */

}