package com.example.studentregistration.viewmodel

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentregistration.models.LoginRequest
import com.example.studentregistration.models.LoginResponse
import com.example.studentregistration.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val userRepository: UserRepository): ViewModel() {
    val loginLiveData = MutableLiveData<LoginResponse>()
    val loginFailedLiveData = MutableLiveData<String>()

    fun loginStudent(loginRequest: LoginRequest){
        viewModelScope.launch {
            val response = userRepository.loginStudent(loginRequest)
            if (response.isSuccessful){
                loginLiveData.postValue(response.body())
            }
            else{
                loginFailedLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}

//   val response = userRepository.loginStudent(loginRequest)
