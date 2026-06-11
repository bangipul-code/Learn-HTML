package com.example.myapp_learnhtml

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapp_learnhtml.ui.theme.Myapp_learnHTMLTheme

@Composable
fun LoginScreen2(
    onLoginClick: (String) -> Unit = {},
    onExploreClick: () -> Unit = {},
) {
    var name by remember { mutableStateOf(value = "") }
    val canContinue = name.isNotBlank()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // // Ornamen lingkaran di pojok kanan atas
            // Box(
            //     modifier = Modifier
            //         .size(240.dp)
            //         .align(Alignment.TopEnd)
            //         .background(
            //             color = MaterialTheme
            //                 .colorScheme
            //                 .surfaceVariant
            //                 .copy(alpha = 0.5f),
            //             shape = CircleShape
            //         )
            // )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    BrandHeader()

                    Spacer(modifier = Modifier.height(36.dp))

                    Text(
                        text = "Mulai belajar HTML dari dasar.",
                        fontSize = 34.sp,
                        lineHeight = 40.sp,
                        fontWeight = FontWeight.ExtraBold,
                    )
                    Text(
                        text = "Ikuti materi singkat, latihan langsung, dan pantau progres belajar tanpa perlu setup rumit.",
                        fontSize = 15.sp,
                        lineHeight = 23.sp,
                        modifier = Modifier.padding(top = 14.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OnboardingBenefit(
                            icon = Icons.Filled.Code,
                            title = "Materi bertahap",
                            description = "Belajar tag, struktur halaman, hingga praktik sederhana."
                        )
                        OnboardingBenefit(
                            icon = Icons.Filled.PlayArrow,
                            title = "Praktik cepat",
                            description = "Langsung coba konsep yang baru dipelajari."
                        )
                        OnboardingBenefit(
                            icon = Icons.Filled.Verified,
                            title = "Progress jelas",
                            description = "Lanjutkan pembelajaran dari posisi terakhir."
                        )
                    }
                }

                LoginCard(
                    name = name,
                    onNameChange = { name = it },
                    canContinue = canContinue,
                    onLoginClick = { onLoginClick(name.trim()) },
                    onExploreClick = onExploreClick,
                    modifier = Modifier.padding(top = 32.dp)
                )
            }
        }
    }
}

@Composable
private fun BrandHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(shape = RoundedCornerShape(size = 16.dp)),
                // .background(color = MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "<>",
                fontSize = 22.sp,
                fontWeight = FontWeight.Black,
                // color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        Column {
            Text(
                text = "FastHTML",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Belajar coding web",
                fontSize = 13.sp
            )
        }
    }
}

@Composable
private fun OnboardingBenefit(
    icon: ImageVector,
    title: String,
    description: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(size = 20.dp))
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .padding(all = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(size = 14.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.size(22.dp)
            )
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = description,
                fontSize = 12.sp,
                lineHeight = 17.sp,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}

@Composable
private fun LoginCard(
    name: String,
    onNameChange: (String) -> Unit,
    canContinue: Boolean,
    onLoginClick: () -> Unit,
    onExploreClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(size = 28.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Column(
            modifier = Modifier.padding(all = 22.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Masuk ke kelas",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Masukkan nama agar progress belajar bisa dipersonalisasi.",
                    fontSize = 13.sp,
                    lineHeight = 19.sp
                )
            }

            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Nama kamu") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = null
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(size = 18.dp),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Done
                )
            )

            Button(
                onClick = onLoginClick,
                enabled = canContinue,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(size = 18.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Login,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Mulai Belajar",
                    fontWeight = FontWeight.Bold
                )
            }

            OutlinedButton(
                onClick = onExploreClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(size = 16.dp)
            ) {
                Text(
                    text = "Lihat Materi Dulu",
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    Myapp_learnHTMLTheme {
        LoginScreen2()
    }
}