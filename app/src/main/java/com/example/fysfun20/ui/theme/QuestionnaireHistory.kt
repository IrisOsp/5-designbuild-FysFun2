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
import com.example.fysfun20.data.QuestionnaireRecord

@Composable
fun QuestionnaireHistory(list: List<QuestionnaireRecord>, onClearDataClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Tidligere spørgeskema", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onClearDataClicked) {
            Text(text = "Slet tidligere besvarelser",
                color = Color.Black)
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(list.size) { index ->
                QuestionnaireRecordCard(questionnaireRecord = list[index])
            }
        }
    }
}

@Composable
fun QuestionnaireRecordCard(questionnaireRecord: QuestionnaireRecord) {
    val date = questionnaireRecord.date
    val formattedDate = "${date.dayOfMonth.toString().padStart(2, '0')}-${date.monthNumber.toString().padStart(2, '0')}-${date.year}"
    Card {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Dato:$formattedDate")
            Text(text = "Smerteniveau: ${questionnaireRecord.quality.level}")
            Text(text = "Smertestillende: ${questionnaireRecord.painMedicine}")
        }
    }
}

