package com.example.studentregistration.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentregistration.api.ApiInterface
import com.example.studentregistration.database.CoursesDao
import com.example.studentregistration.models.Course
import com.example.studentregistration.models.CoursesResponse
import com.example.studentregistration.repository.CoursesRepository
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoursesViewModel @Inject constructor(var coursesRepository: CoursesRepository):ViewModel(){
    var coursesLiveData = MutableLiveData<List<Course>>()
    val coursesFailedLiveData = MutableLiveData<String>()

    fun getCourses (access_token:String){
        viewModelScope.launch {
            val response = coursesRepository.getCourses(access_token)
            if (response.isSuccessful) {
                coursesLiveData.postValue(response.body())
            }
        }
        }
    fun getDbCourses(){
        coursesLiveData = coursesRepository.getCoursesFromDb() as MutableLiveData<List<Course>>
    }

}