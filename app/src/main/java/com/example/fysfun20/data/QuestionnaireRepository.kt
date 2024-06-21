package com.example.fysfun20.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private const val KEY_HISTORY = "history"

class QuestionnaireRepository(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    private var changeListener: SharedPreferences.OnSharedPreferenceChangeListener? = null

    fun getQuestionnaireHistory() = getQuestionnaireHistory(sharedPreferences)

    private fun getQuestionnaireHistory(sharedPreferences: SharedPreferences): QuestionnaireHistory {
        val historyJsonString = sharedPreferences.getString(KEY_HISTORY, null)
        return if (historyJsonString == null) {
            QuestionnaireHistory(emptyList())
        } else {
            Json.decodeFromString(historyJsonString)
        }
    }

    fun deleteData() {
        sharedPreferences.edit {
            remove(KEY_HISTORY)
        }
    }

    fun addQuestionnaireRecord(questionnaireRecord: QuestionnaireRecord) {
        val currentQuestionnaireHistory = getQuestionnaireHistory()

        val updatedQuestionnaireRecordsList = currentQuestionnaireHistory.questionnaireRecords + questionnaireRecord
        val updatedHistory = QuestionnaireHistory(updatedQuestionnaireRecordsList)

        val updatedHistoryJsonString = Json.encodeToString(updatedHistory)

        sharedPreferences.edit {
            putString(KEY_HISTORY, updatedHistoryJsonString)
        }
    }

    fun listenToHistoryChanges(onUpdate: (QuestionnaireHistory) -> Unit) {
        changeListener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
            onUpdate(getQuestionnaireHistory(sharedPreferences))
        }
        sharedPreferences.registerOnSharedPreferenceChangeListener(changeListener)
    }


}