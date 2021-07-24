package com.example.studentregistration.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentregistration.Models.Courses
import com.example.studentregistration.R
import com.example.studentregistration.adapters.CoursesRecyclerViewAdapter

class CoursesActivity : AppCompatActivity() {
    lateinit var message :TextView
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses)

        var studentName = intent.getStringExtra("name")

        message = findViewById(R.id.tvWelcomeMsg)

        message.text = "Welcome ${studentName}"

        display()
    }
    fun  display(){
        var rvCources = findViewById<RecyclerView>(R.id.rvCourses)
        var courseList = listOf(
            Courses("MB101","Mobile Development","Introduction to Android","John Owuo"),
            Courses("BC231","Backend Development","Introduction to Python-Django","James Mwai"),
            Courses("MB101","Frontend Development","Introduction to JavaScript","Purity Maina"),
            Courses("MB101","Internet Of Things","Introduction to IOT","Barren Yaasin"),
            Courses("MB101","Mobile Development","Introduction to Android","Veronicah Thamaini"),
            Courses("MB101","Mobile Development","Introduction to Professional Development","Rodgers Owuor"),
            Courses("MB101","Mobile Development","Introduction to UI/UX Design","Erick"),
            Courses("MB101","Mobile Development","Introduction to Android","John Owuo")
        )
        var coursesAdapter = CoursesRecyclerViewAdapter(courseList)
        rvCources.layoutManager = LinearLayoutManager(baseContext)
        rvCources.adapter = coursesAdapter
    }
}