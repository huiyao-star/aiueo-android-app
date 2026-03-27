package com.example.aiueo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aiueo.data.ConversationData
import com.example.aiueo.model.DialogueLine

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversationScreen() {
    val conversation = ConversationData.sampleConversations.first()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(conversation.title) })
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Scene: ${conversation.scene}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(conversation.dialogues) { line ->
                    DialogueItem(line)
                }
            }
        }
    }
}

@Composable
fun DialogueItem(line: DialogueLine) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = line.character,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier.weight(1f).padding(top = 4.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(text = line.reading, fontSize = 12.sp, color = Color.Gray)
                    Text(text = line.text, fontSize = 18.sp, fontWeight = FontWeight.Medium)
                    Divider(modifier = Modifier.padding(vertical = 8.dp), thickness = 0.5.dp)
                    Text(text = line.translation, fontSize = 14.sp)
                }
            }
            
            Spacer(modifier = Modifier.width(8.dp))
            
            Column {
                IconButton(
                    onClick = { /* Play Audio */ },
                    modifier = Modifier.background(MaterialTheme.colorScheme.primary, CircleShape).size(32.dp)
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
                }
                Spacer(modifier = Modifier.height(8.dp))
                IconButton(
                    onClick = { /* Start Recording for Shadowing */ },
                    modifier = Modifier.background(MaterialTheme.colorScheme.secondary, CircleShape).size(32.dp)
                ) {
                    Icon(Icons.Default.Mic, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
                }
            }
        }
    }
}
