package com.example.myapp_learnhtml.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp_learnhtml.data.local.MateriDatabase
import com.example.myapp_learnhtml.data.model.materiItemsStatic
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

data class HomeUiState(
    val completedMateri: Int = 0,
    val completedLatihan: Int = 0,
    val completedPraktik: Int = 0,
    val totalTopics: Int = 10,
    val nextMateriTitle: String = "Struktur HTML dan tag dasar"
) {
    val totalCompleted: Int = completedMateri + completedLatihan + completedPraktik
    val progress: Float = if (totalTopics > 0) totalCompleted.toFloat() / (totalTopics * 3).toFloat() else 0f
}

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val database = MateriDatabase.getInstance(application)
    private val materiDao = database.materiDao()
    private val latihanDao = database.latihanDao()
    private val praktikDao = database.praktikDao()

    val uiState: StateFlow<HomeUiState> = combine(
        materiDao.observeAllProgress(),
        latihanDao.observeLatihanProgress(),
        praktikDao.observePraktikProgress()
    ) { materiProgress, latihanProgress, praktikProgress ->
        val completedMateri = materiProgress.count { it.isCompleted }
        val completedLatihan = latihanProgress.count { it.isCompleted }
        val completedPraktik = praktikProgress.count { it.isCompleted }
        
        // Find next materi
        val completedMateriIndices = materiProgress.filter { it.isCompleted }.map { it.materiIndex }.toSet()
        val nextMateriIndex = (0 until materiItemsStatic.size).firstOrNull { it !in completedMateriIndices } ?: 0
        val nextMateriTitle = if (completedMateriIndices.size == materiItemsStatic.size) {
            "Semua materi selesai!"
        } else {
            materiItemsStatic.getOrNull(nextMateriIndex)?.title ?: "Selesai semua!"
        }

        HomeUiState(
            completedMateri = completedMateri,
            completedLatihan = completedLatihan,
            completedPraktik = completedPraktik,
            totalTopics = materiItemsStatic.size,
            nextMateriTitle = nextMateriTitle
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeUiState()
    )
}
