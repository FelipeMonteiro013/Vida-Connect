package com.example.vidaconnect.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val URL = "http://ec2-44-223-23-150.compute-1.amazonaws.com:3000/"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun userService(): UserService {
        return retrofitFactory.create(UserService::class.java)
    }
    fun medicalAppointment(): MedicalAppointmentService {
        return retrofitFactory.create(MedicalAppointmentService::class.java)
    }
}