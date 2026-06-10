package com.example.myapp_learnhtml.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun PraktikScreen(
	navController: NavHostController
) {
	val primaryColor = Color(0xFF6200EA)
	val backgroundColor = Color(0xFFF5F5F5)

	var code by remember { mutableStateOf("<h1>Halo Dunia</h1>") }
	var showPreview by remember { mutableStateOf(false) }

	Scaffold { paddingValues ->
		Column(
			modifier = Modifier
				.fillMaxSize()
				.background(backgroundColor)
				.padding(paddingValues)
				.verticalScroll(rememberScrollState())
				.padding(16.dp),
			verticalArrangement = Arrangement.spacedBy(12.dp)
		) {
			// Header
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.background(
						color = primaryColor,
						shape = RoundedCornerShape(12.dp)
					)
					.padding(16.dp)
			) {
				Column {
					Text(
						text = "Praktik",
						fontSize = 20.sp,
						fontWeight = FontWeight.Bold,
						color = Color.White
					)
					Text(
						text = "Latihan menulis dan mencoba kode HTML sederhana",
						fontSize = 12.sp,
						color = Color.White.copy(alpha = 0.9f)
					)
				}
			}

			// Editor area
			Card(
				shape = RoundedCornerShape(8.dp),
				colors = CardDefaults.cardColors(containerColor = Color.White),
				modifier = Modifier.fillMaxWidth()
			) {
				Column(modifier = Modifier.padding(12.dp)) {
					Text(text = "Editor HTML", fontWeight = FontWeight.SemiBold)
					Spacer(modifier = Modifier.height(8.dp))
					OutlinedTextField(
						value = code,
						onValueChange = { code = it },
						modifier = Modifier
							.fillMaxWidth()
							.height(160.dp),
						singleLine = false,
						maxLines = 20
					)

					Row(
						modifier = Modifier
							.fillMaxWidth()
							.padding(top = 8.dp),
						horizontalArrangement = Arrangement.End
					) {
						Button(onClick = { showPreview = true }) {
							Text(text = "Jalankan")
						}
					}
				}
			}

			// Contoh soal
			Text(text = "Daftar Latihan", fontWeight = FontWeight.SemiBold)

			val latihan = listOf(
				"Buat judul halaman menggunakan <h1>",
				"Tambahkan paragraf dengan teks singkat",
				"Buat daftar bernomor dengan 3 item",
				"Tambahkan tautan ke https://example.com"
			)

			latihan.forEachIndexed { index, item ->
				Card(
					modifier = Modifier
						.fillMaxWidth()
						.clickable { /* bisa navigasi ke detail latihan */ },
					shape = RoundedCornerShape(8.dp),
					colors = CardDefaults.cardColors(containerColor = Color.White)
				) {
					Row(
						modifier = Modifier
							.fillMaxWidth()
							.padding(12.dp),
						verticalAlignment = Alignment.CenterVertically
					) {
						Column(modifier = Modifier.weight(1f)) {
							Text(text = "Latihan ${index + 1}", fontWeight = FontWeight.SemiBold)
							Text(text = item, fontSize = 12.sp, color = Color.Gray)
						}
						Text(text = "→", color = primaryColor, fontWeight = FontWeight.Bold)
					}
				}
			}

			Spacer(modifier = Modifier.height(40.dp))
		}
	}

	if (showPreview) {
		AlertDialog(
			onDismissRequest = { showPreview = false },
			confirmButton = {
				TextButton(onClick = { showPreview = false }) {
					Text(text = "Tutup")
				}
			},
			title = { Text(text = "Pratinjau (simulasi)") },
			text = {
				Column {
					Text(text = "Kode HTML:")
					Spacer(modifier = Modifier.height(8.dp))
					Card(
						modifier = Modifier.fillMaxWidth(),
						colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0))
					) {
						Text(
							text = code,
							modifier = Modifier.padding(12.dp)
						)
					}
				}
			}
		)
	}
}
