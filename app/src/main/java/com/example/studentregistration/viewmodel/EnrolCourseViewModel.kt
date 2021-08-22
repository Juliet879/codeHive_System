package com.example.studentregistration.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentregistration.models.EnrolmentRequest
import com.example.studentregistration.models.EnrolmentResponse
import com.example.studentregistration.repository.EnrolCourseRepo
import kotlinx.coroutines.launch

class EnrolCourseViewModel:ViewModel() {
    val enrolCourseRepo = EnrolCourseRepo()
    val enrolLiveData = MutableLiveData<EnrolmentResponse>()
    val enrolFailed  = MutableLiveData<String>()

    fun enrolCourse(access_token:String,enrolmentRequest: EnrolmentRequest){
        viewModelScope.launch {
            var response = enrolCourseRepo.enrolCourse(access_token,enrolmentRequest)
            if (response.isSuccessful){
                enrolLiveData.postValue(response.body())
            }
            else {
                enrolFailed.postValue(response.errorBody()!!.string())
            }
        }
    }
}