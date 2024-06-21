package com.example.fysfun20.data

import kotlinx.serialization.Serializable

@Serializable
data class QuestionnaireHistory (
    val questionnaireRecords: List<QuestionnaireRecord>
)