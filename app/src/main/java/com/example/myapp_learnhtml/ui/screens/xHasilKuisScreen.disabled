package com.example.myapp_learnhtml.ui.screens

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun PreviewTrue() {
    ComplexHtmlViewer(htmlContent = "<button>Button</button>")
}

@Composable
fun ComplexHtmlViewer(htmlContent: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp), // Tentukan tinggi atau gunakan penyesuaian lain
            factory = { context ->
                WebView(context).apply {
                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true // Aktifkan jika butuh JS
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
}