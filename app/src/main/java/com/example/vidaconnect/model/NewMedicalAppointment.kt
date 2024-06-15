package com.example.vidaconnect.model

import com.google.gson.annotations.SerializedName

data class NewMedicalAppointment(
    @SerializedName("_id") val id: String? = "",
    val symptoms: String = "",
    val medicalHistory: String = "",
    val date: String = "",
    val reason: String = "",
    @SerializedName("patient_id") val patientId: String,
    @SerializedName("clinic_id") val clinicId: String ,
    @SerializedName("doctor_id") val doctorId: String,
    val status: String = "Agendado"
)
