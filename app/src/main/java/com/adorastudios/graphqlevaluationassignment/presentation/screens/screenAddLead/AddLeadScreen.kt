package com.adorastudios.graphqlevaluationassignment.presentation.screens.screenAddLead

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.adorastudios.graphqlevaluationassignment.R
import com.adorastudios.graphqlevaluationassignment.presentation.screens.screenAddLead.inputField.InputField
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddLeadScreen(
    navController: NavController,
    viewModel: AddLeadViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEventFlow.collectLatest { event ->
            when (event) {
                AddLeadUiEvent.Back -> {
                    navController.popBackStack()
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        HeaderTile(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textId = R.string.header_createLead,
            onCLickBack = {
                navController.popBackStack()
            },
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                contentPadding = PaddingValues(
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 96.dp,
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                item {
                    InputField(
                        modifier = Modifier.fillMaxWidth(),
                        titleId = R.string.addLeadScreen_firstName,
                        value = viewModel.state.value.firstName,
                        placeholderId = R.string.addLeadScreen_firstNamePlaceholder,
                        onValueChanged = {
                            viewModel.onEvent(AddLeadEvent.ChangeFirstName(it))
                        },
                    )
                }
                item {
                    InputField(
                        modifier = Modifier.fillMaxWidth(),
                        titleId = R.string.addLeadScreen_secondName,
                        value = viewModel.state.value.secondName,
                        placeholderId = R.string.addLeadScreen_secondNamePlaceholder,
                        onValueChanged = {
                            viewModel.onEvent(AddLeadEvent.ChangeSecondName(it))
                        },
                    )
                }
                item {
                    InputField(
                        modifier = Modifier.fillMaxWidth(),
                        titleId = R.string.addLeadScreen_leadIntention,
                        value = viewModel.state.value.leadIntention?.value ?: "",
                        placeholderId = R.string.addLeadScreen_selectPlaceholder,
                        onValueChanged = {
                            viewModel.onEvent(AddLeadEvent.ChangeLeadIntention(it))
                        },
                        options = viewModel.state.value.leadIntentions,
                    )
                }
                item {
                    InputField(
                        modifier = Modifier.fillMaxWidth(),
                        titleId = R.string.addLeadScreen_adSource,
                        value = viewModel.state.value.adSource?.value ?: "",
                        placeholderId = R.string.addLeadScreen_selectPlaceholder,
                        onValueChanged = {
                            viewModel.onEvent(AddLeadEvent.ChangeAdSource(it))
                        },
                        options = viewModel.state.value.adSources,
                    )
                }
                item {
                    InputField(
                        modifier = Modifier.fillMaxWidth(),
                        titleId = R.string.addLeadScreen_country,
                        value = viewModel.state.value.country?.value ?: "",
                        placeholderId = R.string.addLeadScreen_selectPlaceholder,
                        onValueChanged = {
                            viewModel.onEvent(AddLeadEvent.ChangeCountry(it))
                        },
                        options = viewModel.state.value.countries,
                    )
                }
                item {
                    InputField(
                        modifier = Modifier.fillMaxWidth(),
                        titleId = R.string.addLeadScreen_language,
                        value = viewModel.state.value.language?.value ?: "",
                        placeholderId = R.string.addLeadScreen_selectPlaceholder,
                        onValueChanged = {
                            viewModel.onEvent(AddLeadEvent.ChangeLanguage(it))
                        },
                        options = viewModel.state.value.languages,
                    )
                }
                item {
                    InputField(
                        modifier = Modifier.fillMaxWidth(),
                        titleId = R.string.addLeadScreen_phoneNumber,
                        value = viewModel.state.value.phoneNumber,
                        placeholderId = R.string.addLeadScreen_phoneNumberPlaceholder,
                        onValueChanged = {
                            viewModel.onEvent(AddLeadEvent.ChangePhoneNumber(it))
                        },
                    )
                }
            }
            SaveMenu(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) {}
                    .padding(16.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xD9000000))
                    .padding(16.dp),
                onPositiveClick = {
                    viewModel.onEvent(AddLeadEvent.Save)
                },
                onNegativeClick = {
                    navController.popBackStack()
                },
            )
        }
    }
}
