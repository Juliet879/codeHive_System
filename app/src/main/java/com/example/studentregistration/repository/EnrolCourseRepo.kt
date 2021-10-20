package com.example.studentregistration.repository

import com.example.studentregistration.api.ApiClient
import com.example.studentregistration.api.ApiInterface
import com.example.studentregistration.models.EnrolmentRequest
import com.example.studentregistration.models.EnrolmentResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject


class EnrolCourseRepo @Inject constructor(var service:ApiInterface) {
    suspend fun enrolCourse(access_token:String,enrolmentRequest: EnrolmentRequest)
    :Response<EnrolmentResponse> =
        withContext(Dispatchers.IO){
            var response = ApiClient.api.enrolCourse(access_token,enrolmentRequest)
        return@withContext response
    }

}