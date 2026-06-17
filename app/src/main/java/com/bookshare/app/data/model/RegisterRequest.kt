package com.bookshare.app.data.model

import com.google.gson.annotations.SerializedName

data class RegisterRequest(

    val username: String,

    val email: String,

    val password: String,

    @SerializedName("invitation_code")
    val invitationCode: String
)