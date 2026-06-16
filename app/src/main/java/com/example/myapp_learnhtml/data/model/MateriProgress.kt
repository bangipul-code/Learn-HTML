package com.example.myapp_learnhtml.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "materi_progress")
data class MateriProgress(
    @PrimaryKey val materiIndex: Int,
    val isCompleted: Boolean = false
)
