package com.example.myapp_learnhtml

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapp_learnhtml.ui.theme.Myapp_learnHTMLTheme

@Composable
fun LoginScreen(
    onLoginClick: (String) -> Unit = {},
    onExploreClick: () -> Unit = {},
) {
    var name by remember { mutableStateOf("") }
    val canContinue = name.isNotBlank()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF07111F),
                        Color(0xFF123A46),
                        Color(0xFFF7EFE2),
                    )
                )
            )
    ) {
        Box(
            modifier = Modifier
                .size(240.dp)
                .align(Alignment.TopEnd)
                .background(
                    color = Color(0x33F7A440),
                    shape = CircleShape
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                BrandHeader()

                Spacer(modifier = Modifier.height(36.dp))

                Text(
                    text = "Mulai belajar HTML dari dasar.",
                    color = Color.White,
                    fontSize = 34.sp,
                    lineHeight = 40.sp,
                    fontWeight = FontWeight.ExtraBold,
                )
                Text(
                    text = "Ikuti materi singkat, latihan langsung, dan pantau progres belajar tanpa perlu setup rumit.",
                    color = Color(0xFFD7E4E3),
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

@Composable
private fun BrandHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFF7A440)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "<>",
                color = Color(0xFF07111F),
                fontSize = 22.sp,
                fontWeight = FontWeight.Black
            )
        }

        Column {
            Text(
                text = "FastHTML",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Belajar coding web",
                color = Color(0xFFB7C9C7),
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
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0x1AFFFFFF))
            .padding(14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .background(Color(0x26F7A440), RoundedCornerShape(14.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFFF7A440),
                modifier = Modifier.size(22.dp)
            )
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = description,
                color = Color(0xFFCAD7D5),
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
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFBF3)),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Column(
            modifier = Modifier.padding(22.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Masuk ke kelas",
                    color = Color(0xFF102128),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Masukkan nama agar progress belajar bisa dipersonalisasi.",
                    color = Color(0xFF66767A),
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
                shape = RoundedCornerShape(18.dp),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Done
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFF28C28),
                    focusedLeadingIconColor = Color(0xFFF28C28),
                    focusedLabelColor = Color(0xFFF28C28),
                    cursorColor = Color(0xFFF28C28)
                )
            )

            Button(
                onClick = onLoginClick,
                enabled = canContinue,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF28C28),
                    contentColor = Color(0xFF102128),
                    disabledContainerColor = Color(0xFFE3DDD4),
                    disabledContentColor = Color(0xFF8A8177)
                )
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
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Color(0x33102128)),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color(0xFF102128)
                )
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
        LoginScreen()
    }
}
