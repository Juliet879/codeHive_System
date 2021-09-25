package com.example.studentregistration.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.studentregistration.models.Course

@Database(entities = arrayOf(Course::class), version = 1)
abstract class CodeHiveDatabase: RoomDatabase() {
}