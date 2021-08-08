package com.example.studentregistration.repository

import com.example.studentregistration.models.RegistrationRequest
import com.example.studentregistration.models.RegistrationResponse
import com.example.studentregistration.api.ApiClient
import com.example.studentregistration.api.ApiInterface
import com.example.studentregistration.models.LoginRequest
import com.example.studentregistration.models.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    private val apiInterface = ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun registerStudent(registrationRequest: RegistrationRequest) :
            Response<RegistrationResponse> =
        withContext(Dispatchers.IO) {
            return@withContext apiInterface.registerStudent(registrationRequest)
        }

    suspend fun loginStudent(loginRequest: LoginRequest):
            Response<LoginResponse> =
            withContext(Dispatchers.IO) {
                return@withContext apiInterface.loginStudent(loginRequest)
            }
}







