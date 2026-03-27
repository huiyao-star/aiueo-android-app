package com.example.aiueo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurnameRankScreen(onBack: () -> Unit) {
    val surnames = listOf(
        "佐藤", "鈴木", "高橋", "田中", "渡辺", "伊藤", "山本", "中村",
        "小林", "加藤", "吉田", "山田", "佐々木", "山口", "斎藤", "松本",
        "井上", "木村", "林", "清水", "山崎", "森", "阿部", "池田",
        "橋本", "山下", "石川", "中島", "前田", "藤田", "小川", "後藤"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("单语分类", fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Search, contentDescription = null, tint = Color.Red)
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            // Category Tabs
            ScrollableTabRow(
                selectedTabIndex = 4,
                edgePadding = 16.dp,
                containerColor = Color.Transparent,
                contentColor = Color.Red,
                divider = {}
            ) {
                listOf("数字", "单位", "地名", "人名", "色", "位置", "动物").forEachIndexed { index, title ->
                    Tab(
                        selected = index == 3,
                        onClick = { },
                        text = { Text(title, fontSize = 14.sp) }
                    )
                }
            }

            Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Text(
                    text = "全国名字ランキング TOP 200",
                    color = Color.Red,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(surnames) { name ->
                    SurnameCard(name)
                }
            }
        }
    }
}

@Composable
fun SurnameCard(name: String) {
    Card(
        modifier = Modifier.fillMaxWidth().height(60.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2A2A))
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "さとう", fontSize = 8.sp, color = Color.Red) // Mock furigana
            Text(text = name, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}
