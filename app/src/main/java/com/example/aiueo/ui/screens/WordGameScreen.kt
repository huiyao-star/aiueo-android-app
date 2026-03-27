package com.example.aiueo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WordGameScreen(onBack: () -> Unit, highScore: Int, onUpdateScore: (Int) -> Unit) {
    var currentQuestion by remember { mutableIntStateOf(13) }
    var score by remember { mutableIntStateOf(2460) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp)
    ) {
        // Top Stats Section (Seiseki)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Card(
                modifier = Modifier.weight(1.5f),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("成绩", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                        StatItem("最高得点", highScore.toString(), Color.Red)
                        StatItem("平均得点", "8,200", Color.Black)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                        StatItem("测试次数", "44", Color.Black)
                        StatItem("游戏次数", "1", Color.Black)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                        StatItem("正确率", "68.2%", Color.Black)
                        StatItem("评价", "N4+", Color(0xFF0288D1))
                    }
                }
            }

            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                GameActionButton("排行榜", "第 235 位", Color(0xFFFF4081))
                GameActionButton("单词集", "习得 44 个", Color(0xFFFF4081))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    IconButton(
                        onClick = { },
                        modifier = Modifier.weight(1f).background(Color(0xFF424242), RoundedCornerShape(8.dp))
                    ) {
                        Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = null, tint = Color.White)
                    }
                    Button(
                        onClick = onBack,
                        modifier = Modifier.weight(1.5f).height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4081)),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text("戻る", fontSize = 14.sp)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Question Section
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
            Text("第 ", fontSize = 16.sp, color = Color.Gray)
            Text(currentQuestion.toString(), fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0288D1))
            Text(" 问", fontSize = 16.sp, color = Color.Gray)
            Spacer(modifier = Modifier.weight(1f))
            Text("得点", fontSize = 12.sp, color = Color.Gray)
            Text(" $score", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFF4081))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Surface(color = Color(0xFF0288D1), shape = RoundedCornerShape(16.dp)) {
                    Text("N4", color = Color.White, fontSize = 12.sp, modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp))
                }
                Text("育てる", fontSize = 48.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 8.dp))
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Options
        val options = listOf(
            "そだてる" to listOf(1, 2),
            "そだてる" to listOf(1, 2, 3),
            "そたてる" to listOf(0, 1, 2),
            "そたてる" to listOf(1)
        )

        options.forEachIndexed { index, pair ->
            QuizOption(
                label = ('A' + index).toString(),
                text = pair.first,
                accentPositions = pair.second,
                onClick = { 
                    score += 100
                    onUpdateScore(score)
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        Spacer(modifier = Modifier.weight(1f))
        Text(
            "单词の正しい読み方を選んでください。",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}

@Composable
fun StatItem(label: String, value: String, valueColor: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, fontSize = 9.sp, color = Color.Gray)
        Text(value, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = valueColor)
    }
}

@Composable
fun GameActionButton(title: String, subtitle: String, color: Color) {
    Card(
        modifier = Modifier.fillMaxWidth().height(48.dp),
        colors = CardDefaults.cardColors(containerColor = color),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(title, fontSize = 11.sp, color = Color.White)
            Text(subtitle, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}

@Composable
fun QuizOption(label: String, text: String, accentPositions: List<Int>, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF0288D1)),
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(label + ". ", color = Color(0xFF0288D1), fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                PitchAccentText(text, accentPositions)
            }
        }
    }
}

@Composable
fun PitchAccentText(text: String, accentPositions: List<Int>) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box {
            Row {
                text.forEachIndexed { index, char ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .width(20.dp)
                                .height(2.dp)
                                .background(if (index in accentPositions) Color.Red else Color.Transparent)
                        )
                        Text(char.toString(), fontSize = 20.sp, modifier = Modifier.padding(horizontal = 2.dp))
                    }
                }
            }
        }
    }
}
