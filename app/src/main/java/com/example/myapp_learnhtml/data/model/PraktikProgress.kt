package com.example.myapp_learnhtml.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "praktik_progress")
data class PraktikProgress(
    @PrimaryKey val topikIndex: Int,
    val isCompleted: Boolean = false
)
