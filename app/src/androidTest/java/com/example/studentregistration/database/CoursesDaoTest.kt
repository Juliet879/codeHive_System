package com.example.studentregistration.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.studentregistration.getOrAwaitValue
import androidx.test.filters.SmallTest
import com.example.studentregistration.models.Course
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@SmallTest
@ExperimentalCoroutinesApi
class CoursesDaoTest {
    private lateinit var database: CodeHiveDatabase
    private lateinit var coursesDao: CoursesDao

//    allows you to  run code in the same thread, theres no switching of context
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CodeHiveDatabase::class.java
        ).allowMainThreadQueries().build()

        coursesDao = database.getCourseDao()
    }

    @After
    fun rearDown() {
        database.close()
    }

    @Test
    fun testInsertCourse() {
        runBlockingTest {
            val course = Course("21", "WJDJD", "Biology", "new Bology", "PK Kare")
            coursesDao.insertCourse(course)
            val courses = coursesDao.getCourses().getOrAwaitValue()
            assertThat(courses).contains(course)
        }
    }

    @Test
    fun testGetDbCourses() {
        runBlockingTest {
            val course1 = Course("23", "WJDJD", "Biology", "new Bology", "PK Kare")
            val course2 = Course("24", "WJDJD", "Biology", "new Bology", "PK Kare")
            val course3 = Course("25", "WJDJD", "Biology", "new Bology", "PK Kare")
            coursesDao.insertCourse(course1)
            coursesDao.insertCourse(course2)
            coursesDao.insertCourse(course3)
            val coursesList = coursesDao.getCourses().getOrAwaitValue()
            assertThat(coursesList.size).isEqualTo(3)
        }
    }
}

//getOrAwaitValue => test rule that swaps
