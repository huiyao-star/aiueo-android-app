package com.example.aiueo.data

import com.example.aiueo.model.ExampleSentence
import com.example.aiueo.model.GrammarLesson

object GrammarData {
    val n5Lessons = listOf(
        GrammarLesson(
            id = "1",
            title = "～は～です",
            level = "N5",
            description = "Topic marker and state-of-being.",
            examples = listOf(
                ExampleSentence("私は学生です。", "わたしはがくせいです。", "I am a student."),
                ExampleSentence("これは本です。", "これはほんです。", "This is a book.")
            )
        ),
        GrammarLesson(
            id = "2",
            title = "～も～です",
            level = "N5",
            description = "Inclusive marker 'also' or 'too'.",
            examples = listOf(
                ExampleSentence("私も学生です。", "わたしもがくせいです。", "I am also a student."),
                ExampleSentence("それも本です。", "それもほんです。", "That is also a book.")
            )
        ),
        GrammarLesson(
            id = "3",
            title = "～の～",
            level = "N5",
            description = "Possessive marker or connecting nouns.",
            examples = listOf(
                ExampleSentence("私の本です。", "わたしのほんです。", "It is my book."),
                ExampleSentence("日本語の先生です。", "にほんごのせんせいです。", "I am a Japanese teacher.")
            )
        )
    )
}
