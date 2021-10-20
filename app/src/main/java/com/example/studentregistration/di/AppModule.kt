package com.example.studentregistration.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.studentregistration.CodeHiveApp
import com.example.studentregistration.api.ApiInterface
import com.example.studentregistration.database.CodeHiveDatabase
import com.example.studentregistration.database.CoursesDao
import com.example.studentregistration.repository.CoursesRepository
import com.example.studentregistration.repository.EnrolCourseRepo
import com.example.studentregistration.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

        //    bindings a functions that knows how to give you instances of your dependencies
        @Singleton
        @Provides
        fun provideRetrofit(client: OkHttpClient): Retrofit {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://13.244.243.129")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit
        }

        @Provides
        fun provideOkHttpClient(): OkHttpClient {
            val client = OkHttpClient.Builder()
                .addInterceptor(
                    ChuckerInterceptor(
                        CodeHiveApp.appContext
                    )
                )
                .build()
            return client
        }

        @Provides
        fun provideService(retrofit: Retrofit): ApiInterface {
            return retrofit.create(ApiInterface::class.java)
        }

        @Singleton
        @Provides
        fun provideDatabase(@ApplicationContext appContext: Context):
                CodeHiveDatabase {
            return CodeHiveDatabase.getDatabase(appContext)
        }

        @Singleton
        @Provides
        fun provideCoursesDao(database: CodeHiveDatabase): CoursesDao {
            return database.getCourseDao()
        }

        @Singleton
        @Provides
        fun provideCourseRepository(service: ApiInterface, coursesDao: CoursesDao): CoursesRepository {
            return CoursesRepository(service,coursesDao)
        }

        @Singleton
        @Provides
        fun provideUserRepository(service: ApiInterface): UserRepository {
            return UserRepository(service)
        }

        @Singleton
        @Provides
        fun provideEnrolRepository(service: ApiInterface): EnrolCourseRepo {
            return EnrolCourseRepo(service)
        }
    }

