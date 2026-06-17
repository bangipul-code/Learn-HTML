package com.example.myapp_learnhtml.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapp_learnhtml.data.model.PraktikProgress
import kotlinx.coroutines.flow.Flow

@Dao
interface PraktikDao {

    @Query("SELECT * FROM praktik_progress ORDER BY topikIndex ASC")
    fun observePraktikProgress(): Flow<List<PraktikProgress>>

    @Query("SELECT * FROM praktik_progress WHERE topikIndex = :topicIndex")
    suspend fun getPraktikProgress(topicIndex: Int): PraktikProgress?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertPraktikProgress(progress: PraktikProgress)
}
