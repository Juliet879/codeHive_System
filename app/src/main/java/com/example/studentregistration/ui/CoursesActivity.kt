package com.example.studentregistration.ui

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentregistration.Constants
import com.example.studentregistration.adapters.CoursesRecyclerViewAdapter
import com.example.studentregistration.databinding.ActivityCoursesBinding
import com.example.studentregistration.models.EnrolmentRequest
import com.example.studentregistration.repository.CoursesRepository
import com.example.studentregistration.viewmodel.CoursesViewModel
import com.example.studentregistration.viewmodel.EnrolCourseViewModel

class CoursesActivity : AppCompatActivity() {
    lateinit var binding: ActivityCoursesBinding
    val coursesViewModel: CoursesViewModel by viewModels()
    val enrolCourseViewModel: EnrolCourseViewModel by viewModels()
    lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoursesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs = getSharedPreferences(Constants.SHAREDPREFS, Context.MODE_PRIVATE)

    }

    override fun onResume() {
        super.onResume()

        val accessToken = prefs.getString(Constants.ACCESSTOKEN, Constants.EMPTYSTRING)
        val bearer = "Bearer ${accessToken}"
        val studentIdIntent = intent.getStringExtra("STUDENT_ID")
        val courseIntent = intent.getStringExtra("courseId")


//        Log user out if access token is empty
        if (!accessToken!!.isEmpty()) {
            enrol()
            coursesViewModel.getCourses(bearer)
            val enrolReq = EnrolmentRequest(
                student_id = studentIdIntent.toString(),
                course_id = courseIntent.toString()
            )
            enrolCourseViewModel.enrolCourse(bearer, enrolReq)
        }

        coursesViewModel.coursesLiveData.observe(this, { courseList ->
            binding.rvCourses.adapter = CoursesRecyclerViewAdapter(courseList, baseContext)
            binding.rvCourses.layoutManager = LinearLayoutManager(baseContext)
            Toast.makeText(baseContext, "${courseList.size} courses", Toast.LENGTH_LONG).show()

        })
        coursesViewModel.coursesFailedLiveData.observe(this, { error ->
            Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()

        })
    }

    fun enrol() {
        enrolCourseViewModel.enrolLiveData.observe(this, { enrolResponse ->
            Toast.makeText(baseContext, "${enrolResponse.active}", Toast.LENGTH_LONG).show()
        })
        enrolCourseViewModel.enrolFailed.observe(this, { error ->
            Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
        })
    }

}