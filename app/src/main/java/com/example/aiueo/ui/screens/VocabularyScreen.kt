package com.example.aiueo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
fun VocabularyScreen(
    onNavigateToSurname: () -> Unit,
    onNavigateToGame: () -> Unit,
    todayStudyTimeText: String = "0分",
    totalStudyTimeText: String = "0分"
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("🍡 ", fontSize = 20.sp)
                        Text("单词", fontWeight = FontWeight.Bold)
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                LargeActionCard(
                    title = "单词分类",
                    description = "时间・日付・数字・人名・地名・动物・植物・色・副词等，按照分类记日常常用的单词吧。",
                    badgeText = "学习",
                    icon = "🎨",
                    onClick = onNavigateToSurname
                )
            }

            item {
                LargeActionCard(
                    title = "小学生汉字",
                    description = "学习日本小学阶段约1,000个汉字的写法・读法，以及使用该汉字的单词。",
                    badgeText = "学习",
                    icon = "🎒",
                    onClick = { /* Navigate to Kanji list */ }
                )
            }

            item {
                LargeActionCard(
                    title = "自动词和他动词",
                    description = "自动词和他动词成对出现，有几种固定的模式。按规律总结学习吧。",
                    badgeText = "学习",
                    icon = "🔄",
                    onClick = { /* Navigate to Auto/Transitive */ }
                )
            }

            item {
                LargeActionCard(
                    title = "单词游戏",
                    description = "单词的重音有好好记住吗？挑战游戏，愉快地确认吧！还可以查看自己的排名。",
                    badgeText = "挑战",
                    icon = "🎮",
                    onClick = onNavigateToGame
                )
            }

            item {
                Box(modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("勉强时间", fontSize = 12.sp, color = Color.Gray)
                        Text("累计: $totalStudyTimeText  今日: $todayStudyTimeText", fontSize = 12.sp, color = Color.Gray)
                    }
                }
            }
        }
    }
}
