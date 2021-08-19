package com.example.studentregistration.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("student_id") var student_id:String,
    @SerializedName("access_token") var access_token :String,
    var message :String
)
