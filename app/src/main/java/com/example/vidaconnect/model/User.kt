package com.example.vidaconnect.model

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("_id")  val id: String ? = null,
    val document: String = "",
    val name: String = "",
    val email: String = "",
    val password: String? = null,
    @SerializedName("dt_nasc") val dtNasc: String = "",
)