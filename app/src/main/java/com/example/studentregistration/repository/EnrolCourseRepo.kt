package com.example.studentregistration.repository

import com.example.studentregistration.api.ApiClient
import com.example.studentregistration.models.EnrolmentRequest
import com.example.studentregistration.models.EnrolmentResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class EnrolCourseRepo {
    suspend fun enrolCourse(access_token:String,enrolmentRequest: EnrolmentRequest)
    :Response<EnrolmentResponse> = withContext(Dispatchers.IO){
        return@withContext ApiClient.api.enrolCourse(access_token,enrolmentRequest)
    }

}