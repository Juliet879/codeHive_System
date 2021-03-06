package com.example.studentregistration.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentregistration.Constants
import com.example.studentregistration.R
import com.example.studentregistration.adapters.CoursesRecyclerViewAdapter
import com.example.studentregistration.databinding.ActivityCoursesBinding
import com.example.studentregistration.models.EnrolmentRequest
import com.example.studentregistration.viewmodel.CoursesViewModel
import com.example.studentregistration.viewmodel.EnrolCourseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoursesActivity : AppCompatActivity() {
    lateinit var binding: ActivityCoursesBinding
    private val coursesViewModel: CoursesViewModel by viewModels()
    val enrolCourseViewModel: EnrolCourseViewModel by viewModels()
    lateinit var prefs: SharedPreferences
    lateinit var bearer: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoursesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs = getSharedPreferences(Constants.SHAREDPREFS, Context.MODE_PRIVATE)
//        setupPagerAdapter()
    }


//    fun setupPagerAdapter() {
//
//        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
//        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.courses -> {
//                    binding.viewPager.setCurrentItem(0,false)
//                    return@setOnNavigationItemSelectedListener true               }
//                R.id.myCourses -> {
//                    binding.viewPager.setCurrentItem(1,false)
//                    return@setOnNavigationItemSelectedListener true               }
//                }
//            false
//            }
//
//        }
//}

    override fun onResume() {
        super.onResume()

        val accessToken = prefs.getString(Constants.ACCESSTOKEN, Constants.EMPTYSTRING)
        bearer = "Bearer ${accessToken}"

        //    Log user out if access token is empty
        if (!accessToken!!.isEmpty()) {
            // Instance of courseViewModel
            coursesViewModel.getCourses(bearer)
            //    Instance of the enrol request
            enrol()
        }

        coursesViewModel.coursesLiveData.observe(this, { courseList ->
            binding.rvCourses.adapter = CoursesRecyclerViewAdapter(courseList, baseContext,
                object : EnrolmentClickListener {
                    override fun onClickEnrolment(courseId: String) {
                        val studentId =
                            prefs.getString(Constants.STUDENTID, Constants.EMPTYSTRING)!!
                        val enrolReq = EnrolmentRequest(student_id = studentId, course_id = courseId)
                        enrolCourseViewModel.enrolCourse(bearer, enrolReq)
                    }
                })
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
            Toast.makeText(baseContext, "Already enrolled", Toast.LENGTH_LONG).show()
        })
    }
}

interface EnrolmentClickListener {
    fun onClickEnrolment(courseId: String)
}
