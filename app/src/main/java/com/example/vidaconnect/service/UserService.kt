package com.example.vidaconnect.service

import com.example.vidaconnect.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

//    BaseURL
    @GET("patient/{id}")
    fun getUserById(@Path("id") id: String) : Call<User>

    @GET("patient/")
    fun getUsers() : Call<List<User>>

}