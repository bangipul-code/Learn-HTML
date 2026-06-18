package com.example.myapp_learnhtml.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.myapp_learnhtml.ui.viewmodel.HomeViewModel

private const val MATERI_ROUTE = "materi"
private const val PRAKTIK_ROUTE = "praktik"
private const val LATIHAN_ROUTE = "latihan"

@Composable
fun HomeScreen(
    userName: String = "Fauzi Taufiq",
    userAvatar: String = "",
    navController: NavHostController,
    viewModel: HomeViewModel = viewModel()
) {
    val firstName = userName.ifBlank { "Teman belajar" }.substringBefore(" ")
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
            item {
                HomeHeader(
                    userName = firstName,
                    userAvatar = userAvatar,
                    onOpenMateri = { navController.navigateSingleTop(MATERI_ROUTE) }
                )
            }

            item {
                LearningStatusCard(
                    completedLessons = uiState.totalCompleted,
                    totalLessons = uiState.totalTopics * 3,
                    nextLesson = uiState.nextMateriTitle
                )
            }

            item {
                ContinueLearningCard(
                    onContinue = { navController.navigateSingleTop(MATERI_ROUTE) },
                    onPractice = { navController.navigateSingleTop(PRAKTIK_ROUTE) }
                )
            }

            item {
                SectionTitle(
                    title = "Jalur belajar",
                    subtitle = "Pilih langkah yang paling relevan dengan kebutuhanmu."
                )
            }

            items(learningPaths) { path ->
                LearningPathRow(
                    path = path,
                    onClick = { navController.navigateSingleTop(path.route) }
                )
            }

            /* item {
                HelpAndRecoveryCard(
                    onOpenPractice = { navController.navigateSingleTop(PRAKTIK_ROUTE) },
                    onOpenQuiz = { navController.navigateSingleTop(LATIHAN_ROUTE) }
                )
            } */

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
}

@Composable
private fun HomeHeader(
    userName: String,
    userAvatar: String,
    onOpenMateri: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "Beranda",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Halo, $userName",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Lanjutkan dari progres terakhir atau buka materi yang ingin dipelajari.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            if (userAvatar.isNotBlank()) {
                Text(
                    text = userAvatar,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }

        OutlinedButton(
            onClick = onOpenMateri,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = "Lihat semua materi")
        }
    }
}

@Composable
private fun LearningStatusCard(
    completedLessons: Int,
    totalLessons: Int,
    nextLesson: String
) {
    val progress = completedLessons.toFloat() / totalLessons.toFloat()

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Status belajar",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "$completedLessons dari $totalLessons tugas selesai",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Text(
                    text = "${(progress * 100).toInt()}%",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )

            HorizontalDivider()

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "Materi berikutnya",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = nextLesson,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun ContinueLearningCard(
    onContinue: () -> Unit,
    onPractice: () -> Unit
) {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(size = 24.dp)
    ) {
        Column(
            modifier = Modifier.padding(all = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "Aksi utama",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Satu tombol utama dibuat jelas agar pengguna tidak perlu menebak langkah berikutnya.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Button(
                onClick = onContinue,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(size = 16.dp)
            ) {
                Text(text = "Lanjutkan belajar")
            }

            OutlinedButton(
                onClick = onPractice,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(size = 16.dp)
            ) {
                Text(text = "Praktikkan materi")
            }
        }
    }
}

@Composable
private fun SectionTitle(
    title: String,
    subtitle: String
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun LearningPathRow(
    path: LearningPath,
    onClick: () -> Unit
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = path.step,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = path.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = path.description,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Text(
                text = "Buka",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

/* @Composable
private fun HelpAndRecoveryCard(
    onOpenPractice: () -> Unit,
    onOpenQuiz: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(
                text = "Butuh validasi?",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Kalau belum yakin, coba praktik singkat atau kerjakan latihan untuk mengecek pemahaman.",
                style = MaterialTheme.typography.bodyMedium
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    enabled = false,
                    onClick = onOpenPractice,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(size = 16.dp)
                ) {
                    Text(text = "Praktik")
                }
                OutlinedButton(
                    enabled = false,
                    onClick = onOpenQuiz,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(size = 16.dp)
                ) {
                    Text(text = "Latihan")
                }
            }
        }
    }
} */

private fun NavHostController.navigateSingleTop(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

private data class LearningPath(
    val step: String,
    val title: String,
    val description: String,
    val route: String
)

private val learningPaths = listOf(
    LearningPath(
        step = "01",
        title = "Baca konsep inti",
        description = "Mulai dari struktur dokumen, tag, atribut, dan elemen umum.",
        route = MATERI_ROUTE
    ),
    LearningPath(
        step = "02",
        title = "Coba langsung",
        description = "Tulis kode HTML sederhana agar konsep berubah menjadi kebiasaan.",
        route = PRAKTIK_ROUTE
    ),
    LearningPath(
        step = "03",
        title = "Ukur pemahaman",
        description = "Kerjakan latihan singkat untuk melihat bagian yang perlu diulang.",
        route = LATIHAN_ROUTE
    )
)
