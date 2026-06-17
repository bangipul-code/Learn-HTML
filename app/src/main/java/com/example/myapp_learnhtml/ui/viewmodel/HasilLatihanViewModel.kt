package com.example.myapp_learnhtml.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myapp_learnhtml.data.model.latihanItemsStatic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HasilLatihanViewModel(application: Application) : AndroidViewModel(application) {

    private val _result = MutableStateFlow<LatihanResult?>(null)
    val result: StateFlow<LatihanResult?> = _result.asStateFlow()

    fun load(topicIndex: Int) {
        _result.value = LatihanResultStore.get(topicIndex) ?: LatihanResult(
            topicIndex = topicIndex,
            title = latihanItemsStatic.getOrNull(topicIndex)?.title ?: "Latihan",
            score = 0,
            correctCount = 0,
            totalQuestions = 0,
            reviewItems = emptyList()
        )
    }
}
