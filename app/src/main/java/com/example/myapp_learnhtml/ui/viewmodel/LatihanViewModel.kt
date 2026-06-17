package com.example.myapp_learnhtml.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp_learnhtml.data.local.MateriDatabase
import com.example.myapp_learnhtml.data.local.SampleLatihanData
import com.example.myapp_learnhtml.data.model.LatihanItem
import com.example.myapp_learnhtml.data.model.latihanItemsStatic
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class LatihanListUiState(
    val items: List<LatihanItem> = latihanItemsStatic,
    val completedCount: Int = 0,
    val averageScore: Int = 0
)

class LatihanViewModel(application: Application) : AndroidViewModel(application) {

    private val database = MateriDatabase.getInstance(application)
    private val materiDao = database.materiDao()
    private val latihanDao = database.latihanDao()

    val uiState: StateFlow<LatihanListUiState> = combine(
        materiDao.observeAllProgress(),
        latihanDao.observeLatihanProgress()
    ) { materiProgress, latihanProgress ->
        val completedMateri = materiProgress.filter { it.isCompleted }.map { it.materiIndex }.toSet()
        val maxCompletedMateri = completedMateri.maxOrNull() ?: -1
        val progressByTopic = latihanProgress.associateBy { it.topikIndex }

        val items = latihanItemsStatic.mapIndexed { index, item ->
            val progress = progressByTopic[index]
            item.copy(
                isUnlocked = index == 0 || index <= maxCompletedMateri + 1,
                bestScore = progress?.skor,
                correctCount = progress?.jumlahBenar ?: 0,
                questionCount = progress?.jumlahSoal ?: item.questionCount,
                isCompleted = progress?.isCompleted == true
            )
        }

        val completed = items.count { it.isCompleted }
        val scoredItems = items.mapNotNull { it.bestScore }
        LatihanListUiState(
            items = items,
            completedCount = completed,
            averageScore = if (scoredItems.isEmpty()) 0 else scoredItems.average().toInt()
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = LatihanListUiState()
    )

    init {
        viewModelScope.launch {
            seedQuestionsIfNeeded()
        }
    }

    private suspend fun seedQuestionsIfNeeded() {
        if (latihanDao.getQuestionCount() == 0) {
            latihanDao.insertQuestions(SampleLatihanData.questions)
        }
    }
}
