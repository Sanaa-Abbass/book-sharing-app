package com.bookshare.app.data.model

import com.google.gson.annotations.SerializedName


data class User(

    val id: Int,

    val username: String,

    val email: String,

    @SerializedName("building_name")
    val buildingName: String,

    @SerializedName("is_verified")
    val isVerified: Boolean
)