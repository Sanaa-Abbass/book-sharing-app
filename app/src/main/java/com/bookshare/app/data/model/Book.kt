package com.bookshare.app.data.model

import com.google.gson.annotations.SerializedName


data class Book(
    val id: Int,
    val owner: String,
    val title: String,
    val author: String,
    val description: String,
    val image: String?,
    val available: Boolean,
    val language: String,
    val category: String,
    val condition: String,


    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String
)