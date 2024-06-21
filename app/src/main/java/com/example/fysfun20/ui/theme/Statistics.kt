package com.example.fysfun20.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Statistics(
    navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Statistik",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = { navController.navigate("traininghistory")},
                modifier = Modifier
                    .padding(8.dp)
                    .width(250.dp)
                    .border(
                        BorderStroke(4.dp, Color.White),
                        shape = RoundedCornerShape(70.dp)
                    )
            ){
                Text("TRÆNINGSHISTORIK",
                    color = Color.Black)
            }
            Button(
                onClick = { navController.navigate("questionnairehistory") },
                modifier = Modifier
                    .padding(8.dp)
                    .width(250.dp)
                    .border(
                        BorderStroke(4.dp, Color.White),
                        shape = RoundedCornerShape(70.dp)
                    )
            ){
                Text("SPØRGESKEMAHISTORIK",
                    color = Color.Black)
            }
        }
    }
}