package com.example.studentregistration.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentregistration.models.CoursesResponse
import com.example.studentregistration.repository.CoursesRepository
import kotlinx.coroutines.launch

class CoursesViewModel:ViewModel(){
    val coursesLiveData = MutableLiveData<List<CoursesResponse>>()
    val coursesFailedLiveData = MutableLiveData<String>()
    val coursesRepository = CoursesRepository()

    fun getCourses (access_token:String){
        viewModelScope.launch {
            val response = coursesRepository.getCourses(access_token)
            if (response.isSuccessful){
                coursesLiveData.postValue(response.body())
            }
            else{
                coursesFailedLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}