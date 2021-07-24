package com.example.studentregistration.Services

import com.example.studentregistration.Models.LoginRequest
import com.example.studentregistration.Models.LoginResponse
import com.example.studentregistration.Models.RegistrationRequest
import com.example.studentregistration.Models.RegistrationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/students/register")
    fun registerStudent(@Body registrationRequest: RegistrationRequest) : Call<RegistrationResponse>
    @POST("/students/login")
    fun loginStudent(@Body loginRequest: LoginRequest) : Call<LoginResponse>
}