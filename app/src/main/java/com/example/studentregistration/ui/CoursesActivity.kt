package com.example.studentregistration.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentregistration.adapters.CoursesRecyclerViewAdapter
import com.example.studentregistration.databinding.ActivityCoursesBinding
import com.example.studentregistration.repository.CoursesRepository
import com.example.studentregistration.viewmodel.CoursesViewModel

class CoursesActivity : AppCompatActivity() {
    lateinit var binding: ActivityCoursesBinding
    val coursesViewModel: CoursesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
        binding = ActivityCoursesBinding.inflate(layoutInflater)
    setContentView(binding.root)

}
    override fun onResume() {
        super.onResume()
        coursesViewModel.getCourses()
        coursesViewModel.coursesLiveData.observe(this,{courseResponse ->
            binding.rvCourses.adapter = CoursesRecyclerViewAdapter(courseResponse)
            binding.rvCourses.layoutManager = LinearLayoutManager(baseContext)
        })
    }
}