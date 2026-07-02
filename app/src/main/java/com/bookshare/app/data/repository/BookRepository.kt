package com.bookshare.app.data.repository

import com.bookshare.app.data.api.RetrofitClient
import com.bookshare.app.data.model.Book
import retrofit2.Response

class BookRepository {

    suspend fun getBooks(token: String?) : Response<List<Book>>{
        return RetrofitClient.api.getBooks(token)
    }

    suspend fun getBook(token: String , id : Int) : Response<Book>{
        return RetrofitClient.api.getBook(token,id)
    }

    suspend fun createBook(token: String , book: Book): Response<Book> {
        return RetrofitClient.api.createBook(token,book)
    }
}