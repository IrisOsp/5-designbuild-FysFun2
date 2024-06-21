package com.example.fysfun20.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import android.util.Log

private const val KEY_HISTORY_2 = "history_2"

class TrainingRepository(context: Context) {


    private val sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    private var changeListener: SharedPreferences.OnSharedPreferenceChangeListener? = null
    private val json = Json { ignoreUnknownKeys = true }
    fun getTrainingHistory() = getTrainingHistory(sharedPreferences)

    private fun getTrainingHistory(sharedPreferences: SharedPreferences): TrainingHistory {
        val historyJsonString = sharedPreferences.getString(KEY_HISTORY_2, null)
        Log.d("TrainingRepository", "Raw JSON string: $historyJsonString")
        return if (historyJsonString == null) {
            TrainingHistory(emptyList())
        } else {
            try {
                json.decodeFromString(historyJsonString)
            } catch (e: Exception) {
                TrainingHistory(emptyList())
            }
        }
    }


    fun deleteData() {
        sharedPreferences.edit {
            remove(KEY_HISTORY_2)
        }
    }

    fun addTrainingRecord(trainingRecord: TrainingRecord) {
        val currentTrainingHistory = getTrainingHistory()

        val updatedTrainingRecordsList = currentTrainingHistory.trainingRecords.toMutableList().apply {
            add(trainingRecord)
        }
        val updatedHistory = TrainingHistory(updatedTrainingRecordsList)

        val updatedHistoryJsonString = Json.encodeToString(updatedHistory)

        sharedPreferences.edit {
            putString(KEY_HISTORY_2, updatedHistoryJsonString)
        }
    }

    fun listenToHistoryChanges(onUpdate: (TrainingHistory) -> Unit) {
        changeListener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
            onUpdate(getTrainingHistory(sharedPreferences))
        }
        sharedPreferences.registerOnSharedPreferenceChangeListener(changeListener)
    }


}