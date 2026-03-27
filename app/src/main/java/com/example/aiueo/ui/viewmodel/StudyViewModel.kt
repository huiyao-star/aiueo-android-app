package com.example.aiueo.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.aiueo.data.StudyPreferences
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class StudyViewModel(application: Application) : AndroidViewModel(application) {
    private val prefs = StudyPreferences(application)

    val totalStudyTime = prefs.totalStudyTime.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0L)
    val todayStudyTime = prefs.todayStudyTime.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0L)
    val highScore = prefs.highScore.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0)

    init {
        // Daily reset check
        viewModelScope.launch {
            val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            prefs.resetTodayTimeIfNeeded(currentDate)
        }

        // Increment study time every minute
        viewModelScope.launch {
            while (true) {
                delay(60000) // 1 minute
                prefs.addStudyTime(60)
            }
        }
    }

    fun updateScore(score: Int) {
        viewModelScope.launch {
            prefs.updateHighScore(score)
        }
    }

    fun formatTime(seconds: Long): String {
        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        return if (hours > 0) "${hours}小时${minutes}分" else "${minutes}分"
    }
}
