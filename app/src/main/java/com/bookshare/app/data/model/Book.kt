package com.bookshare.app.data.model

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val ownerName: String,
    val available: Boolean
)
