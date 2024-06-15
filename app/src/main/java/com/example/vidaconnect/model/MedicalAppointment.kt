package com.example.vidaconnect.model

import com.google.gson.annotations.SerializedName

data class MedicalAppointment(
    @SerializedName("_id") val id: String? = "",
    val symptoms: String = "",
    val medicalHistory: String = "",
    val date: String = "",
    val reason: String = "",
    @SerializedName("patient_id") val patientId: User,
    @SerializedName("clinic_id") val clinicId: Clinic? ,
    @SerializedName("doctor_id") val doctorId: Doctor,
    val status: String = ""
)
