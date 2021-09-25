package com.example.studentregistration.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.fragment.app.viewModels
import com.example.studentregistration.Constants
import com.example.studentregistration.R
import com.example.studentregistration.adapters.CoursesRecyclerViewAdapter
import com.example.studentregistration.adapters.CoursesViewHolder
import com.example.studentregistration.databinding.FragmentAllCoursesBinding
import com.example.studentregistration.models.Course
import com.example.studentregistration.viewmodel.CoursesViewModel


class AllCoursesFragment : Fragment() {
    val coursesViewModel: CoursesViewModel by viewModels()
    private var _binding: FragmentAllCoursesBinding? = null
    private val binding get() = _binding!!
    lateinit var prefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAllCoursesBinding.inflate(layoutInflater, container, false)
        prefs = requireContext().getSharedPreferences(Constants.SHAREDPREFS, Context.MODE_PRIVATE)
        return inflater.inflate(R.layout.fragment_all_courses, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coursesViewModel.coursesLiveData.observe(viewLifecycleOwner, { courseList ->
            Toast.makeText(requireContext(), "${courseList.size}", Toast.LENGTH_LONG).show()

        })
        coursesViewModel.coursesFailedLiveData.observe(viewLifecycleOwner, { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()

        })
    }

    fun displayCourses(courseList: List<Course>) {
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}