package com.example.myapp_learnhtml

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun MateriBelajar() {
    val limit = 1 .. 100

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(size = 5.dp)
                )
                // .padding(horizontal = 50.dp)
                .padding(all = 50.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Status",
                textDecoration = TextDecoration.LineThrough,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.38f)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "Lock"
                )
                Text(text = "Bayar Rp.100.000")
            }
        }

        Text(text = "Dasar-Dasar HTML", modifier = Modifier.padding(top = 20.dp, bottom = 10.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(count = 10) { index ->
                Box(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(size = 10.dp)
                        )
                        .padding(
                            vertical = 50.dp,
                            horizontal = 10.dp
                        )
                        .fillMaxWidth()
                ) {
                    if (index in 0..5) {
                        Text(
                            text = "Materi ${index + 1}"
                        )
                    }
                    else {
                        Text(
                            text = "Materi ${index + 1}",
                            textDecoration = TextDecoration.LineThrough,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                        )
                    }
                }
            }
        }
    }
}