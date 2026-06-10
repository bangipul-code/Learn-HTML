package com.example.myapp_learnhtml.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OnboardingScreen(
    onNavigateForward: (String) -> Unit = {}
) {
    var userName by remember { mutableStateOf("") }
    var selectedAvatar by remember { mutableStateOf(0) }
    
    val avatarEmojis = listOf("👨‍💻", "👩‍💻", "🧑‍💻", "👨", "👩")
    val primaryColor = Color(0xFF6200EA)
    val secondaryColor = Color(0xFF03DAC6)
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = androidx.compose.ui.graphics.Brush.linearGradient(
                    colors = listOf(Color(0xFFFFFFFF), Color(0xFFF8F8FF))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            
            // Header Section
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                // App Logo/Title
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(
                            color = primaryColor,
                            shape = RoundedCornerShape(20.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "</>\n HTML",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "fastHTML",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = primaryColor
                )
                
                Text(
                    text = "Belajar HTML dengan Mudah dan Menyenangkan",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            
            // Features Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                FeatureItem(icon = "📚", title = "Materi Lengkap", description = "Pelajari HTML dari dasar hingga mahir")
                FeatureItem(icon = "💻", title = "Editor Real-time", description = "Praktik langsung dengan code editor")
                FeatureItem(icon = "🎯", title = "Kuis Interaktif", description = "Uji pemahaman dengan kuis seru")
                FeatureItem(icon = "📊", title = "Progress Tracking", description = "Pantau perkembangan belajar Anda")
            }
            
            // User Input Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Siapa Nama Anda?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                
                // Name Input Field
                OutlinedTextField(
                    value = userName,
                    onValueChange = { userName = it },
                    label = { Text("Masukkan nama lengkap") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Name Icon",
                            tint = primaryColor
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = primaryColor,
                        unfocusedBorderColor = Color.LightGray,
                        cursorColor = primaryColor
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    maxLines = 1
                )
                
                // Avatar Selection
                Text(
                    text = "Pilih Avatar",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    avatarEmojis.forEachIndexed { index, emoji ->
                        AvatarButton(
                            emoji = emoji,
                            isSelected = selectedAvatar == index,
                            primaryColor = primaryColor,
                            onSelect = { selectedAvatar = index },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Continue Button
            Button(
                onClick = {
                    if (userName.isNotBlank()) {
                        onNavigateForward(userName)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = userName.isNotBlank(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryColor,
                    disabledContainerColor = Color.LightGray
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Lanjutkan",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun FeatureItem(
    icon: String,
    title: String,
    description: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = icon,
            fontSize = 28.sp,
            modifier = Modifier.width(40.dp)
        )
        
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Text(
                text = description,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun AvatarButton(
    emoji: String,
    isSelected: Boolean,
    primaryColor: Color,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onSelect,
        modifier = modifier
            .aspectRatio(1f),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) primaryColor else Color.LightGray,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = emoji,
            fontSize = 32.sp
        )
    }
}
