package com.example.studentregistration.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.studentregistration.models.Course
import com.example.studentregistration.models.CoursesResponse

@Dao
interface CoursesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCourse(course: Course)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCourses(vararg courses: Course)

    @Query("SELECT * FROM Courses")
    fun getCourses(): LiveData<List<Course>>
}