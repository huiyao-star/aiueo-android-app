package com.example.aiueo.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aiueo.data.KanaData
import com.example.aiueo.model.Kana
import com.example.aiueo.ui.theme.AiueoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KanaScreen(onNavigateToTest: () -> Unit) {
    var selectedType by remember { mutableStateOf("Hiragana") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("五十音图 (Gojuon)") },
                actions = {
                    IconButton(onClick = onNavigateToTest) {
                        Icon(Icons.Default.Quiz, contentDescription = "Start Test", tint = Color(0xFFFF4081))
                    }
                    Row(modifier = Modifier.padding(end = 16.dp)) {
                        FilterChip(
                            selected = selectedType == "Hiragana",
                            onClick = { selectedType = "Hiragana" },
                            label = { Text("あ") }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        FilterChip(
                            selected = selectedType == "Katakana",
                            onClick = { selectedType = "Katakana" },
                            label = { Text("ア") }
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(5),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(KanaData.hiraganaChart) { kana ->
                    KanaCard(kana = kana, displayType = selectedType)
                }
            }
        }
    }
}

@Composable
fun KanaCard(kana: Kana, displayType: String) {
    if (kana.hiragana.isEmpty()) {
        Box(modifier = Modifier.size(70.dp))
        return
    }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .aspectRatio(1f)
            .clickable { /* TODO: Play Audio */ },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = if (displayType == "Hiragana") kana.hiragana else kana.katakana,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = kana.romaji,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KanaScreenPreview() {
    AiueoTheme {
        KanaScreen(onNavigateToTest = {})
    }
}
