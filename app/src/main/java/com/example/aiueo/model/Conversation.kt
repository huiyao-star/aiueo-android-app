package com.example.aiueo.model

data class Conversation(
    val id: String,
    val title: String,
    val scene: String,
    val dialogues: List<DialogueLine>
)

data class DialogueLine(
    val character: String,
    val text: String,
    val reading: String,
    val translation: String,
    val audioUrl: String = ""
)
