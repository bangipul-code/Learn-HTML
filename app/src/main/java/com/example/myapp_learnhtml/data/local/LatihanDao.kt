package com.example.myapp_learnhtml.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapp_learnhtml.data.model.LatihanProgress
import com.example.myapp_learnhtml.data.model.LatihanQuestion
import kotlinx.coroutines.flow.Flow

@Dao
interface LatihanDao {

    @Query("SELECT * FROM latihan_progress ORDER BY topikIndex ASC")
    fun observeLatihanProgress(): Flow<List<LatihanProgress>>

    @Query("SELECT * FROM latihan_progress WHERE topikIndex = :topicIndex")
    suspend fun getLatihanProgress(topicIndex: Int): LatihanProgress?

    @Query("SELECT * FROM latihan_questions WHERE topicId = :topicId ORDER BY id ASC")
    fun getQuestionsByTopic(topicId: Int): Flow<List<LatihanQuestion>>

    @Query("SELECT COUNT(*) FROM latihan_questions")
    suspend fun getQuestionCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(questions: List<LatihanQuestion>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertLatihanProgress(progress: LatihanProgress)
}
