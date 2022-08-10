package com.example.app41

import androidx.room.Dao
import androidx.room.Query
import com.example.app41.models.News

@Dao
interface NewsDao {
    @Query("SELECT*FROM news WHERE title LIKE '%'||:search||'%'")
        fun search(search:String):List<News>

}