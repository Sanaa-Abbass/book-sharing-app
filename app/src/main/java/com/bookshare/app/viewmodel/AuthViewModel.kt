package com.bookshare.app.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookshare.app.data.datastore.TokenManager
import com.bookshare.app.data.model.User
import com.bookshare.app.data.repository.AuthRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val repository = AuthRepository()
    // Logged-in user

    // The UI can observe this state, but only the ViewModel can change it.
    var currentUser by mutableStateOf<User?>(null)
        private set

    // Loading state
    var isLoading by mutableStateOf(false)
        private set

    // Error message
    var errorMessage by mutableStateOf<String?>(null)
        private set

    /**
     * Login
     */
    fun login(context: Context, username: String, password: String, onSuccess: () -> Unit) {

        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {

                val response = repository.login(username, password)

                if (response.isSuccessful) {

                    val body = response.body()

                    if (body != null) {

                        TokenManager(context).saveTokens(body.access, body.refresh)

                        onSuccess()

                    } else {

                        errorMessage = "Empty response from server."
                    }

                } else {

                    errorMessage ="Login failed (${response.code()})"

                    Log.e("LOGIN", response.errorBody()?.string() ?: "")
                }

            } catch (e: Exception) {

                errorMessage = e.message

                Log.e("LOGIN", "Network Error", e)

            } finally {

                isLoading = false
            }
        }
    }

    /**
     * Load Profile
     */
    fun loadProfile(context: Context) {

        viewModelScope.launch {

            isLoading = true
            errorMessage = null

            try {

                val token =TokenManager(context).accessToken.first()

                if (token == null) {

                    errorMessage = "User not logged in."
                    isLoading = false
                    return@launch
                }

                val response =repository.getProfile(token)

                if (response.isSuccessful) {

                    currentUser =response.body()

                } else {

                    errorMessage ="Failed to load profile (${response.code()})"

                    Log.e("PROFILE", response.errorBody()?.string() ?: "")
                }

            } catch (e: Exception) {

                errorMessage = e.message

                Log.e("PROFILE", "Network Error", e)

            } finally {

                isLoading = false
            }
        }
    }

    /**
     * Logout
     */
    fun logout(context: Context, onLogout: () -> Unit) {

        viewModelScope.launch {

            TokenManager(context).clearTokens()

            currentUser = null

            onLogout()
        }
    }
}