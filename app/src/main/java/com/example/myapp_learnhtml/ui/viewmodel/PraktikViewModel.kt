package com.example.myapp_learnhtml.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp_learnhtml.data.local.MateriDatabase
import com.example.myapp_learnhtml.data.model.PraktikItem
import com.example.myapp_learnhtml.data.model.praktikItemsStatic
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

data class PraktikListUiState(
    val items: List<PraktikItem> = praktikItemsStatic,
    val completedCount: Int = 0
)

class PraktikViewModel(application: Application) : AndroidViewModel(application) {

    private val materiDao = MateriDatabase.getInstance(application).materiDao()
    private val praktikDao = MateriDatabase.getInstance(application).praktikDao()

    val uiState: StateFlow<PraktikListUiState> = combine(
        materiDao.observeAllProgress(),
        praktikDao.observePraktikProgress()
    ) { materiProgress, praktikProgress ->
        val completedMateri = materiProgress.filter { it.isCompleted }.map { it.materiIndex }.toSet()
        val maxCompletedMateri = completedMateri.maxOrNull() ?: -1
        val completedPraktik = praktikProgress.filter { it.isCompleted }.map { it.topikIndex }.toSet()

        val items = praktikItemsStatic.mapIndexed { index, item ->
            item.copy(
                isUnlocked = index == 0 || index <= maxCompletedMateri + 1,
                isCompleted = completedPraktik.contains(index)
            )
        }

        PraktikListUiState(
            items = items,
            completedCount = items.count { it.isCompleted }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = PraktikListUiState()
    )
}
