package com.example.myapp_learnhtml.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp_learnhtml.data.local.MateriDatabase
import com.example.myapp_learnhtml.data.local.SampleLatihanData
import com.example.myapp_learnhtml.data.model.LatihanProgress
import com.example.myapp_learnhtml.data.model.LatihanQuestion
import com.example.myapp_learnhtml.data.model.latihanItemsStatic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class LatihanReviewItem(
    val question: LatihanQuestion,
    val selectedAnswer: Int?
) {
    val isCorrect: Boolean = selectedAnswer == question.jawabanBenar
}

data class LatihanResult(
    val topicIndex: Int,
    val title: String,
    val score: Int,
    val correctCount: Int,
    val totalQuestions: Int,
    val reviewItems: List<LatihanReviewItem>
)

object LatihanResultStore {
    private val results = mutableMapOf<Int, LatihanResult>()

    fun save(result: LatihanResult) {
        results[result.topicIndex] = result
    }

    fun get(topicIndex: Int): LatihanResult? = results[topicIndex]
}

data class DetailLatihanUiState(
    val title: String = "",
    val questions: List<LatihanQuestion> = emptyList(),
    val currentIndex: Int = 0,
    val selectedAnswer: Int? = null,
    val answers: Map<Int, Int> = emptyMap(),
    val isSubmitted: Boolean = false,
    val isLoading: Boolean = true
) {
    val currentQuestion: LatihanQuestion? = questions.getOrNull(currentIndex)
    val totalQuestions: Int = questions.size
    val progress: Float = if (questions.isEmpty()) 0f else (currentIndex + 1).toFloat() / questions.size
    val isLastQuestion: Boolean = currentIndex == questions.lastIndex
    val canSubmit: Boolean = selectedAnswer != null && !isSubmitted
    val submittedAnswerIsCorrect: Boolean
        get() = currentQuestion?.let { selectedAnswer == it.jawabanBenar } == true
}

class DetailLatihanViewModel(application: Application) : AndroidViewModel(application) {

    private val latihanDao = MateriDatabase.getInstance(application).latihanDao()

    private val _uiState = MutableStateFlow(DetailLatihanUiState())
    val uiState: StateFlow<DetailLatihanUiState> = _uiState.asStateFlow()

    private var loadedTopicIndex: Int? = null

    fun load(topicIndex: Int) {
        if (loadedTopicIndex == topicIndex) return
        loadedTopicIndex = topicIndex

        viewModelScope.launch {
            if (latihanDao.getQuestionCount() == 0) {
                latihanDao.insertQuestions(SampleLatihanData.questions)
            }

            val questions = latihanDao.getQuestionsByTopic(topicIndex).first()
            _uiState.value = DetailLatihanUiState(
                title = latihanItemsStatic.getOrNull(topicIndex)?.title ?: "Latihan",
                questions = questions,
                isLoading = false
            )
        }
    }

    fun selectAnswer(answerIndex: Int) {
        val state = _uiState.value
        if (state.isSubmitted) return
        _uiState.value = state.copy(selectedAnswer = answerIndex)
    }

    fun submitAnswer() {
        val state = _uiState.value
        val selected = state.selectedAnswer ?: return
        _uiState.value = state.copy(
            answers = state.answers + (state.currentIndex to selected),
            isSubmitted = true
        )
    }

    fun moveToNextQuestion() {
        val state = _uiState.value
        if (state.currentIndex >= state.questions.lastIndex) return

        val nextIndex = state.currentIndex + 1
        _uiState.value = state.copy(
            currentIndex = nextIndex,
            selectedAnswer = state.answers[nextIndex],
            isSubmitted = false
        )
    }

    fun finish(topicIndex: Int): LatihanResult {
        val state = _uiState.value
        val reviewItems = state.questions.mapIndexed { index, question ->
            LatihanReviewItem(question = question, selectedAnswer = state.answers[index])
        }
        val correctCount = reviewItems.count { it.isCorrect }
        val score = if (reviewItems.isEmpty()) 0 else (correctCount * 100) / reviewItems.size
        val result = LatihanResult(
            topicIndex = topicIndex,
            title = state.title,
            score = score,
            correctCount = correctCount,
            totalQuestions = reviewItems.size,
            reviewItems = reviewItems
        )

        LatihanResultStore.save(result)
        viewModelScope.launch {
            val previous = latihanDao.getLatihanProgress(topicIndex)
            val bestScore = maxOf(previous?.skor ?: 0, score)
            val bestCorrect = if (bestScore == score) correctCount else previous?.jumlahBenar ?: correctCount
            latihanDao.upsertLatihanProgress(
                LatihanProgress(
                    topikIndex = topicIndex,
                    skor = bestScore,
                    jumlahBenar = bestCorrect,
                    jumlahSoal = reviewItems.size,
                    isCompleted = true
                )
            )
        }

        return result
    }
}
