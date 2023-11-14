package com.adorastudios.graphqlevaluationassignment.presentation.screens.screenLeadList.header

data class Action(
    val iconId: Int,
    val action: () -> Unit,
    val textDescription: String?,
)
