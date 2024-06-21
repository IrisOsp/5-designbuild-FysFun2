package com.example.fysfun20.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fysfun20.data.PainMedicine
import com.example.fysfun20.data.PainQuality
import com.example.fysfun20.data.QuestionnaireRecord
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone

@Composable
fun Questionnaire(
    onSaveClicked: (QuestionnaireRecord) -> Unit
) {
    var showMessage by remember { mutableStateOf(false) }
    var messageText by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Spørgeskema",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            var painQuality by remember { mutableStateOf(PainQuality.LOW) }
            var painMedicine by remember { mutableStateOf(PainMedicine.NO.label) }

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Hvordan er dine smerter fra 0-4?")

                Row(modifier = Modifier.padding(top = 6.dp)) {
                    for (qualityType in PainQuality.values()) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            RadioButton(
                                selected = painQuality == qualityType,
                                onClick = { painQuality = qualityType },
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(
                                text = qualityType.level.toString() + "  ",
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Har du taget smertestillende i dag?")

                Row(verticalAlignment = Alignment.CenterVertically) {
                    for (medicineType in PainMedicine.entries.map { entry -> entry.label }) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            RadioButton(
                                selected = painMedicine == medicineType,
                                onClick = { painMedicine = medicineType },
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(text = medicineType)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val questionnaireRecord = QuestionnaireRecord(
                            quality = painQuality,
                            date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
                            painMedicine = painMedicine
                        )
                        onSaveClicked(questionnaireRecord)

                        showMessage = true
                        messageText = "Tak for din besvarelse!"

                        painQuality = PainQuality.LOW
                        painMedicine = PainMedicine.NO.label
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        "GEM SPØRGESKEMA",
                        color = Color.Black
                    )
                }

                if (showMessage) {
                    Text(
                        text = messageText,
                        color = Color.Black,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

