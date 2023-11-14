package com.adorastudios.graphqlevaluationassignment.presentation.screens.screenLeadList

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adorastudios.graphqlevaluationassignment.domain.LeadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LeadListViewModel @Inject constructor(
    private val leadRepository: LeadRepository,
) : ViewModel() {
    private val _state: MutableState<LeadListState> = mutableStateOf(LeadListState())
    val state: State<LeadListState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val (leads, error) = leadRepository.queryLeads().run {
                getOrNull()?.fetchLeads?.data to exceptionOrNull()?.message
            }

            withContext(Dispatchers.Main) {
                if (leads != null) {
                    _state.value = state.value.copy(
                        loadingState = LoadingState.Loaded(leads),
                    )
                } else {
                    _state.value = state.value.copy(
                        loadingState = LoadingState.Failed(error ?: ""),
                    )
                }
            }
        }
    }
}
