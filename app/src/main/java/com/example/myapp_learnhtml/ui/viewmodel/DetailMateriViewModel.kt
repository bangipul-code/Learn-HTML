package com.example.myapp_learnhtml.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp_learnhtml.data.local.Converters
import com.example.myapp_learnhtml.data.local.MateriDatabase
import com.example.myapp_learnhtml.data.local.SampleData
import com.example.myapp_learnhtml.data.model.GlosariumItem
import com.example.myapp_learnhtml.data.model.MateriPage
import com.example.myapp_learnhtml.data.model.MateriTopic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class DetailMateriViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = MateriDatabase.getInstance(application).materiDao()
    private val converters = Converters()

    private val _topics = MutableStateFlow<List<MateriTopic>>(emptyList())
    val topics: StateFlow<List<MateriTopic>> = _topics.asStateFlow()

    private val _currentTopic = MutableStateFlow<MateriTopic?>(null)
    val currentTopic: StateFlow<MateriTopic?> = _currentTopic.asStateFlow()

    private val _pages = MutableStateFlow<List<MateriPage>>(emptyList())
    val pages: StateFlow<List<MateriPage>> = _pages.asStateFlow()

    private val _currentPageIndex = MutableStateFlow(0)
    val currentPageIndex: StateFlow<Int> = _currentPageIndex.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private var hasInitialized = false

    fun loadTopic(topicId: Int) {
        if (hasInitialized) return
        hasInitialized = true

        viewModelScope.launch {
            try {
                if (dao.getTopicCount() == 0) {
                    seedDatabase()
                }

                val allTopics = dao.getAllTopics()
                _topics.value = allTopics

                val targetTopic = allTopics.find { it.id == topicId } ?: allTopics.firstOrNull()
                _currentTopic.value = targetTopic

                targetTopic?.let {
                    val topicPages = dao.getPagesByTopic(it.id)
                    _pages.value = topicPages
                    _currentPageIndex.value = 0
                }

                _isLoading.value = false
            } catch (e: IOException) {
                _isLoading.value = false
            }
        }
    }

    private suspend fun seedDatabase() {
        for (topic in SampleData.topics) {
            dao.insertTopic(topic)
        }
        dao.insertPages(SampleData.pages)
    }

    fun goToNextPage() {
        val currentIndex = _currentPageIndex.value
        if (currentIndex < _pages.value.lastIndex) {
            _currentPageIndex.value = currentIndex + 1
        }
    }

    fun goToPreviousPage() {
        val currentIndex = _currentPageIndex.value
        if (currentIndex > 0) {
            _currentPageIndex.value = currentIndex - 1
        }
    }

    fun jumpToPage(index: Int) {
        if (index in _pages.value.indices) {
            _currentPageIndex.value = index
        }
    }

    fun getGlosariumList(page: MateriPage): List<GlosariumItem> {
        return converters.toGlosariumList(page.glosariumJson)
    }

    fun getPageCount(): Int = _pages.value.size

    fun markMateriCompleted(materiIndex: Int) {
        viewModelScope.launch {
            dao.upsertProgress(
                com.example.myapp_learnhtml.data.model.MateriProgress(
                    materiIndex = materiIndex,
                    isCompleted = true
                )
            )
        }
    }
}
