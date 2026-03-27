package com.example.aiueo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aiueo.ui.theme.AiueoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadingScreen(
    onNavigateToDetail: () -> Unit,
    todayStudyTimeText: String = "0分",
    totalStudyTimeText: String = "0分"
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("🍤 ", fontSize = 20.sp)
                        Text("音读", fontWeight = FontWeight.Bold)
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
                ReadingBannerCard(
                    title = "音读练习",
                    description = "日语的发音和对话能力向上，每日音读练习开始吧！",
                    badgeText = "练习",
                    onClick = onNavigateToDetail
                )
            }

            item {
                Row(modifier = Modifier.fillMaxWidth()) {
                    SmallActionCard(
                        modifier = Modifier.weight(1f),
                        title = "收藏夹",
                        subtitle = "保存済: 0个",
                        icon = Icons.Default.Star,
                        iconColor = Color(0xFF64B5F6)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    SmallActionCard(
                        modifier = Modifier.weight(1f),
                        title = "浏览历史",
                        subtitle = "最大保存 20个",
                        icon = Icons.Default.History,
                        iconColor = Color(0xFFE57373)
                    )
                }
            }

            item {
                SectionHeader(title = "会话练习", icon = Icons.AutoMirrored.Filled.List)
            }

            item {
                val categories = listOf("日常生活", "购物", "交通", "餐厅")
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(categories) { category ->
                        CategoryCard(category, onClick = onNavigateToDetail)
                    }
                }
            }

            item {
                ReadingBannerCard(
                    title = "【发音技巧】视频",
                    description = "学习发音技巧的视频系列。重音、长音、促音、节奏、语调等日语发音要点。",
                    badgeText = "观看",
                    showPlayIcon = true
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

@Composable
fun ReadingBannerCard(
    title: String,
    description: String,
    badgeText: String,
    showPlayIcon: Boolean = false,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Badge(containerColor = Color(0xFFFF4081)) {
                        Text(badgeText, color = Color.White, modifier = Modifier.padding(horizontal = 4.dp))
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = description, fontSize = 13.sp, color = Color.Gray, lineHeight = 18.sp)
            }
            Box(modifier = Modifier.size(60.dp), contentAlignment = Alignment.Center) {
                Text(if(showPlayIcon) "📺" else "🍤", fontSize = 40.sp)
            }
        }
    }
}

@Composable
fun CategoryCard(name: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier.width(100.dp).height(130.dp).clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Box(modifier = Modifier.size(60.dp).background(Color(0xFF2A2A2A), RoundedCornerShape(8.dp)), contentAlignment = Alignment.Center) {
                Text("🎨", fontSize = 30.sp)
            }
        }
    }
}

@Composable
fun SmallActionCard(modifier: Modifier = Modifier, title: String, subtitle: String, icon: ImageVector, iconColor: Color) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(text = subtitle, fontSize = 11.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun SectionHeader(title: String, icon: ImageVector) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(1f).height(1.dp).background(Color.Gray))
        Text(text = title, modifier = Modifier.padding(horizontal = 16.dp), fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Box(modifier = Modifier.weight(1f).height(1.dp).background(Color.Gray))
        Icon(icon, contentDescription = null, tint = Color(0xFFFF4081), modifier = Modifier.padding(start = 8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ReadingScreenPreview() {
    AiueoTheme {
        ReadingScreen(onNavigateToDetail = {})
    }
}
