package com.bookshare.app.data.api


import com.bookshare.app.data.model.*

import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("api/auth/login/")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>


    @POST("api/auth/register/")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<Unit>


    @GET("api/auth/profile/")
    suspend fun getProfile(
        @Header("Authorization") token: String
    ): Response<User>


    @GET("api/books/")
    suspend fun getBooks(
        @Header("Authorization") token: String?
    ): Response<List<Book>>

    @GET("api/books/{id}/")
    suspend fun getBook(
        @Header("Authorization") token: String,
        @Path("id") id : Int
    ): Response<Book>

    @POST("api/books/")
    suspend fun createBook(
        @Header("Authorization") token: String,
        @Body book: Book
    ): Response<Book>
}