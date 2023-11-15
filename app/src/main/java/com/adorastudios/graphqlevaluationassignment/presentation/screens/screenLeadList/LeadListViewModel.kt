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

    private var cursor: String = ""
    private var hasMore: Boolean = false

    init {
        loadSomeMore()
    }

    fun loadSomeMore() {
        viewModelScope.launch(Dispatchers.IO) {
            val (leads, error) = leadRepository.queryLeads(cursor).run {
                cursor = getOrNull()?.fetchLeads?.cursor ?: ""
                hasMore = getOrNull()?.fetchLeads?.hasMore ?: false
                getOrNull()?.fetchLeads?.data to exceptionOrNull()?.message
            }

            withContext(Dispatchers.Main) {
                if (leads != null) {
                    val displayedLeads =
                        (_state.value.loadingState as? LoadingState.Loaded)?.leads?.toMutableList()
                            ?: mutableListOf()
                    displayedLeads.addAll(leads)
                    _state.value = state.value.copy(
                        loadingState = LoadingState.Loaded(displayedLeads, hasMore),
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
