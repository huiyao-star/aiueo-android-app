package com.example.aiueo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GojuonTestScreen(onBack: () -> Unit) {
    var currentStep by remember { mutableIntStateOf(1) }
    val totalSteps = 20
    var score by remember { mutableIntStateOf(0) }
    var selectedOption by remember { mutableStateOf<String?>(null) }
    var isCorrect by remember { mutableStateOf<Boolean?>(null) }

    // Mock question
    val question = "あ"
    val options = listOf("a", "i", "u", "e")
    val correctAnswer = "a"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("五十音测试") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.Close, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Progress
            Row(verticalAlignment = Alignment.CenterVertically) {
                LinearProgressIndicator(
                    progress = { currentStep.toFloat() / totalSteps },
                    modifier = Modifier.weight(1f).height(8.dp),
                    color = Color.Green,
                    trackColor = Color.Gray
                )
                Text(
                    text = "$currentStep / $totalSteps",
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(60.dp))

            Text("选出正确的读音", fontSize = 16.sp, color = Color.Gray)
            
            Spacer(modifier = Modifier.height(24.dp))

            // Large Question Character
            Surface(
                modifier = Modifier.size(120.dp),
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFF1E1E1E),
                border = androidx.compose.foundation.BorderStroke(2.dp, Color.DarkGray)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(question, fontSize = 64.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(60.dp))

            // Options Grid
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                options.chunked(2).forEach { rowOptions ->
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        rowOptions.forEach { option ->
                            val isThisSelected = selectedOption == option
                            val backgroundColor = when {
                                isThisSelected && option == correctAnswer -> Color.Green.copy(alpha = 0.2f)
                                isThisSelected && option != correctAnswer -> Color.Red.copy(alpha = 0.2f)
                                else -> Color(0xFF1E1E1E)
                            }
                            val borderColor = when {
                                isThisSelected && option == correctAnswer -> Color.Green
                                isThisSelected && option != correctAnswer -> Color.Red
                                else -> Color.DarkGray
                            }

                            Surface(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(80.dp)
                                    .clickable(enabled = selectedOption == null) {
                                        selectedOption = option
                                        isCorrect = option == correctAnswer
                                        if (isCorrect!!) score += 10
                                    },
                                shape = RoundedCornerShape(12.dp),
                                color = backgroundColor,
                                border = androidx.compose.foundation.BorderStroke(2.dp, borderColor)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(option, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            if (selectedOption != null) {
                Button(
                    onClick = {
                        if (currentStep < totalSteps) {
                            currentStep++
                            selectedOption = null
                            isCorrect = null
                        } else {
                            // Finish test
                        }
                    },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4081))
                ) {
                    Text("下一题", fontSize = 18.sp)
                }
            }
        }
    }
}
