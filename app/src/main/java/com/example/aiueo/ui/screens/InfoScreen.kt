package com.example.aiueo.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.aiueo.ui.theme.AiueoTheme

@Composable
fun InfoScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "情报与设置 (Info & Settings)")
    }
}

@Preview(showBackground = true)
@Composable
fun InfoScreenPreview() {
    AiueoTheme {
        InfoScreen()
    }
}
