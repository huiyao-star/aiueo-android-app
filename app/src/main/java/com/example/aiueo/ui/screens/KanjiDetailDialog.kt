package com.example.aiueo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun KanjiDetailDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = Color(0xFF1E1E1E),
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Box {
                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(Icons.Default.Close, contentDescription = null, tint = Color.Red)
                }

                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Kanji strokes placeholder
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .background(Color.Transparent)
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        // Drawing a grid background
                        // Mocking the kanji "中"
                        Text("中", fontSize = 100.sp, color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                        KanjiReadingInfo(label = "音读", readings = listOf("ちゅう", "じゅう"), color = Color.Red)
                        KanjiReadingInfo(label = "训读", readings = listOf("なか"), color = Color.Blue)
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Examples in dialog
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            MiniExampleCard("中心", "ちゅうしん", "center", Modifier.weight(1f))
                            MiniExampleCard("休憩中", "きゅうけいちゅう", "resting", Modifier.weight(1f))
                        }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            MiniExampleCard("一日中", "いちにちじゅう", "all day", Modifier.weight(1f))
                            MiniExampleCard("世界中", "せかいじゅう", "all over the world", Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun KanjiReadingInfo(label: String, readings: List<String>, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(color = color, shape = RoundedCornerShape(12.dp)) {
            Text(text = label, color = Color.White, fontSize = 10.sp, modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp))
        }
        readings.forEach { reading ->
            Text(text = reading, color = color, fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 4.dp))
        }
    }
}

@Composable
fun MiniExampleCard(kanji: String, reading: String, english: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2A2A)),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = reading, fontSize = 8.sp, color = Color.Red)
            Text(text = kanji, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = english, fontSize = 10.sp, color = Color.Gray)
        }
    }
}
