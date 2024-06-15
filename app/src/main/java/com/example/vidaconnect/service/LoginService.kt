package com.example.vidaconnect.service

import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(val email: String, val password: String)
interface LoginService {

    @POST("/pateient")
    fun login(@Body request: LoginRequest) : Call<Response<ResponseBody>>
}