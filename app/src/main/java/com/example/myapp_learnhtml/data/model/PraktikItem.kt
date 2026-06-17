package com.example.myapp_learnhtml.data.model

data class PraktikItem(
    val topicIndex: Int,
    val title: String,
    val description: String,
    val isUnlocked: Boolean = false,
    val isCompleted: Boolean = false
)

val praktikItemsStatic = materiItemsStatic.mapIndexed { index, materi ->
    PraktikItem(
        topicIndex = index,
        title = materi.title,
        description = "Praktik langsung: ${materi.title.lowercase()}",
        isUnlocked = index == 0
    )
}
