package com.example.myapp_learnhtml.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp_learnhtml.data.local.MateriDatabase
import com.example.myapp_learnhtml.data.local.SamplePraktikData
import com.example.myapp_learnhtml.data.model.PraktikProgress
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class DetailPraktikUiState(
    val title: String = "",
    val langkah: List<String> = emptyList(),
    val kode: String = "",
    val kodeAwal: String = "",
    val kriteriaSelesai: List<String> = emptyList(),
    val hasRun: Boolean = false,
    val isCompleted: Boolean = false,
    val isLoading: Boolean = true
)

class DetailPraktikViewModel(application: Application) : AndroidViewModel(application) {

    private val praktikDao = MateriDatabase.getInstance(application).praktikDao()

    private val _uiState = MutableStateFlow(DetailPraktikUiState())
    val uiState: StateFlow<DetailPraktikUiState> = _uiState.asStateFlow()

    private var loadedTopicIndex: Int? = null

    fun load(topicIndex: Int) {
        if (loadedTopicIndex == topicIndex) return
        loadedTopicIndex = topicIndex

        viewModelScope.launch {
            val exercise = SamplePraktikData.exercises.getOrNull(topicIndex)
            val progress = praktikDao.getPraktikProgress(topicIndex)

            _uiState.value = DetailPraktikUiState(
                title = exercise?.let { SamplePraktikData.exercises.indexOf(it) }?.let {
                    com.example.myapp_learnhtml.data.model.praktikItemsStatic.getOrNull(it)?.title
                } ?: "Praktik",
                langkah = exercise?.langkah ?: emptyList(),
                kode = exercise?.kodeAwal ?: "",
                kodeAwal = exercise?.kodeAwal ?: "",
                kriteriaSelesai = exercise?.kriteriaSelesai ?: emptyList(),
                isCompleted = progress?.isCompleted == true,
                isLoading = false
            )
        }
    }

    fun updateKode(newKode: String) {
        _uiState.value = _uiState.value.copy(kode = newKode)
    }

    fun resetKode() {
        _uiState.value = _uiState.value.copy(
            kode = _uiState.value.kodeAwal,
            hasRun = false
        )
    }

    fun runCode() {
        _uiState.value = _uiState.value.copy(hasRun = true)
        checkCompletion()
    }

    private fun checkCompletion() {
        val state = _uiState.value
        if (!state.hasRun || state.isCompleted) return

        val codeLower = state.kode.lowercase()
        val allCriteriaMet = state.kriteriaSelesai.all { criteria ->
            codeLower.contains(criteria.lowercase())
        }

        if (allCriteriaMet) {
            _uiState.value = state.copy(isCompleted = true)
            viewModelScope.launch {
                praktikDao.upsertPraktikProgress(
                    PraktikProgress(
                        topikIndex = loadedTopicIndex ?: return@launch,
                        isCompleted = true
                    )
                )
            }
        }
    }
}
