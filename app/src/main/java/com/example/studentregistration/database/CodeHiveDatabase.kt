package com.example.studentregistration.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.studentregistration.models.Course

@Database(entities = arrayOf(Course::class), version = 1, exportSchema = false)
abstract class CodeHiveDatabase:RoomDatabase(){
    abstract fun getCourseDao(): CoursesDao

    //a singleton, a single instance at a time
    companion object{
        private var database: CodeHiveDatabase? = null

        fun getDatabase(context: Context): CodeHiveDatabase {
            if(database == null){
                database = Room.databaseBuilder(context, CodeHiveDatabase::class.java, "codehive-db")
                    .fallbackToDestructiveMigration().build()
            }
            return  database as CodeHiveDatabase
        }
    }

}