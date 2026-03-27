package com.example.aiueo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.aiueo.ui.navigation.Screen
import com.example.aiueo.ui.screens.*
import com.example.aiueo.ui.theme.AiueoTheme
import com.example.aiueo.ui.viewmodel.StudyViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AiueoTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val studyViewModel: StudyViewModel = viewModel()
    
    // Hide navigation bar on detail screens
    val showBottomBar = Screen.items.any { it.route == currentDestination?.route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    Screen.items.forEach { screen ->
                        NavigationBarItem(
                            icon = { Icon(screen.icon, contentDescription = null) },
                            label = { Text(screen.title) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.Kana.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.Kana.route) { 
                KanaScreen(onNavigateToTest = { navController.navigate(Screen.GojuonTest.route) }) 
            }
            composable(Screen.Vocabulary.route) { 
                val todayTime by studyViewModel.todayStudyTime.collectAsState()
                val totalTime by studyViewModel.totalStudyTime.collectAsState()
                
                VocabularyScreen(
                    onNavigateToSurname = { navController.navigate(Screen.SurnameRank.route) },
                    onNavigateToGame = { navController.navigate(Screen.WordGame.route) },
                    todayStudyTimeText = studyViewModel.formatTime(todayTime),
                    totalStudyTimeText = studyViewModel.formatTime(totalTime)
                ) 
            }
            composable(Screen.Grammar.route) { 
                GrammarScreen(onNavigateToDetail = { navController.navigate(Screen.GrammarDetail.route) }) 
            }
            composable(Screen.Reading.route) { 
                val todayTime by studyViewModel.todayStudyTime.collectAsState()
                val totalTime by studyViewModel.totalStudyTime.collectAsState()
                
                ReadingScreen(
                    onNavigateToDetail = { navController.navigate(Screen.ReadingDetail.route) },
                    todayStudyTimeText = studyViewModel.formatTime(todayTime),
                    totalStudyTimeText = studyViewModel.formatTime(totalTime)
                ) 
            }
            composable(Screen.Info.route) { InfoScreen() }
            
            // Detail Screens
            composable(Screen.GrammarDetail.route) { 
                GrammarDetailScreen(onBack = { navController.popBackStack() }) 
            }
            composable(Screen.ReadingDetail.route) { 
                ReadingDetailScreen(onBack = { navController.popBackStack() }) 
            }
            composable(Screen.SurnameRank.route) { 
                SurnameRankScreen(onBack = { navController.popBackStack() }) 
            }
            composable(Screen.WordGame.route) { 
                val highScore by studyViewModel.highScore.collectAsState()
                WordGameScreen(
                    onBack = { navController.popBackStack() },
                    highScore = highScore,
                    onUpdateScore = { studyViewModel.updateScore(it) }
                )
            }
            composable(Screen.GojuonTest.route) { 
                GojuonTestScreen(onBack = { navController.popBackStack() })
            }
        }
    }
}
