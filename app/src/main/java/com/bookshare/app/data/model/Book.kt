package com.bookshare.app.data.model

import com.google.gson.annotations.SerializedName


data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    val available: Boolean,
    @SerializedName("owner_username")
    val ownerUsername: String
)