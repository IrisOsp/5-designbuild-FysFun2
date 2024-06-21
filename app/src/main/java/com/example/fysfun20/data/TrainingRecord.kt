package com.example.fysfun20.data


import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class TrainingRecord(
    val exerciseName: String,
    val rep: Int,
    val weight: String,
    val execute: String,
    val date: LocalDate
    )
