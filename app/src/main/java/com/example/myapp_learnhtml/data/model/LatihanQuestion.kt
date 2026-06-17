package com.example.myapp_learnhtml.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "latihan_questions")
data class LatihanQuestion(
    @PrimaryKey val id: Int,
    val topicId: Int,
    val pertanyaan: String,
    val opsi: List<String>,
    val jawabanBenar: Int
)
