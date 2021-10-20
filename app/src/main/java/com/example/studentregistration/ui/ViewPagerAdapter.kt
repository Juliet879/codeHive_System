package com.example.studentregistration.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager):FragmentPagerAdapter(fragmentManager){
    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        when(position){
            0-> return AllCoursesFragment()
            1-> return MyCoursesFragment()
            else -> return AllCoursesFragment()
        }
    }

    fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> AllCoursesFragment()
            1 -> MyCoursesFragment()
            else -> AllCoursesFragment()
        }
    }
}