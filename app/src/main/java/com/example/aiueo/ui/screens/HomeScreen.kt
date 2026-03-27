package com.example.aiueo.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aiueo.ui.theme.AiueoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("AIUEO 日本語") })
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Text(
                    text = "Welcome back!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            item {
                FeaturedCard()
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                Text(
                    text = "Learning Modules",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            
            val modules = listOf(
                ModuleItem("五十音测试", Icons.Default.Quiz, "Gojuon Test"),
                ModuleItem("单词游戏", Icons.Default.Gamepad, "Word Game"),
                ModuleItem("汉字学习", Icons.Default.Translate, "Kanji"),
                ModuleItem("文法精讲", Icons.AutoMirrored.Filled.MenuBook, "Grammar"),
                ModuleItem("场景对话", Icons.AutoMirrored.Filled.Chat, "Conversation"),
                ModuleItem("视频讲座", Icons.Default.PlayCircle, "Video")
            )
            
            // Grid implementation inside LazyColumn using chunks
            modules.chunked(2).forEach { rowModules ->
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        rowModules.forEach { module ->
                            Box(modifier = Modifier.weight(1f)) {
                                ModuleCard(module)
                            }
                        }
                        if (rowModules.size < 2) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FeaturedCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            Column {
                Text("Daily Goal", fontWeight = FontWeight.Bold)
                Text("Keep up your streak! 5 days in a row.")
                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = { /* TODO */ }) {
                    Text("Continue Lesson")
                }
            }
        }
    }
}

data class ModuleItem(val name: String, val icon: ImageVector, val subtitle: String)

@Composable
fun ModuleCard(module: ModuleItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* TODO: Navigate */ },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(module.icon, contentDescription = null, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = module.name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(text = module.subtitle, fontSize = 12.sp, color = MaterialTheme.colorScheme.outline)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AiueoTheme {
        HomeScreen()
    }
}
