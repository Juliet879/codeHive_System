package com.example.studentregistration.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentregistration.models.RegistrationRequest
import com.example.studentregistration.models.RegistrationResponse
import com.example.studentregistration.repository.UserRepository
import kotlinx.coroutines.launch

class RegisterViewModel:ViewModel() {
    val registrationLiveData = MutableLiveData<RegistrationResponse>()
    val registrationFailedLiveData = MutableLiveData<String>()
    private val userRepository = UserRepository()

    fun registerUser(registrationRequest: RegistrationRequest){
//    launch - creating an actual coroutine
        viewModelScope.launch {
            val response = userRepository.registerStudent(registrationRequest)
            if (response.isSuccessful){
                registrationLiveData.postValue(response.body())
            }
            else{
                registrationFailedLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}