package com.example.studentregistration.repository

import androidx.lifecycle.LiveData
import com.example.studentregistration.CodeHiveApp
import com.example.studentregistration.api.ApiInterface
import com.example.studentregistration.database.CodeHiveDatabase
import com.example.studentregistration.database.CoursesDao
import com.example.studentregistration.models.Course
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class CoursesRepository @Inject constructor(var service:ApiInterface,val coursesDao: CoursesDao) {
    val db = CodeHiveDatabase.getDatabase(CodeHiveApp.appContext)

    suspend fun getCourses(access_token:String):Response<List<Course>> =
        withContext(Dispatchers.IO){
            val response =  service.getCourses(access_token)
            response.body()?.forEach { course ->
                coursesDao.insertCourse(course)
            }
            return@withContext response
        }

    fun getCoursesFromDb(): LiveData<List<Course>> {
        return db.getCourseDao().getCourses()
    }
}