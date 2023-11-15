package com.adorastudios.graphqlevaluationassignment.presentation.screens.screenAddLead

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adorastudios.graphqlevaluationassignment.R
import com.adorastudios.graphqlevaluationassignment.domain.LeadRepository
import com.adorastudios.graphqlevaluationassignment.domain.Toaster
import com.adorastudios.graphqlevaluationassignment.presentation.screens.screenAddLead.inputField.InputFieldOption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddLeadViewModel @Inject constructor(
    private val leadRepository: LeadRepository,
    private val toaster: Toaster,
) : ViewModel() {
    private val _state: MutableState<AddLeadState> = mutableStateOf(AddLeadState())
    val state: State<AddLeadState> = _state

    private val _uiEventFlow: MutableSharedFlow<AddLeadUiEvent> = MutableSharedFlow()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val countriesAsync = async { leadRepository.queryCountries() }
            val adSourcesAsync = async { leadRepository.queryAdSources() }
            val languagesAsync = async { leadRepository.queryLanguages() }
            val intentionsAsync = async { leadRepository.queryIntentions() }

            val countries = countriesAsync.await()
            val adSources = adSourcesAsync.await()
            val languages = languagesAsync.await()
            val intentions = intentionsAsync.await()

            withContext(Dispatchers.Main) {
                _state.value = state.value.copy(
                    languages = languages.getOrNull()?.languages?.map {
                        InputFieldOption(it.id, it.title)
                    } ?: listOf(),
                    adSources = adSources.getOrNull()?.fetchAdSources?.map {
                        InputFieldOption(it.id, it.title)
                    } ?: listOf(),
                    countries = countries.getOrNull()?.fetchCountries?.map {
                        InputFieldOption(it.id, it.title)
                    } ?: listOf(),
                    leadIntentions = intentions.getOrNull()?.fetchLeadIntentionTypes?.map {
                        InputFieldOption(it.id, it.title)
                    } ?: listOf(),
                )
            }
        }
    }

    fun onEvent(event: AddLeadEvent) {
        when (event) {
            is AddLeadEvent.ChangeAdSource -> {
                _state.value = state.value.copy(
                    adSource = event.option,
                )
            }

            is AddLeadEvent.ChangeCountry -> {
                _state.value = state.value.copy(
                    country = event.option,
                )
            }

            is AddLeadEvent.ChangeFirstName -> {
                _state.value = state.value.copy(
                    firstName = event.value,
                )
            }

            is AddLeadEvent.ChangeLanguage -> {
                _state.value = state.value.copy(
                    language = event.option,
                )
            }

            is AddLeadEvent.ChangeLeadIntention -> {
                _state.value = state.value.copy(
                    leadIntention = event.option,
                )
            }

            is AddLeadEvent.ChangePhoneNumber -> {
                _state.value = state.value.copy(
                    phoneNumber = event.value,
                )
            }

            is AddLeadEvent.ChangeSecondName -> {
                _state.value = state.value.copy(
                    secondName = event.value,
                )
            }

            AddLeadEvent.Save -> {
                if (dataIsCorrect()) {
                    viewModelScope.launch(Dispatchers.IO) {
                        val result = leadRepository.mutationLead(
                            phoneNumber = state.value.phoneNumber,
                            firstName = state.value.firstName,
                            secondName = state.value.secondName,
                            countryId = state.value.country?.id ?: return@launch,
                            languageIds = listOf(state.value.country?.id ?: return@launch),
                            adSource = state.value.adSource?.id ?: return@launch,
                            intentionId = state.value.leadIntention?.id ?: return@launch,
                        )
                        if (result.isSuccess) {
                            toaster.show(R.string.addLeadScreen_leadCreated)
                        } else {
                            toaster.show(R.string.addLeadScreen_error)
                        }
                    }.invokeOnCompletion {
                        viewModelScope.launch {
                            _uiEventFlow.emit(AddLeadUiEvent.Back)
                        }
                    }
                } else {
                    viewModelScope.launch(Dispatchers.Main) {
                        toaster.show(R.string.addLeadScreen_someFieldsAreEmpty)
                    }
                }
            }
        }
    }

    private fun dataIsCorrect() = _state.value.adSource != null && _state.value.country != null &&
        _state.value.language != null && _state.value.leadIntention != null &&
        _state.value.firstName.isNotBlank() && _state.value.secondName.isNotBlank() &&
        _state.value.phoneNumber.isNotBlank()
}
