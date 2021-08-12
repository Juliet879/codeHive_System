package com.example.studentregistration.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("studentId") var student_id:String,
    @SerializedName("accessToken") var access_token :String,
    var message :String
)
