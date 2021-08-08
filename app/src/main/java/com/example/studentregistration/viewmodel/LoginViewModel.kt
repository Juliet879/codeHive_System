package com.example.studentregistration.viewmodel

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentregistration.models.LoginRequest
import com.example.studentregistration.models.LoginResponse
import com.example.studentregistration.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    val loginLiveData = MutableLiveData<LoginResponse>()
    val loginFailedLiveData = MutableLiveData<String>()
    private val userRepository = UserRepository()

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