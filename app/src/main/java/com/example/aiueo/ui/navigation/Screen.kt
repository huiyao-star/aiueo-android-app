package com.example.aiueo.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Translate
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    // Bottom Bar Screens
    object Kana : Screen("kana", "五十音", Icons.Default.Translate)
    object Vocabulary : Screen("vocabulary", "单词", Icons.AutoMirrored.Filled.List)
    object Grammar : Screen("grammar", "文法", Icons.Default.Book)
    object Reading : Screen("reading", "音读", Icons.Default.Mic)
    object Info : Screen("info", "情报", Icons.Default.Info)

    // Detail Screens
    object GrammarDetail : Screen("grammar_detail", "文法详情", Icons.Default.Book)
    object ReadingDetail : Screen("reading_detail", "音读详情", Icons.Default.Mic)
    object SurnameRank : Screen("surname_rank", "姓氏排行", Icons.AutoMirrored.Filled.List)
    object WordGame : Screen("word_game", "单词游戏", Icons.Default.Translate)
    object GojuonTest : Screen("gojuon_test", "五十音测试", Icons.Default.Quiz)

    companion object {
        val items = listOf(Kana, Vocabulary, Grammar, Reading, Info)
    }
}
