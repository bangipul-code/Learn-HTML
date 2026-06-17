package com.example.myapp_learnhtml.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapp_learnhtml.data.model.LatihanQuestion
import com.example.myapp_learnhtml.ui.viewmodel.DetailLatihanUiState
import com.example.myapp_learnhtml.ui.viewmodel.DetailLatihanViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailLatihanScreen(
    topicIndex: Int,
    navController: NavController,
    viewModel: DetailLatihanViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(topicIndex) {
        viewModel.load(topicIndex)
    }

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    val question = state.currentQuestion
    if (question == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Soal tidak ditemukan")
        }
        return
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = state.title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                }
            )
        },
        bottomBar = {
            Surface(modifier = Modifier.fillMaxWidth(), tonalElevation = 3.dp) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Soal ${state.currentIndex + 1} dari ${state.totalQuestions}",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                    Button(
                        onClick = {
                            if (!state.isSubmitted) {
                                viewModel.submitAnswer()
                            } else if (state.isLastQuestion) {
                                viewModel.finish(topicIndex)
                                navController.navigate("hasil_latihan/$topicIndex") {
                                    popUpTo("detail_latihan/$topicIndex") { inclusive = true }
                                }
                            } else {
                                viewModel.moveToNextQuestion()
                            }
                        },
                        enabled = state.canSubmit || state.isSubmitted,
                        shape = RoundedCornerShape(size = 10.dp)
                    ) {
                        Text(
                            text = when {
                                !state.isSubmitted -> "Submit"
                                state.isLastQuestion -> "Lihat Hasil"
                                else -> "Selanjutnya"
                            },
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProgressCard(state = state)
            QuestionCard(question = question)
            question.opsi.forEachIndexed { index, option ->
                AnswerOptionCard(
                    label = "${('A' + index)}.",
                    text = option,
                    selected = state.selectedAnswer == index,
                    isSubmitted = state.isSubmitted,
                    isCorrectAnswer = question.jawabanBenar == index,
                    onClick = { viewModel.selectAnswer(index) }
                )
            }
            FeedbackCard(state = state, question = question)
        }
    }
}

@Composable
private fun ProgressCard(state: DetailLatihanUiState) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(size = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Soal ${state.currentIndex + 1} dari ${state.totalQuestions}",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "${(state.progress * 100).toInt()}%",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )
            }
            LinearProgressIndicator(
                progress = { state.progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
        }
    }
}

@Composable
private fun QuestionCard(question: LatihanQuestion) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(size = 18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Text(
            text = question.pertanyaan,
            modifier = Modifier.padding(all = 20.dp),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun AnswerOptionCard(
    label: String,
    text: String,
    selected: Boolean,
    isSubmitted: Boolean,
    isCorrectAnswer: Boolean,
    onClick: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    val containerColor = when {
        isSubmitted && isCorrectAnswer -> colorScheme.primaryContainer
        isSubmitted && selected -> colorScheme.errorContainer
        selected -> colorScheme.secondaryContainer
        else -> colorScheme.surface
    }
    val borderColor = when {
        isSubmitted && isCorrectAnswer -> colorScheme.primary
        isSubmitted && selected -> colorScheme.error
        selected -> colorScheme.secondary
        else -> colorScheme.outlineVariant
    }

    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = !isSubmitted, onClick = onClick),
        shape = RoundedCornerShape(size = 16.dp),
        colors = CardDefaults.outlinedCardColors(containerColor = containerColor),
        border = BorderStroke(1.dp, borderColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Text(text = label, fontWeight = FontWeight.Bold)
            Text(
                text = text,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
private fun FeedbackCard(
    state: DetailLatihanUiState,
    question: LatihanQuestion
) {
    if (!state.isSubmitted) return

    val isCorrect = state.submittedAnswerIsCorrect
    val colorScheme = MaterialTheme.colorScheme
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(size = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isCorrect) colorScheme.primaryContainer else colorScheme.errorContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = if (isCorrect) Icons.Filled.CheckCircle else Icons.Filled.Close,
                contentDescription = null,
                tint = if (isCorrect) colorScheme.primary else colorScheme.error
            )
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = if (isCorrect) "Jawaban benar" else "Jawaban belum tepat",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Jawaban benar: ${question.opsi[question.jawabanBenar]}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Unspecified
                )
            }
        }
    }
}
