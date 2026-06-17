package com.example.myapp_learnhtml

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LaptopChromebook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.edit
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapp_learnhtml.ui.screens.DetailLatihanScreen
import com.example.myapp_learnhtml.ui.screens.DetailMateriScreen
import com.example.myapp_learnhtml.ui.screens.DetailPraktikScreen
import com.example.myapp_learnhtml.ui.screens.HasilLatihanScreen
import com.example.myapp_learnhtml.ui.screens.HomeScreen
import com.example.myapp_learnhtml.ui.screens.LatihanScreen
import com.example.myapp_learnhtml.ui.screens.PraktikScreen
import com.example.myapp_learnhtml.ui.screens.ProfilScreen

private const val ONBOARDING_ROUTE = "onboarding"

// === DEKLARASI RUTE === \\
sealed class Screen(
    val title: String,
    val icon: ImageVector,
    val route: String,
) {
    object Home : Screen(
        title = "Beranda",
        icon = Icons.Default.Home,
        route = "home"
    )

    object Materi : Screen(
        title = "Materi",
        icon = Icons.Default.Book,
        route = "materi"
    )

    object Praktik : Screen(
        title = "Praktik",
        icon = Icons.AutoMirrored.Filled.Assignment,
        route = "praktik"
    )

    object Latihan : Screen(
        title = "Latihan",
        icon = Icons.Default.LaptopChromebook,
        route = "latihan"
    )

    object DetailLatihan : Screen(
        title = "Detail Latihan",
        icon = Icons.Default.LaptopChromebook,
        route = "detail_latihan/{topicIndex}"
    )

    object HasilLatihan : Screen(
        title = "Hasil Latihan",
        icon = Icons.Default.LaptopChromebook,
        route = "hasil_latihan/{topicIndex}"
    )

    object Profil : Screen(
        title = "Profil",
        icon = Icons.Default.Person,
        route = "profil"
    )
}

// === HALAMAN PRAKTIK === \\
// PraktikScreen is now in ui/screens/PraktikScreen.kt

// ========== MAIN SCREEN ========== \\

@Composable
fun NavigationBar(navController: NavController) {
    val screens = listOf(Screen.Home, Screen.Materi, Screen.Praktik, Screen.Latihan, Screen.Profil)

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        screens.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(route = screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.title
                    )
                },
                label = {
                    Text(text = screen.title)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val context = LocalContext.current
    val prefs = remember {
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }
    var userName by rememberSaveable {
        mutableStateOf(value = prefs.getString("user_name", "") ?: "")
    }

    // Tentukan rute awal: jika nama sudah ada, langsung ke Dashboard (Home)
    val startDestination = remember {
        if (userName.isBlank()) ONBOARDING_ROUTE else Screen.Home.route
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != ONBOARDING_ROUTE) {
                NavigationBar(navController = navController)
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = ONBOARDING_ROUTE) {
                LoginScreen2(
                    onLoginClick = { name ->
                        userName = name
                        prefs.edit { putString("user_name", name) }

                        navController.navigate(Screen.Home.route) {
                            popUpTo(ONBOARDING_ROUTE) { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                    onExploreClick = {
                        navController.navigate(Screen.Materi.route) {
                            popUpTo(ONBOARDING_ROUTE) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(route = Screen.Home.route) {
                HomeScreen(
                    userName = userName.ifBlank { "Anonymous" },
                    userAvatar = "",
                    navController = navController
                )
            }
            composable(route = Screen.Materi.route) { MateriBelajar(navController = navController) }
            composable(route = "detail_materi/{index}") { backStackEntry ->
                val indexStr = backStackEntry.arguments?.getString("index") ?: "0"
                val index = indexStr.toIntOrNull() ?: 0
                DetailMateriScreen(
                    materiIndex = index,
                    navController = navController
                )
            }
            composable(route = Screen.Praktik.route) { PraktikScreen(navController = navController) }
            composable(route = "detail_praktik/{index}") { backStackEntry ->
                val indexStr = backStackEntry.arguments?.getString("index") ?: "0"
                val index = indexStr.toIntOrNull() ?: 0
                DetailPraktikScreen(
                    topicIndex = index,
                    navController = navController
                )
            }
            composable(route = Screen.Latihan.route) { LatihanScreen(navController = navController) }
            composable(route = Screen.DetailLatihan.route) { backStackEntry ->
                val topicIndexStr = backStackEntry.arguments?.getString("topicIndex") ?: "0"
                val topicIndex = topicIndexStr.toIntOrNull() ?: 0
                DetailLatihanScreen(
                    topicIndex = topicIndex,
                    navController = navController
                )
            }
            composable(route = Screen.HasilLatihan.route) { backStackEntry ->
                val topicIndexStr = backStackEntry.arguments?.getString("topicIndex") ?: "0"
                val topicIndex = topicIndexStr.toIntOrNull() ?: 0
                HasilLatihanScreen(
                    topicIndex = topicIndex,
                    navController = navController
                )
            }
            composable(route = Screen.Profil.route) { ProfilScreen() }
        }
    }
}
