package com.example.myapp_learnhtml

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private data class MateriItem(
    val title: String,
    val description: String,
    val duration: String,
    val isUnlocked: Boolean,
    val isCompleted: Boolean = false
)

private val materiItems = listOf(
    MateriItem(
        title = "Pengenalan HTML",
        description = "Mengenal Fungsi HTML dan Struktur Dasar Halaman Web",
        duration = "8 menit",
        isUnlocked = true,
        isCompleted = true
    ),
    MateriItem(
        title = "Struktur Dokumen",
        description = "Mengenal Struktur Utama: doctype, html, head, dan body",
        duration = "10 menit",
        isUnlocked = true,
        isCompleted = true
    ),
    MateriItem(
        title = "Heading dan Paragraf",
        description = "Susun konten teks yang mudah dibaca.",
        duration = "7 menit",
        isUnlocked = true
    ),
    MateriItem(
        title = "Link dan Navigasi",
        description = "Buat hubungan antarhalaman dengan anchor.",
        duration = "9 menit",
        isUnlocked = true
    ),
    MateriItem(
        title = "Gambar dan Atribut",
        description = "Gunakan gambar dengan atribut yang jelas.",
        duration = "11 menit",
        isUnlocked = true
    ),
    MateriItem(
        title = "List dan Tabel",
        description = "Tampilkan data terstruktur secara rapi.",
        duration = "12 menit",
        isUnlocked = false
    ),
    MateriItem(
        title = "Form Dasar",
        description = "Kenali input, label, dan validasi sederhana.",
        duration = "15 menit",
        isUnlocked = false
    ),
    MateriItem(
        title = "Semantic HTML",
        description = "Gunakan tag semantik untuk aksesibilitas.",
        duration = "13 menit",
        isUnlocked = false
    ),
    MateriItem(
        title = "Media HTML",
        description = "Tambahkan audio dan video dengan kontrol yang tepat.",
        duration = "10 menit",
        isUnlocked = false
    ),
    MateriItem(
        title = "Mini Project",
        description = "Gabungkan konsep utama menjadi halaman profil.",
        duration = "20 menit",
        isUnlocked = false
    )
)

@Preview(showBackground = true)
@Composable
fun MateriBelajar() {
    val unlockedCount = materiItems.count { it.isUnlocked }
    val completedCount = materiItems.count { it.isCompleted }
    val progress = completedCount.toFloat() / materiItems.size

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            MateriHeader()
        }

        item {
            ProgressSummary(
                completedCount = completedCount,
                totalCount = materiItems.size,
                progress = progress
            )
        }

        item {
            UnlockSummary(
                unlockedCount = unlockedCount,
                lockedCount = materiItems.size - unlockedCount
            )
        }

        item {
            SectionTitle(
                title = "Dasar-Dasar HTML",
                subtitle = "Mulai dari materi yang terbuka, lalu lanjutkan setelah akses dibuka."
            )
        }

        itemsIndexed(materiItems) { index, item ->
            MateriCard(
                number = index + 1,
                item = item
            )
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun MateriHeader() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Fast HTML",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Belajar cepat HTML lewat urutan materi yang jelas dan bertahap.",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun ProgressSummary(
    completedCount: Int,
    totalCount: Int,
    progress: Float
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Progres belajar",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "$completedCount dari $totalCount materi selesai",
                        style = MaterialTheme.typography.bodyMedium
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
        }
    }
}

@Composable
private fun UnlockSummary(
    unlockedCount: Int,
    lockedCount: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.size(48.dp),
                    shape = RoundedCornerShape(16.dp),
                    tonalElevation = 1.dp
                ) {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = null,
                        modifier = Modifier.padding(12.dp)
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "$unlockedCount materi terbuka",
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.38f),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "$lockedCount materi lanjutan masih terkunci. Buka akses penuh untuk melanjutkan.",
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.38f),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            OutlinedButton(
                enabled = false,
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Buka akses")
            }
        }
    }
}

@Composable
private fun SectionTitle(
    title: String,
    subtitle: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
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
private fun MateriCard(
    number: Int,
    item: MateriItem
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(size = 18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.Top
            ) {
                Surface(
                    modifier = Modifier.size(44.dp),
                    shape = RoundedCornerShape(14.dp),
                    tonalElevation = 1.dp
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = number.toString().padStart(2, '0'),
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = item.description,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = "${itemStatus(item)} - ${item.duration}",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }

            if (item.isUnlocked) {
                Button(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = if (item.isCompleted) Icons.Filled.Repeat else Icons.Filled.PlayArrow,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = if (item.isCompleted) "Ulangi" else "Mulai")
                }
            } else {
                OutlinedButton(
                    onClick = { },
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Terkunci")
                }
            }
        }
    }
}

private fun itemStatus(item: MateriItem): String {
    return when {
        item.isCompleted -> "Selesai"
        item.isUnlocked -> "Tersedia"
        else -> "Terkunci"
    }
}
