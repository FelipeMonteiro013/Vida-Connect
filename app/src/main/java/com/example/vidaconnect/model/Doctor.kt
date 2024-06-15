package com.example.vidaconnect.model

import com.google.gson.annotations.SerializedName

data class Doctor(
    @SerializedName("_id")  val id: String ? = null,
    val crm: String = "",
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    @SerializedName("specialty_id") val specialtyId : String = "",
    @SerializedName("clinic_id") val clinicId: String = "",
    val status: Boolean,
)
