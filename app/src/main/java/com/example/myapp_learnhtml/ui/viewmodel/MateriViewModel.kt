package com.example.myapp_learnhtml.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp_learnhtml.data.local.MateriDatabase
import com.example.myapp_learnhtml.data.model.MateriItem
import com.example.myapp_learnhtml.data.model.materiItemsStatic
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MateriViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = MateriDatabase.getInstance(application).materiDao()

    val materiItems: StateFlow<List<MateriItem>> = dao.observeAllProgress()
        .map { progressList ->
            val completedSet = progressList.filter { it.isCompleted }.map { it.materiIndex }.toSet()
            val maxCompletedIndex = if (completedSet.isEmpty()) -1 else completedSet.max()

            materiItemsStatic.mapIndexed { index, item ->
                item.copy(
                    isCompleted = completedSet.contains(index),
                    isUnlocked = index == 0 || index <= maxCompletedIndex + 1
                )
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = materiItemsStatic
        )
}
