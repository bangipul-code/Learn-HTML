package com.example.myapp_learnhtml.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapp_learnhtml.ui.viewmodel.HasilLatihanViewModel
import com.example.myapp_learnhtml.ui.viewmodel.LatihanResult
import com.example.myapp_learnhtml.ui.viewmodel.LatihanReviewItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HasilLatihanScreen(
    topicIndex: Int,
    navController: NavController,
    viewModel: HasilLatihanViewModel = viewModel()
) {
    val result by viewModel.result.collectAsState()

    LaunchedEffect(topicIndex) {
        viewModel.load(topicIndex)
    }

    val safeResult = result

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Hasil Latihan",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold
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
        }
    ) { innerPadding ->
        if (safeResult == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Memuat hasil...")
            }
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ScoreCard(result = safeResult)
            Text(
                text = "Review Jawaban",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            if (safeResult.reviewItems.isEmpty()) {
                Text(
                    text = "Review jawaban hanya tersedia setelah menyelesaikan latihan dari halaman quiz.",
                    style = MaterialTheme.typography.bodyMedium
                )
            } else {
                safeResult.reviewItems.forEachIndexed { index, item ->
                    ReviewCard(number = index + 1, item = item)
                }
            }
            Button(
                onClick = {
                    navController.navigate("latihan") {
                        popUpTo("latihan") { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(size = 10.dp)
            ) {
                Text(text = "Kembali ke Daftar Latihan")
            }
            OutlinedButton(
                onClick = {
                    navController.navigate("detail_latihan/$topicIndex") {
                        popUpTo("latihan")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(size = 10.dp)
            ) {
                Text(text = "Ulangi Latihan")
            }
        }
    }
}

@Composable
private fun ScoreCard(result: LatihanResult) {
    val progress = if (result.totalQuestions == 0) 0f else result.correctCount.toFloat() / result.totalQuestions

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(size = 22.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 22.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = result.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = result.score.toString(),
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${result.correctCount} dari ${result.totalQuestions} jawaban benar",
                style = MaterialTheme.typography.bodyLarge
            )
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun ReviewCard(
    number: Int,
    item: LatihanReviewItem
) {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(size = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    imageVector = if (item.isCorrect) Icons.Filled.CheckCircle else Icons.Filled.Close,
                    contentDescription = null,
                    tint = if (item.isCorrect) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                )
                Text(
                    text = "$number. ${item.question.pertanyaan}",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Text(
                text = "Jawaban kamu: ${item.selectedAnswer?.let { item.question.opsi[it] } ?: "Tidak dijawab"}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Jawaban benar: ${item.question.opsi[item.question.jawabanBenar]}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
