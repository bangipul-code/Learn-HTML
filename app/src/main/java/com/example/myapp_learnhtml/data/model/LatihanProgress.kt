package com.example.myapp_learnhtml.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "latihan_progress")
data class LatihanProgress(
    @PrimaryKey val topikIndex: Int,
    val skor: Int,
    val jumlahBenar: Int,
    val jumlahSoal: Int,
    val isCompleted: Boolean
)
