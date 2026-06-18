package com.example.myapp_learnhtml

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapp_learnhtml.ui.theme.Myapp_learnHTMLTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            Myapp_learnHTMLTheme { MainScreen() }

            /* val navController = rememberNavController()
            var userName by rememberSaveable { mutableStateOf("") }

            Myapp_learnHTMLTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(modifier = Modifier.padding(innerPadding)) {
                        NavHost(
                            navController = navController,
                            startDestination = "onboarding",
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable("onboarding") {
                                OnboardingScreen(onNavigateForward = {
                                    userName = it
                                    navController.navigate("home") {
                                        popUpTo("onboarding") { inclusive = true }
                                    }
                                })
                            }
                            composable("home") {
                                HomeScreen(
                                    userName = userName,
                                    userAvatar = "👨‍💻",
                                    navController = navController
                                )
                            }
                            composable("materi") {
                                DestinationScreen(title = "Materi")
                            }
                            composable("kuis") {
                                DestinationScreen(title = "Kuis")
                            }
                            composable("progress") {
                                DestinationScreen(title = "Progress")
                            }
                            composable("profil") {
                                DestinationScreen(title = "Profil")
                            }
                        }
                    }
                }
            } */
        }
    }
}

 /* @Composable
 fun FastHtmlOnboarding() {
     val pages = listOf(
         OnboardingPage(
             title = "Selamat Datang di FastHTML",
             description = "Belajar membuat tampilan HTML dengan cepat dan mudah di aplikasi ini."
         ),
         OnboardingPage(
             title = "Template Cepat",
             description = "Pilih template, edit konten, dan lihat hasilnya secara instan."
         ),
         OnboardingPage(
             title = "Praktik dan Simulasikan",
             description = "Uji hasil HTML Anda langsung di dalam aplikasi tanpa perlu browser tambahan."
         )
     )

     var pageIndex by rememberSaveable { mutableStateOf(0) }
     val page = pages[pageIndex]

     Column(
         modifier = Modifier
             .fillMaxSize()
             .padding(24.dp),
         verticalArrangement = Arrangement.SpaceBetween,
         horizontalAlignment = Alignment.CenterHorizontally
     ) {
         Column(
             horizontalAlignment = Alignment.CenterHorizontally
         ) {
             Text(
                 text = page.title,
                 style = MaterialTheme.typography.headlineMedium,
                 modifier = Modifier.padding(bottom = 16.dp)
             )
             Text(
                 text = page.description,
                 style = MaterialTheme.typography.bodyLarge,
                 modifier = Modifier.padding(bottom = 24.dp)
             )
         }

         Column(
             horizontalAlignment = Alignment.CenterHorizontally
         ) {
             Text(
                 text = "Langkah ${pageIndex + 1} dari ${pages.size}",
                 style = MaterialTheme.typography.bodySmall,
                 modifier = Modifier.padding(bottom = 12.dp)
             )
             Button(
                 onClick = {
                     if (pageIndex < pages.lastIndex) {
                         pageIndex += 1
                     }
                 }
             ) {
                 Text(if (pageIndex < pages.lastIndex) "Selanjutnya" else "Mulai")
             }
         }
     }
 } */

 /* private data class OnboardingPage(
     val title: String,
     val description: String
 ) */

 /* @Preview(showBackground = true)
 @Composable
 fun FastHtmlOnboardingPreview() {
     Myapp_learnHTMLTheme {
         FastHtmlOnboarding()
     }
 } */

/* @Composable
 fun DestinationScreen(title: String) {
     Column(
         modifier = Modifier
             .fillMaxSize()
             .padding(24.dp),
         verticalArrangement = Arrangement.Center,
         horizontalAlignment = Alignment.CenterHorizontally
     ) {
         Text(text = title, style = MaterialTheme.typography.headlineSmall)
     }
 } */

/* @Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    Myapp_learnHTMLTheme {
        MainScreen()
    }
} */