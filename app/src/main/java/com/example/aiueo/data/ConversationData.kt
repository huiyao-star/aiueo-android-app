package com.example.aiueo.data

import com.example.aiueo.model.Conversation
import com.example.aiueo.model.DialogueLine

object ConversationData {
    val sampleConversations = listOf(
        Conversation(
            id = "1",
            title = "在便利店",
            scene = "Convenience Store",
            dialogues = listOf(
                DialogueLine("店員", "いらっしゃいませ。", "いらっしゃいませ。", "Welcome."),
                DialogueLine("客", "これをお願いします。", "これをおねがいします。", "This one, please."),
                DialogueLine("店員", "500円です。", "ごひゃくえんです。", "That will be 500 yen."),
                DialogueLine("客", "はい、どうぞ。", "はい、どうぞ。", "Here you go.")
            )
        ),
        Conversation(
            id = "2",
            title = "问路",
            scene = "Asking Directions",
            dialogues = listOf(
                DialogueLine("通行人A", "すみません、駅はどこですか。", "すみません、えきはどこですか。", "Excuse me, where is the station?"),
                DialogueLine("通行人B", "あそこです。", "あそこです。", "It's over there.")
            )
        )
    )
}
