package com.example.studentregistration.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentregistration.Constants
import com.example.studentregistration.R
import com.example.studentregistration.adapters.CoursesRecyclerViewAdapter
import com.example.studentregistration.databinding.FragmentAllCoursesBinding
import com.example.studentregistration.models.Course
import com.example.studentregistration.models.EnrolmentRequest
import com.example.studentregistration.viewmodel.CoursesViewModel
import com.example.studentregistration.viewmodel.EnrolCourseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllCoursesFragment : Fragment() {
    private val coursesViewModel: CoursesViewModel by viewModels()
    val enrolCourseViewModel: EnrolCourseViewModel by viewModels()
    private var _binding: FragmentAllCoursesBinding? = null
    private val binding get() = _binding!!
    lateinit var prefs: SharedPreferences
    lateinit var bearer: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAllCoursesBinding.inflate(layoutInflater, container, false)
        prefs = requireContext().getSharedPreferences(Constants.SHAREDPREFS, Context.MODE_PRIVATE)
        return inflater.inflate(R.layout.fragment_all_courses, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val accessToken = prefs.getString(Constants.ACCESSTOKEN, Constants.EMPTYSTRING)
        bearer = "Bearer ${accessToken}"

        //    Log user out if access token is empty
        if (!accessToken!!.isEmpty()) {
            // Instance of courseViewModel
            coursesViewModel.getCourses(bearer)
            //    Instance of the enrol request
            enrol()
        }

        coursesViewModel.coursesLiveData.observe(viewLifecycleOwner, { courseList ->
            binding.rvAllCourses.adapter = CoursesRecyclerViewAdapter(courseList,requireContext(),
                object : EnrolmentClickListener {
                    override fun onClickEnrolment(courseId: String) {
                        val studentId = prefs.getString(Constants.STUDENTID, Constants.EMPTYSTRING)!!
                        val enrolReq = EnrolmentRequest(
                            student_id = studentId,
                            course_id = courseId
                        )
                        enrolCourseViewModel.enrolCourse(bearer, enrolReq)
                    }
                })
            binding.rvAllCourses.layoutManager = LinearLayoutManager(requireContext())
            Toast.makeText(requireContext(), "${courseList.size} courses", Toast.LENGTH_LONG).show()

        })
        coursesViewModel.coursesFailedLiveData.observe(viewLifecycleOwner, { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()

        })
    }

    fun enrol() {
        enrolCourseViewModel.enrolLiveData.observe(viewLifecycleOwner, { enrolResponse ->
            Toast.makeText(requireContext(), "${enrolResponse.active}", Toast.LENGTH_LONG).show()
        })
        enrolCourseViewModel.enrolFailed.observe(viewLifecycleOwner, { error ->
            Toast.makeText(requireContext(), "Already enrolled", Toast.LENGTH_LONG).show()
        })
    }


    interface EnrolmentClickListener {
        fun onClickEnrolment(courseId: String)
    }


    fun displayCourses(courseList: List<Course>) {
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
