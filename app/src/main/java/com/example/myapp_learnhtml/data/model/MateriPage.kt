package com.example.myapp_learnhtml.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "materi_pages",
    foreignKeys = [
        ForeignKey(
            entity = MateriTopic::class,
            parentColumns = ["id"],
            childColumns = ["topicId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("topicId")]
)
data class MateriPage(
    @PrimaryKey val id: Int,
    val topicId: Int,
    val pageOrder: Int,
    val judul: String,
    val deskripsi: String,
    val analogi: String,
    val contohKode: String,
    val hasilDiWeb: String,
    val glosariumJson: String
)
