package com.example.fysfun20.data

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class QuestionnaireRecord(
    val quality: PainQuality,
    val date: LocalDate,
    val painMedicine:String
)

enum class PainQuality(val level: Int) {
    LOW(0),
    MODERATE(1),
    HIGH(2),
    SEVERE(3),
    VERY_SEVERE(4)
}

enum class PainMedicine(val label:String){
    YES("Ja"),
    NO("Nej")
}


