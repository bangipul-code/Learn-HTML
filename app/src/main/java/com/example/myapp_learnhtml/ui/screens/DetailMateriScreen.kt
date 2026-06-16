package com.example.myapp_learnhtml.ui.screens

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapp_learnhtml.ui.viewmodel.DetailMateriViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMateriScreen(
    materiIndex: Int,
    navController: NavController,
    viewModel: DetailMateriViewModel = viewModel()
) {
    val topics by viewModel.topics.collectAsState()
    val currentTopic by viewModel.currentTopic.collectAsState()
    val pages by viewModel.pages.collectAsState()
    val currentPageIndex by viewModel.currentPageIndex.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(key1 = materiIndex) {
        viewModel.loadTopic(topicId = materiIndex)
    }

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    val page = pages.getOrNull(currentPageIndex)
    if (page == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Materi tidak ditemukan")
        }
        return
    }

    val progress = (currentPageIndex + 1).toFloat() / pages.size
    var menuExpanded by remember { mutableStateOf(value = false) }
    var glossaryExpanded by remember { mutableStateOf(value = false) }
    val glosariumList = remember(key1 = page) { viewModel.getGlosariumList(page) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = currentTopic?.title ?: "",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium,
                        // color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali",
                            // tint = Color.Black
                        )
                    }
                },
                // colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                //     containerColor = Color.White
                // ),
                modifier = Modifier.border(
                    width = 1.dp,
                    color = Color.Black
                )
            )
        },
        bottomBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color.Black
                    ),
                // color = Color.White
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = { viewModel.goToPreviousPage() },
                        enabled = currentPageIndex > 0,
                        shape = RoundedCornerShape(size = 10.dp),
                        // colors = ButtonDefaults.outlinedButtonColors(
                        //     contentColor = Color.Black,
                        //     disabledContentColor = Color.Gray
                        // ),
                        modifier = Modifier.width(130.dp)
                    ) {
                        Text(text = "Sebelumnya", fontWeight = FontWeight.SemiBold)
                    }

                    Button(
                        onClick = {
                            if (currentPageIndex < pages.lastIndex) {
                                viewModel.goToNextPage()
                            } else {
                                viewModel.markMateriCompleted(materiIndex)
                                navController.popBackStack()
                            }
                        },
                        shape = RoundedCornerShape(size = 10.dp),
                        // colors = ButtonDefaults.buttonColors(
                        //     containerColor = Color.Black,
                        //     contentColor = Color.White
                        // ),
                        modifier = Modifier.width(130.dp)
                    ) {
                        Text(
                            text = if (currentPageIndex == pages.lastIndex) "Selesai" else "Berikutnya",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        },
        // containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
                .verticalScroll(state = rememberScrollState())
                .padding(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Progress Bar
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(size = 12.dp),
                // colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(
                    modifier = Modifier.padding(all = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clickable { menuExpanded = true }
                                    .padding(vertical = 4.dp)
                            ) {
                                Text(
                                    text = "Materi ${currentPageIndex + 1} dari ${pages.size}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    // color = Color.Black
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    imageVector = Icons.Filled.ArrowDropDown,
                                    contentDescription = "Pilih Halaman",
                                    // tint = Color.Black,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            DropdownMenu(
                                expanded = menuExpanded,
                                onDismissRequest = { menuExpanded = false },
                                // modifier = Modifier.background(Color.White)
                            ) {
                                pages.forEachIndexed { index, p ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = "${index + 1}. ${p.judul}",
                                                color = if (index == currentPageIndex) Color.Black else Color.Gray,
                                                fontWeight = if (index == currentPageIndex) FontWeight.Bold else FontWeight.Normal
                                            )
                                        },
                                        onClick = {
                                            viewModel.jumpToPage(index)
                                            menuExpanded = false
                                        }
                                    )
                                }
                            }
                        }

                        Text(
                            text = "${(progress * 100).toInt()}% Selesai",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            // color = Color.Black
                        )
                    }

                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp),
                        // color = Color.Black,
                        // trackColor = Color(0xFFE0E0E0)
                    )
                }
            }

            // Judul Halaman
            Text(
                text = page.judul,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold,
                // color = Color.Black
            )

            // Card Deskripsi
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(size = 12.dp),
                // colors = CardDefaults.cardColors(containerColor = Color.White),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(
                    modifier = Modifier.padding(all = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = page.deskripsi,
                        textAlign = TextAlign.Justify,
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = 22.sp,
                        // color = Color.Black
                    )
                }
            }

            // Card Analogi
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                // colors = CardDefaults.cardColors(containerColor = Color(0xFFFAFAFA)),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Row(
                    modifier = Modifier.padding(all = 14.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = "Analogi",
                        // tint = Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = page.analogi,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 20.sp,
                        // color = Color(0xFF4A4A4A)
                    )
                }
            }

            // Contoh Kode (dengan horizontal scroll) + Hasil Render
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Contoh Kode HTML:",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    // color = Color.Gray
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                        // .background(color = Color(0xFFF9F9F9), shape = RoundedCornerShape(8.dp))
                        .padding(12.dp)
                        .horizontalScroll(rememberScrollState())
                ) {
                    Text(
                        text = page.contohKode,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 13.sp,
                        // color = Color.Black
                    )
                }

                Text(
                    text = "Hasil Render di Browser:",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    // color = Color.Gray
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                        // .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                        .padding(all = 12.dp)
                ) {
                    AndroidWebView(htmlContent = page.hasilDiWeb)
                }
            }

            // Glosarium
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                // colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9)),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    val glossaryRotation = if (glossaryExpanded) 180f else 0f
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { glossaryExpanded = !glossaryExpanded }
                            .padding(all = 14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "Glosarium",
                                // tint = Color.Black,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "Glosarium",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                // color = Color.Black
                            )
                        }
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = if (glossaryExpanded) "Tutup" else "Buka",
                            // tint = Color.Black,
                            modifier = Modifier.rotate(glossaryRotation)
                        )
                    }

                    AnimatedVisibility(visible = glossaryExpanded) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 14.dp, vertical = 8.dp)
                                .padding(bottom = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            glosariumList.forEach { item ->
                                Column(modifier = Modifier.padding(bottom = 4.dp)) {
                                    Text(
                                        text = item.istilah,
                                        style = MaterialTheme.typography.bodySmall,
                                        fontWeight = FontWeight.Bold,
                                        // color = Color.Black
                                    )
                                    Text(
                                        text = item.definisi,
                                        style = MaterialTheme.typography.bodySmall,
                                        // color = Color.DarkGray,
                                        lineHeight = 16.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AndroidWebView(htmlContent: String, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier.fillMaxWidth(),
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = false
            }
        },
        update = { webView ->
            webView.loadDataWithBaseURL(
                null,
                htmlContent,
                "text/html",
                "utf-8",
                null
            )
        }
    )
}

// @Preview(showBackground = true)
// @Composable
// private fun DetailMateriScreenPreview() {
//     val navController = rememberNavController()
//     DetailMateriScreen(materiIndex = 0, navController = navController)
// }
