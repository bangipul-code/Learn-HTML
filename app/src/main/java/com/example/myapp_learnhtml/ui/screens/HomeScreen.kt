package com.example.myapp_learnhtml.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(
    userName: String = "Fauzi Taufiq",
    userAvatar: String = "🍕",
    navController: NavHostController
) {
    var selectedTab by remember { mutableStateOf(0) }
    
    val primaryColor = Color(0xFF6200EA)
    val secondaryColor = Color(0xFF03DAC6)
    val backgroundColor = Color(0xFFF5F5F5)
    
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it },
                primaryColor = primaryColor,
                navController = navController
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header with greeting
            item {
                HeaderSection(
                    userName = userName,
                    userAvatar = userAvatar,
                    primaryColor = primaryColor
                )
            }
            
            // Progress Card
            item {
                ProgressCard(
                    progressPercentage = 35,
                    completedLessons = 7,
                    totalLessons = 20,
                    primaryColor = primaryColor,
                    secondaryColor = secondaryColor
                )
            }
            
            // Continue Learning Section
            item {
                ContinueLearningSection(primaryColor = primaryColor)
            }
            
            // Categories Section
            item {
                CategoriesSection(
                    navController = navController,
                    primaryColor = primaryColor
                )
            }
            
            // Recent Activities
            item {
                RecentActivitiesSection(primaryColor = primaryColor)
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            }
    }
}

@Composable
fun HeaderSection(
    userName: String,
    userAvatar: String,
    primaryColor: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = primaryColor,
                shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
            )
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Selamat siang",
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = 0.7f)
                    )
                    Text(
                        text = userName,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                
                Text(
                    text = userAvatar,
                    fontSize = 48.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
            
            Text(
                text = "Mari lanjutkan belajar HTML! 🚀",
                fontSize = 12.sp,
                color = Color.White.copy(alpha = 0.8f)
            )
        }
    }
}

@Composable
fun ProgressCard(
    progressPercentage: Int,
    completedLessons: Int,
    totalLessons: Int,
    primaryColor: Color,
    secondaryColor: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Progres Belajar Anda",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            
            // Progress Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(12.dp)
                        .background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(6.dp)
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(fraction = progressPercentage / 100f)
                            .background(
                                color = secondaryColor,
                                shape = RoundedCornerShape(6.dp)
                            )
                    )
                }
                
                Text(
                    text = "$progressPercentage%",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = primaryColor
                )
            }
            
            // Stats Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                StatItem(label = "Selesai", value = "$completedLessons", primaryColor = primaryColor)
                StatItem(label = "Total", value = "$totalLessons", primaryColor = primaryColor)
                StatItem(label = "Sisa", value = "${totalLessons - completedLessons}", primaryColor = primaryColor)
            }
        }
    }
}

@Composable
fun StatItem(
    label: String,
    value: String,
    primaryColor: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = value,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = primaryColor
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun ContinueLearningSection(
    primaryColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Lanjutkan Belajar",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
        
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = {}),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(
                            color = Color(0xFFFFE0B2),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "📝", fontSize = 32.sp)
                }
                
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "HTML Basics",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                    Text(
                        text = "Struktur HTML & Tag Dasar",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
                
                Text(
                    text = "→",
                    fontSize = 20.sp,
                    color = primaryColor
                )
            }
        }
    }
}

@Composable
fun CategoriesSection(
    navController: NavHostController,
    primaryColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Kategori Materi",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
        
        val categories = listOf(
            CategoryItem("📚", "Dasar", Color(0xFFFFCDD2)),
            CategoryItem("🎨", "Form & Input", Color(0xFFC8E6C9)),
            CategoryItem("🔧", "Styling", Color(0xFFBBDEFB)),
            CategoryItem("⚡", "Advanced", Color(0xFFFFF9C4))
        )
        
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(categories) { category ->
                CategoryCard(
                    emoji = category.emoji,
                    title = category.title,
                    backgroundColor = category.color,
                    onClick = {
                        navController.navigate("materi") {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

data class CategoryItem(
    val emoji: String,
    val title: String,
    val color: Color
)

@Composable
fun CategoryCard(
    emoji: String,
    title: String,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = emoji, fontSize = 32.sp)
            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun RecentActivitiesSection(
    primaryColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Aktivitas Terbaru",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
        
        ActivityItem(
            icon = "✅",
            title = "Selesai: Tag HTML",
            subtitle = "Kemarin",
            primaryColor = primaryColor
        )
        
        ActivityItem(
            icon = "🎯",
            title = "Kuis: Tag Form",
            subtitle = "Skor: 8/10",
            primaryColor = primaryColor
        )
        
        ActivityItem(
            icon = "🔄",
            title = "Praktek: CSS Styling",
            subtitle = "Hari ini",
            primaryColor = primaryColor
        )
    }
}

@Composable
fun ActivityItem(
    icon: String,
    title: String,
    subtitle: String,
    primaryColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = icon, fontSize = 24.sp)
        
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = title,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Text(
                text = subtitle,
                fontSize = 11.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun BottomNavigationBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    primaryColor: Color,
    navController: NavHostController
) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        val navItems = listOf(
            NavItem("Home", "home", Icons.Filled.Home, 0),
            NavItem("Materi", "materi", Icons.Filled.School, 1),
            NavItem("Kuis", "kuis", Icons.Filled.HelpOutline, 2),
            NavItem("Progress", "progress", Icons.Filled.TrendingUp, 3),
            NavItem("Profil", "profil", Icons.Filled.Person, 4)
        )
        
        navItems.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 10.sp,
                        fontWeight = if (selectedTab == item.index) FontWeight.SemiBold else FontWeight.Normal
                    )
                },
                selected = selectedTab == item.index,
                onClick = {
                    onTabSelected(item.index)
                    navController.navigate(item.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = primaryColor,
                    selectedTextColor = primaryColor,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color(0xFFE0E0E0)
                )
            )
        }
    }
}

data class NavItem(
    val label: String,
    val route: String,
    val icon: ImageVector,
    val index: Int
)