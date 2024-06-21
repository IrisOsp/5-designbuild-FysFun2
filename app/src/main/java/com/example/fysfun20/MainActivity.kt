package com.example.fysfun20

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fysfun20.data.QuestionnaireRepository
import com.example.fysfun20.ui.theme.theme.FysfunTheme
import com.example.fysfun20.ui.theme.MainScreen
import com.example.fysfun20.ui.theme.Questionnaire
import com.example.fysfun20.ui.theme.QuestionnaireHistory
import com.example.fysfun20.ui.theme.Statistics
import com.example.fysfun20.ui.theme.Training
import com.example.fysfun20.ui.theme.UserSettings
import com.example.fysfun20.data.TrainingRepository
import com.example.fysfun20.ui.theme.TrainingHistory
import com.example.fysfun20.ui.theme.EndSite


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val questionnaireRepository = QuestionnaireRepository(context=this )
        var questionnaireHistory by mutableStateOf(questionnaireRepository.getQuestionnaireHistory())
        questionnaireRepository.listenToHistoryChanges { questionnaireHistory = it }
        val trainingRepository = TrainingRepository(context=this )
        var trainingHistory by mutableStateOf(trainingRepository.getTrainingHistory())
        trainingRepository.listenToHistoryChanges { trainingHistory = it }
        setContent {
            FysfunTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {

                    val navController = rememberNavController()

                    Scaffold(
                        topBar = {
                            TopAppBar(
                                colors = topAppBarColors(
                                    titleContentColor = MaterialTheme.colorScheme.primary,
                                ),

                                title = {
                                },
                                navigationIcon = {
                                    Icon(
                                        painter = painterResource(R.drawable.logo),
                                        contentDescription = null,
                                        modifier=Modifier.height(70.dp)
                                    )
                                },
                            )
                        },
                        bottomBar = {
                            NavigationBar(
                                content = {
                                    var selectedItemIndex by remember {
                                        mutableIntStateOf(0)
                                    }

                                    NavigationBarItem(
                                        selected = selectedItemIndex == 0,
                                        onClick = {
                                            selectedItemIndex = 0
                                            navController.navigate("mainScreen")
                                        },
                                        icon = {
                                            Column(
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.Center,
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Filled.Home,
                                                    contentDescription = "Localized description"
                                                )
                                                Text("Hjem")
                                            }
                                        },
                                        label = { }
                                    )
                                    NavigationBarItem(
                                        selected = selectedItemIndex == 1,
                                        onClick = {
                                            selectedItemIndex = 1
                                            navController.navigate("statistics")
                                        },
                                        icon = {
                                            Column(
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.Center,) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.statistics),
                                                    contentDescription = "Localized description",
                                                    modifier = Modifier.size(24.dp)
                                                )
                                                Text("Statistik")
                                            }
                                        },
                                        label = {    }
                                    )



                                    NavigationBarItem(
                                        selected = selectedItemIndex == 2,
                                        onClick = {
                                            selectedItemIndex = 2
                                            navController.navigate("settings")
                                        },
                                        icon = {
                                            Column(
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.Center,
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Filled.Settings,
                                                    contentDescription = "Localized description"
                                                )
                                                Text("Indstillinger",
                                                    textAlign = TextAlign.Center,
                                                    modifier = Modifier.padding(top = 4.dp))
                                            }
                                        },
                                        label = { }
                                    )

                                },
                            )

                        }

                    ) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {

                            NavHost(
                                navController = navController,
                                startDestination = "mainScreen"
                            ) {
                                composable(route = "mainScreen") {
                                    MainScreen(
                                        navController = navController
                                    )
                                }
                                composable(route = "statistics") {
                                    Statistics(
                                        navController = navController
                                    )
                                }
                                composable(route = "questionnaire") {
                                    Questionnaire(
                                        onSaveClicked = { questionnaireRecord ->
                                            questionnaireRepository.addQuestionnaireRecord(questionnaireRecord = questionnaireRecord)
                                            println("Training Record Saved: $questionnaireRecord")
                                            Log.d("Retrive test of item", questionnaireRepository.getQuestionnaireHistory().toString())
                                        }
                                    )
                                }
                                composable(route = "training") {
                                    Training(
                                        navController = navController,
                                        onSaveClicked = { trainingRecord ->
                                            trainingRepository.addTrainingRecord(trainingRecord = trainingRecord)
                                            println("Training Record Saved: $trainingRecord")
                                            Log.d("Retrieve test of item", trainingRepository.getTrainingHistory().toString())
                                        }
                                    )
                                }

                                composable(route = "settings") {
                                    UserSettings(
                                    )
                                }
                                composable(route = "questionnairehistory") {
                                    QuestionnaireHistory(
                                        list = questionnaireHistory.questionnaireRecords,
                                        onClearDataClicked = questionnaireRepository::deleteData

                                    )
                                }
                                composable(route = "traininghistory") {
                                    TrainingHistory(
                                        list = trainingHistory.trainingRecords,
                                        onClearDataClicked = trainingRepository::deleteData

                                    )
                                }
                                composable(route = "endsite") {
                                    EndSite(
                                    )
                                }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
