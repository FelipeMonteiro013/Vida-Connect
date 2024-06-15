package com.example.vidaconnect.service

import com.example.vidaconnect.model.MedicalAppointment
import com.example.vidaconnect.model.NewMedicalAppointment
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MedicalAppointmentService {

    @GET("/medicalAppointment/patient/{userId}")
    fun getMedicalAppointmentByPatientId(@Path("userId") userId: String): Call<List<MedicalAppointment>>

    @POST("/medicalAppointment")
    fun createAppointment(@Body appointment: NewMedicalAppointment) : Call<MedicalAppointment>
}