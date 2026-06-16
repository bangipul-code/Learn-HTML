package com.example.myapp_learnhtml.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapp_learnhtml.data.model.MateriPage
import com.example.myapp_learnhtml.data.model.MateriTopic

@Dao
interface MateriDao {

    @Query(value = "SELECT * FROM materi_topics ORDER BY id ASC")
    suspend fun getAllTopics(): List<MateriTopic>

    @Query(value = "SELECT * FROM materi_pages WHERE topicId = :topicId ORDER BY pageOrder ASC")
    suspend fun getPagesByTopic(topicId: Int): List<MateriPage>

    @Query(value = "SELECT * FROM materi_topics WHERE id = :topicId")
    suspend fun getTopicById(topicId: Int): MateriTopic?

    @Query(value = "SELECT COUNT(*) FROM materi_topics")
    suspend fun getTopicCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopic(topic: MateriTopic)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPages(pages: List<MateriPage>)
}
