package com.example.aiueo.data

import com.example.aiueo.model.Vocabulary

object VocabularyData {
    val basicVocab = listOf(
        Vocabulary("私", "わたし", "I, me", "Pronoun"),
        Vocabulary("あなた", "あなた", "You", "Pronoun"),
        Vocabulary("先生", "せんせい", "Teacher", "People"),
        Vocabulary("学生", "がくせい", "Student", "People"),
        Vocabulary("日本語", "にほんご", "Japanese Language", "Language"),
        Vocabulary("本", "ほん", "Book", "Object"),
        Vocabulary("車", "くるま", "Car", "Object"),
        Vocabulary("食べる", "たべる", "To eat", "Verb"),
        Vocabulary("飲む", "のむ", "To drink", "Verb"),
        Vocabulary("大きい", "おおきい", "Big", "Adjective")
    )
}
