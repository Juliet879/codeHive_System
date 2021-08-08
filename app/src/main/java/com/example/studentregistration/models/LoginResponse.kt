package com.example.studentregistration.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("student_id") var studentId:String,
    @SerializedName("access_token") var accessToken :String,
    var message :String
)