package com.example.studentregistration.api

import com.example.studentregistration.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @POST("/students/register")
    suspend fun registerStudent(@Body registrationRequest: RegistrationRequest) : Response<RegistrationResponse>
    @POST("/students/login")
    suspend fun loginStudent(@Body loginRequest: LoginRequest) : Response<LoginResponse>
    @GET("/courses")
    suspend fun getCourses(@Header ("Authorization")token:String)  : Response<List<CoursesResponse>>
    @POST("/enrolments")
    suspend fun enrolCourse(@Header ("Authorization")token: String,@Body enrolmentRequest: EnrolmentRequest):Response<EnrolmentResponse>

}