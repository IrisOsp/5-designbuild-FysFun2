package com.example.fysfun20.data

import kotlinx.serialization.Serializable

@Serializable
data class TrainingHistory (
    val trainingRecords: List<TrainingRecord>
)
