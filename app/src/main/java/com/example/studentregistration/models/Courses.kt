package com.example.studentregistration.models

import com.google.gson.annotations.SerializedName

data class Courses(
    @SerializedName("course_id")var courseId:String,
    @SerializedName("course_code")var courseCode:String,
    @SerializedName("course_name")var courseName:String,
    var description:String,
    var instructor:String
)