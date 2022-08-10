package com.example.app41

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.app41.models.News

@Database(entities = [News::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}