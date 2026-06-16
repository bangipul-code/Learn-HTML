package com.example.myapp_learnhtml.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "materi_topics")
data class MateriTopic(
    @PrimaryKey val id: Int,
    val title: String
)
