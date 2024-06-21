package com.example.fysfun20.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fysfun20.data.TrainingRecord
import kotlinx.datetime.LocalDate

@Composable
fun TrainingHistory(list: List<TrainingRecord>, onClearDataClicked: () -> Unit) {
    val groupedRecords = list.groupBy { it.date }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Tidligere træning", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onClearDataClicked) {
            Text(text = "Slet tidligere besvarelser", color = Color.Black)
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            groupedRecords.forEach { (date, records) ->
                item {
                    TrainingRecordCardGroup(date = date, trainingRecords = records)
                }
            }
        }
    }
}

@Composable
fun TrainingRecordCardGroup(date: LocalDate, trainingRecords: List<TrainingRecord>) {
    val formattedDate = "${date.dayOfMonth.toString().padStart(2, '0')}-${date.monthNumber.toString().padStart(2, '0')}-${date.year}"

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Dato: $formattedDate", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            trainingRecords.forEach { record ->
                TrainingRecordCard(trainingRecord = record)
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

@Composable
fun TrainingRecordCard(trainingRecord: TrainingRecord) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Øvelse: ${trainingRecord.exerciseName}")
            Text(text = "Antal gentagelser: ${trainingRecord.rep}")
            Text(text = "Belastning: ${trainingRecord.weight}")
            Text(text = "Kunne ikke udføre: ${trainingRecord.execute}")
        }
    }
}