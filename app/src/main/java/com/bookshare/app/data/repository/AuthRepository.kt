package com.bookshare.app.data.repository

import com.bookshare.app.data.model.LoginRequest
import com.bookshare.app.data.model.RegisterRequest
import com.bookshare.app.data.api.RetrofitClient

class AuthRepository {

    suspend fun login(username: String, password: String) =
        RetrofitClient.api.login(
            LoginRequest(
                username,
                password
            )
        )

    suspend fun register(request: RegisterRequest) =
        RetrofitClient.api.register(request)

    suspend fun getProfile(token: String) =
        RetrofitClient.api.getProfile("Bearer $token")
}