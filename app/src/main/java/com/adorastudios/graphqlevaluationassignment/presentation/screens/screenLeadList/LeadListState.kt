package com.adorastudios.graphqlevaluationassignment.presentation.screens.screenLeadList

import com.adorastudios.graphqlevaluationassignment.com.adorastudios.graphqlevaluationassignment.FetchLeadsQuery

data class LeadListState(
    val loadingState: LoadingState = LoadingState.Loading,
)

sealed class LoadingState {
    data class Loaded(
        val leads: List<FetchLeadsQuery.Data1>,
        val hasMore: Boolean,
    ) : LoadingState()

    object Loading : LoadingState()
    data class Failed(val message: String) : LoadingState()
}
