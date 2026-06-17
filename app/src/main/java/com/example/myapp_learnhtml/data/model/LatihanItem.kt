package com.example.myapp_learnhtml.data.model

data class LatihanItem(
    val topicIndex: Int,
    val title: String,
    val description: String,
    val questionCount: Int = 5,
    val isUnlocked: Boolean = false,
    val bestScore: Int? = null,
    val correctCount: Int = 0,
    val isCompleted: Boolean = false
)

val latihanItemsStatic = materiItemsStatic.mapIndexed { index, materi ->
    LatihanItem(
        topicIndex = index,
        title = materi.title,
        description = "Latihan pilihan ganda untuk ${materi.title.lowercase()}",
        isUnlocked = index == 0
    )
}
