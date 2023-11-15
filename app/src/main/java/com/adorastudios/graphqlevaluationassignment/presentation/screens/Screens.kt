package com.adorastudios.graphqlevaluationassignment.presentation.screens

sealed class Screens(val route: String) {
    object LeadListScreen : Screens("leadL_lst")
    object AddLeadScreen : Screens("lead")
}
