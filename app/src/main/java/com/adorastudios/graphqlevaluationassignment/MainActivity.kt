package com.adorastudios.graphqlevaluationassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adorastudios.graphqlevaluationassignment.presentation.screens.Screens
import com.adorastudios.graphqlevaluationassignment.presentation.screens.screenAddLead.AddLeadScreen
import com.adorastudios.graphqlevaluationassignment.presentation.screens.screenLeadList.LeadListScreen
import com.adorastudios.graphqlevaluationassignment.presentation.theme.GraphQLEvaluationAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GraphQLEvaluationAssignmentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screens.LeadListScreen.route,
                    ) {
                        composable(
                            route = Screens.LeadListScreen.route,
                        ) {
                            LeadListScreen(navController = navController)
                        }
                        composable(
                            route = Screens.AddLeadScreen.route,
                        ) {
                            AddLeadScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
