package com.example.aiueo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aiueo.ui.theme.AiueoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GrammarScreen(onNavigateToDetail: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("🥡 ", fontSize = 20.sp)
                        Text("文法", fontWeight = FontWeight.Bold)
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
                    title = "基础知识",
                    description = "文的构成、单词的分段、活用、助词的使用方法、口语形等，日语语法的基本知识学习吧。",
                    badgeText = "学习",
                    icon = "📑",
                    onClick = onNavigateToDetail
                )
            }

            item {
                LargeActionCard(
                    title = "敬语的使用方法",
                    description = "日语的敬语是、丁宁语・美化语・尊敬语・谦让语・丁重语等分类。各自的使用方法学习吧。",
                    badgeText = "学习",
                    icon = "🍵",
                    onClick = onNavigateToDetail
                )
            }

            item {
                GrammarListSection(onItemClick = onNavigateToDetail)
            }

            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    ActionButton(modifier = Modifier.weight(1f), text = "使用频度", color = Color(0xFFFF4081))
                    ActionButton(modifier = Modifier.weight(1f), text = "表现分类", color = Color(0xFFFF4081))
                    ActionButton(modifier = Modifier.weight(1f), text = "类似文法", color = Color(0xFFFF4081))
                }
            }

            item {
                LargeActionCard(
                    title = "文法测试",
                    description = "学习过的文法确实记住了吗？用测试来确认一下吧！",
                    badgeText = "测试",
                    icon = "⚽",
                    onClick = { /* Navigate to Test */ }
                )
            }
        }
    }
}

@Composable
fun LargeActionCard(title: String, description: String, badgeText: String, icon: String, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Badge(containerColor = Color(0xFFFF4081)) {
                        Text(badgeText, color = Color.White, modifier = Modifier.padding(horizontal = 4.dp))
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = description, fontSize = 13.sp, color = Color.Gray, lineHeight = 18.sp)
            }
            Text(text = icon, fontSize = 50.sp)
        }
    }
}

@Composable
fun GrammarListSection(onItemClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1f).height(1.dp).background(Color.Gray))
                Text("文法一览", modifier = Modifier.padding(horizontal = 16.dp), fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Box(modifier = Modifier.weight(1f).height(1.dp).background(Color.Gray))
                Icon(Icons.Default.Search, contentDescription = null, tint = Color(0xFFFF4081), modifier = Modifier.padding(start = 8.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            val levels = listOf(
                GrammarLevel("N5", "初级文法 I", "(合计: 109个)", Color(0xFF00BCD4)),
                GrammarLevel("N4", "初级文法 II", "(合计: 107个)", Color(0xFF0288D1)),
                GrammarLevel("N3", "中级文法 I", "(合计: 152个)", Color(0xFF1565C0)),
                GrammarLevel("N2", "中级文法 II", "(合计: 183个)", Color(0xFF283593)),
                GrammarLevel("N1", "上级文法", "(合计: 206个)", Color(0xFF311B92)),
                GrammarLevel("全 て", "全部的文法", "(合计: 757个)", Color(0xFFFF1744))
            )

            Column {
                levels.chunked(3).forEach { rowItems ->
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        rowItems.forEach { level ->
                            LevelCard(modifier = Modifier.weight(1f).clickable { onItemClick() }, level = level)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

data class GrammarLevel(val name: String, val title: String, val count: String, val color: Color)

@Composable
fun LevelCard(modifier: Modifier = Modifier, level: GrammarLevel) {
    Card(
        modifier = modifier.height(90.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2A2A)),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(level.color),
                contentAlignment = Alignment.Center
            ) {
                Text(text = level.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.5f)
                    .padding(horizontal = 4.dp, vertical = 2.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = level.title, 
                    fontSize = 10.sp, 
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 12.sp,
                    maxLines = 2
                )
                Text(
                    text = level.count, 
                    fontSize = 9.sp, 
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun ActionButton(modifier: Modifier = Modifier, text: String, color: Color) {
    Button(
        onClick = { },
        modifier = modifier.height(40.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(text = text, fontSize = 13.sp, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun GrammarScreenPreview() {
    AiueoTheme {
        GrammarScreen(onNavigateToDetail = {})
    }
}
