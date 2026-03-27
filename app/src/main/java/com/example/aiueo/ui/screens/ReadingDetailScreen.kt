package com.example.aiueo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.PlayArrow
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
fun ReadingDetailScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("牛乳を飲む", fontSize = 18.sp) },
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Mascot Illustration Placeholder
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("🥛🎒", fontSize = 80.sp) // Mocking the mascot
            }

            Text("难易度", fontSize = 12.sp, color = Color.Gray)
            Row {
                repeat(2) { Icon(Icons.Default.StarBorder, null, tint = Color.Green, modifier = Modifier.size(16.dp)) }
                repeat(1) { Icon(Icons.Default.StarBorder, null, tint = Color.LightGray, modifier = Modifier.size(16.dp)) }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Sentence Card
            ContentBoxDetail(label = "文章") {
                Column {
                    FuriganaText(base = "牛乳", furigana = "ぎゅうにゅう")
                    Text(" をたくさん飲んだら、", fontSize = 18.sp)
                    FuriganaText(base = "背", furigana = "せ")
                    Text("が", fontSize = 18.sp)
                    FuriganaText(base = "伸", furigana = "の")
                    Text("びるかなぁ？", fontSize = 18.sp)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Meaning Card
            ContentBoxDetail(label = "意味") {
                Text(
                    text = "I wonder if drinking lots of milk will make me taller.",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Reading Guide Card
            ContentBoxDetail(label = "読み方", borderColor = Color.Red) {
                val readingStr = buildAnnotatedString {
                    append("ぎゅう")
                    withStyle(style = SpanStyle(color = Color.Red)) { append("ー") }
                    append("にゅう")
                    withStyle(style = SpanStyle(color = Color.Red)) { append("ー") }
                    append(" を たくさ")
                    withStyle(style = SpanStyle(color = Color.Red)) { append("ん") }
                    append(" の")
                    withStyle(style = SpanStyle(color = Color.Red)) { append("ん") }
                    append("だら、 ")
                    withStyle(style = SpanStyle(color = Color.Red)) { append("/") }
                    append("\nせ が のびる かなぁ？ ")
                    withStyle(style = SpanStyle(color = Color.Red)) { append("/") }
                }
                Text(text = readingStr, fontSize = 16.sp, lineHeight = 24.sp)
                
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Surface(color = Color.Red, shape = RoundedCornerShape(4.dp)) {
                        Text("说明", color = Color.White, fontSize = 10.sp, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Audio Controls
            Text("先生のお手本", color = Color.Red, fontSize = 12.sp, modifier = Modifier.fillMaxWidth())
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color.Red),
                    color = Color.Transparent
                ) {
                    Text("1.00 x", color = Color.Red, fontSize = 12.sp, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                }
                
                Spacer(modifier = Modifier.width(8.dp))
                
                Slider(
                    value = 0f,
                    onValueChange = {},
                    modifier = Modifier.weight(1f),
                    colors = SliderDefaults.colors(thumbColor = Color.Red, activeTrackColor = Color.Red)
                )
                
                IconButton(onClick = {}) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color.Red)
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text("下的按钮をクリックすると、録音します", fontSize = 12.sp, color = Color.Gray)
            
            FloatingActionButton(
                onClick = { },
                containerColor = Color.Red,
                contentColor = Color.White,
                shape = CircleShape,
                modifier = Modifier.size(64.dp)
            ) {
                Icon(Icons.Default.Mic, contentDescription = null, modifier = Modifier.size(32.dp))
            }
        }
    }
}

@Composable
fun ContentBoxDetail(label: String, borderColor: Color = Color.LightGray, content: @Composable () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, fontSize = 10.sp, color = Color.Gray, modifier = Modifier.padding(start = 4.dp, bottom = 2.dp))
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, borderColor),
            color = Color.Transparent
        ) {
            Box(modifier = Modifier.padding(12.dp)) {
                content()
            }
        }
    }
}

@Composable
fun FuriganaText(base: String, furigana: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(horizontal = 1.dp)) {
        Text(text = furigana, fontSize = 10.sp, color = Color.Gray)
        Text(text = base, fontSize = 18.sp)
    }
}
