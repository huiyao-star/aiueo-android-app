package com.example.aiueo.model

data class GrammarLesson(
    val id: String,
    val title: String,
    val level: String, // N5, N4, etc.
    val description: String,
    val examples: List<ExampleSentence>
)

data class ExampleSentence(
    val japanese: String,
    val reading: String,
    val english: String
)
