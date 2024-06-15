package com.example.vidaconnect.service

import com.example.vidaconnect.model.Login
import com.example.vidaconnect.model.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {

    //    BaseURL
    @GET("patient/{id}")
    fun getUserById(@Path("id") id: String): Call<User>

    @GET("patient/")
    fun getUsers(): Call<List<User>>

    @POST("/patient")
    fun createUser(@Body user: User): Call<User>

    @POST("/patient/login")
    fun login(@Body login: Login ) : Call<User>

}