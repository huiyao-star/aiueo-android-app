package com.example.aiueo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GrammarDetailScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("～までに", fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.StarBorder, contentDescription = null, tint = Color.Red)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Level and Category Tags
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                LevelTag("N4")
                CategoryTag("范围")
                CategoryTag("时点")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Star Rating
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                repeat(3) {
                    Icon(Icons.Default.StarBorder, contentDescription = null, tint = Color.Red, modifier = Modifier.size(20.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Connection (Setsuzoku)
            SectionHeaderDetail("【接续】")
            Text(
                buildAnnotatedString {
                    append("动词")
                    withStyle(style = SpanStyle(color = Color.Red, fontWeight = FontWeight.Bold)) {
                        append("辞书形")
                    }
                    append(" + ")
                    withStyle(style = SpanStyle(color = Color.Red, fontWeight = FontWeight.Bold)) {
                        append("までに")
                    }
                },
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                buildAnnotatedString {
                    append("名词")
                    append(" + ")
                    withStyle(style = SpanStyle(color = Color.Red, fontWeight = FontWeight.Bold)) {
                        append("までに")
                    }
                },
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Explanation (Setsumei)
            SectionHeaderDetail("【说明】")
            Text(
                text = "「までに」は、ある行动や出来事が完了するべき期限や时点を示す助词です。指定された期限内に特定の行动を终える必要がある场合に使われます。",
                fontSize = 15.sp,
                lineHeight = 22.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                text = "【译文】「までに」 is a particle that indicates a deadline or time by which an action or event should be completed. It is used when something must be finished within a specified timeframe.",
                fontSize = 14.sp,
                color = Color(0xFF64B5F6),
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Examples (Reibun)
            SectionHeaderDetail("【例文】")
            ExampleItemDetail(
                japanese = "・夕食までに帰ってきてね。",
                highlight = "までに",
                english = "【译文】Come back by dinner time."
            )
            ExampleItemDetail(
                japanese = "・宿题は明日までに提出してください。",
                highlight = "までに",
                english = "【译文】Please submit your homework by tomorrow."
            )
        }
    }
}

@Composable
fun SectionHeaderDetail(text: String) {
    Text(
        text = text,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF0288D1),
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun ExampleItemDetail(japanese: String, highlight: String, english: String) {
    Column(modifier = Modifier.padding(vertical = 12.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            val annotatedString = buildAnnotatedString {
                val parts = japanese.split(highlight)
                parts.forEachIndexed { index, s ->
                    append(s)
                    if (index < parts.size - 1) {
                        withStyle(style = SpanStyle(color = Color.Red, fontWeight = FontWeight.Bold)) {
                            append(highlight)
                        }
                    }
                }
            }
            Text(text = annotatedString, fontSize = 16.sp, modifier = Modifier.weight(1f))
            IconButton(onClick = { }) {
                Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = null, tint = Color.Red, modifier = Modifier.size(20.dp))
            }
        }
        Text(text = english, fontSize = 14.sp, color = Color(0xFF64B5F6), modifier = Modifier.padding(top = 4.dp))
    }
}

@Composable
fun LevelTag(text: String) {
    Surface(
        color = Color(0xFF0288D1),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CategoryTag(text: String) {
    Surface(
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF0288D1)),
        shape = RoundedCornerShape(16.dp),
        color = Color.Transparent
    ) {
        Text(
            text = text,
            color = Color(0xFF0288D1),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp),
            fontSize = 12.sp
        )
    }
}
