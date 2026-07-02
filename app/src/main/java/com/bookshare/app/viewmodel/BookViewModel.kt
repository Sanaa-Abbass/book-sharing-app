package com.bookshare.app.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookshare.app.data.datastore.TokenManager
import com.bookshare.app.data.model.Book
import com.bookshare.app.data.repository.BookRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class BookViewModel : ViewModel(){
    private val  repository =BookRepository()

    var books by mutableStateOf<List<Book>>(emptyList())
        private set
    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun loadBooks(context: Context){
        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                val  token = "Bearer " +TokenManager(context).accessToken.first()
                val  response = repository.getBooks(token)

                if (token == null) {

                    errorMessage = "User not logged in."
                    isLoading = false
                    return@launch
                }

                if (response.isSuccessful){
                    books = response.body() ?: emptyList()

                    print("Books : $books")
                }else{
                    print(response.errorBody()?.string())
                }
            }catch (e : Exception){
                Log.e("Books", "Network Error", e)

            }finally {
                isLoading = false
            }
        }
    }
}