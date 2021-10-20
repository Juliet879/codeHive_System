package com.example.studentregistration.repository

import androidx.lifecycle.MutableLiveData
import com.example.studentregistration.BaseTest
import com.example.studentregistration.getOrAwait
import com.example.studentregistration.models.Course
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response

class CoursesRepositoryTest : BaseTest() {
    @MockK
    lateinit var coursesRepository: CoursesRepository

    @Test
    fun testfetchCourses() {
        val courseList = listOf(Course("892", "HDJJ2", "Mathematics", "jkjfjfajjd00a", "Tuma"))
        coEvery { coursesRepository.getCourses("1234") } returns Response.success(courseList)
        runBlocking {
            val response = coursesRepository.getCourses("1234")
            assertThat(response.body()).isEqualTo(courseList)
        }
    }

    @Test
    fun testGetCourseFromDb() {
        val courseList = listOf(Course("892", "HDJJ2", "Mathematics", "jkjfjfajjd00a", "Tuma"))
        every { coursesRepository.getCoursesFromDb() } returns MutableLiveData(courseList)
        assertThat(coursesRepository.getCoursesFromDb().getOrAwait()).isEqualTo(courseList)
    }
}
