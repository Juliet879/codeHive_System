package com.example.studentregistration.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.studentregistration.BaseTest
import com.example.studentregistration.getOrAwait
import com.example.studentregistration.models.Course
import com.example.studentregistration.repository.CoursesRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class CoursesViewModelTest:BaseTest() {
    @MockK
    lateinit var coursesRepository: CoursesRepository

    @InjectMockKs
    lateinit var coursesViewModel: CoursesViewModel

    val dispatcher = TestCoroutineDispatcher()


    @Before
    override fun setUp() {
        super.setUp()
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun testGetCourseFromDb(){
        val coursesList = listOf(Course("123", "YDHH", "Literature", "fsunffuid", "Manuva"))
        every { coursesRepository.getCoursesFromDb() } returns MutableLiveData(coursesList)
        coursesViewModel.getDbCourses()
        val value = coursesViewModel.coursesLiveData.getOrAwait()
        assertThat(value).isEqualTo(coursesList)
    }

    @Test
    fun testFetchCourses(){
        val coursesList = listOf(Course("123", "YDHH", "Literature", "fsunffuid", "Manuva"))
        coEvery { coursesRepository.getCourses("djd") } returns Response.success(coursesList)
        runBlocking {
            coursesRepository.getCourses("djd")
        }
        coVerify { coursesRepository.getCourses("djd") }        //asserts/ verify that when you call one function it calls another function
    }

}