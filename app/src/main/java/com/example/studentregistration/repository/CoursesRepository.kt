package com.example.studentregistration.repository

import com.example.studentregistration.api.ApiClient
import com.example.studentregistration.api.ApiInterface
import com.example.studentregistration.api.SessionManager
import com.example.studentregistration.models.CoursesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CoursesRepository() {
//    private val  apiInterface = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun getCourses(access_token:String):
            Response<List<CoursesResponse>> =
        withContext(Dispatchers.IO){
            return@withContext ApiClient.api.getCourses(access_token)
        }
}