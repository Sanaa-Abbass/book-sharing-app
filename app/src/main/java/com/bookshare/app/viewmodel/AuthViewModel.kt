package com.bookshare.app.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookshare.app.data.datastore.TokenManager
import com.bookshare.app.data.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val repository =AuthRepository()

    fun login(
        context: Context,
        username: String,
        password: String,
        onSuccess: () -> Unit
    ) {

        viewModelScope.launch {

            try{
                val response=repository.login(username, password)

                if (response.isSuccessful) {

                    val token=response.body()

                    if (token != null) {

                        TokenManager(context).saveTokens(token.access, token.refresh)

                        onSuccess()
                    }

                } else {

                    println(
                        "ERROR = ${response.errorBody()?.string()}"
                    )
                }
            }catch (e: Exception){
                println("NETWORK ERROR = ${e.message}")
            }
        }
    }
}