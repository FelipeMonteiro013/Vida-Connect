package com.example.vidaconnect.model

import com.google.gson.annotations.SerializedName

data class Login (
    val email: String = "",
    val password: String = "",
)