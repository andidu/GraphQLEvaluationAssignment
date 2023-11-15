package com.adorastudios.graphqlevaluationassignment.presentation.screens.screenAddLead

import com.adorastudios.graphqlevaluationassignment.presentation.screens.screenAddLead.inputField.InputFieldOption

data class AddLeadState(
    val firstName: String = "",
    val secondName: String = "",
    val leadIntention: InputFieldOption? = null,
    val leadIntentions: List<InputFieldOption> = emptyList(),
    val adSource: InputFieldOption? = null,
    val adSources: List<InputFieldOption> = emptyList(),
    val country: InputFieldOption? = null,
    val countries: List<InputFieldOption> = emptyList(),
    val language: InputFieldOption? = null,
    val languages: List<InputFieldOption> = emptyList(),
    val phoneNumber: String = "",
)
