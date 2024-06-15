package com.example.vidaconnect.model

import com.google.gson.annotations.SerializedName

data class Clinic(
    @SerializedName("_id")  val id: String ? = null,
    val corporateReason: String = "",
    val cnpj: String = "",
    val address: String = "",
    val phone: String = "",
    val email: String = "",
    val status: Boolean,
)
