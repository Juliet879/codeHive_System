package com.example.studentregistration.Models

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    var email:String,
    var password:String
)
