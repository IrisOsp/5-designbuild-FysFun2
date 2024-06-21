package com.example.fysfun20.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun MainScreen(
    navController: NavController
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text =
            """
                Velkommen tilbage 
                
                Sten Børge Hansen
               
            """.trimIndent(),
        )
        Button(
            onClick = { navController.navigate("training")},
            modifier = Modifier
                .padding(8.dp)
                .width(250.dp)
                .border(
                    BorderStroke(4.dp, Color.White),
                    shape = RoundedCornerShape(70.dp)
                )
        ) {
            Text("START TRÆNING",
                color = Color.Black)
        }
        Button(
            onClick = { navController.navigate("questionnaire") },
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
                .width(250.dp)
                .border(
                    BorderStroke(4.dp, Color.White),
                    shape = RoundedCornerShape(70.dp)
                )

        ) {
            Text("SPØRGESKEMA - SMERTER",
                color = Color.Black)
        }


    }

}