package com.example.aiueo.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "study_prefs")

class StudyPreferences(private val context: Context) {

    companion object {
        val TOTAL_STUDY_TIME = longPreferencesKey("total_study_time_seconds")
        val TODAY_STUDY_TIME = longPreferencesKey("today_study_time_seconds")
        val HIGH_SCORE = intPreferencesKey("high_score")
        val LAST_STUDY_DATE = stringPreferencesKey("last_study_date")
    }

    val totalStudyTime: Flow<Long> = context.dataStore.data.map { it[TOTAL_STUDY_TIME] ?: 0L }
    val todayStudyTime: Flow<Long> = context.dataStore.data.map { it[TODAY_STUDY_TIME] ?: 0L }
    val highScore: Flow<Int> = context.dataStore.data.map { it[HIGH_SCORE] ?: 0 }

    suspend fun addStudyTime(seconds: Long) {
        context.dataStore.edit { prefs ->
            val currentTotal = prefs[TOTAL_STUDY_TIME] ?: 0L
            val currentToday = prefs[TODAY_STUDY_TIME] ?: 0L
            prefs[TOTAL_STUDY_TIME] = currentTotal + seconds
            prefs[TODAY_STUDY_TIME] = currentToday + seconds
        }
    }

    suspend fun updateHighScore(score: Int) {
        context.dataStore.edit { prefs ->
            val currentHigh = prefs[HIGH_SCORE] ?: 0
            if (score > currentHigh) {
                prefs[HIGH_SCORE] = score
            }
        }
    }

    suspend fun resetTodayTimeIfNeeded(currentDate: String) {
        context.dataStore.edit { prefs ->
            val lastDate = prefs[LAST_STUDY_DATE] ?: ""
            if (lastDate != currentDate) {
                prefs[TODAY_STUDY_TIME] = 0L
                prefs[LAST_STUDY_DATE] = currentDate
            }
        }
    }
}
