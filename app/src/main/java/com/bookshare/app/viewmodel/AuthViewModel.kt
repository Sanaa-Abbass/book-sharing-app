package com.bookshare.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookshare.app.data.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val repository =AuthRepository()

    fun login(
        username: String,
        password: String
    ) {

        viewModelScope.launch {

            val response =repository.login(username, password)

            if (response.isSuccessful) {

                val token =response.body()?.access

                println("TOKEN = $token")
            } else {

                println(
                    "ERROR = ${response.errorBody()?.string()}"
                )
            }
        }
    }
}