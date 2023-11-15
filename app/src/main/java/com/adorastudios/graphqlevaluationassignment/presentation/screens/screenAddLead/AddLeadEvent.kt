package com.adorastudios.graphqlevaluationassignment.presentation.screens.screenAddLead

import com.adorastudios.graphqlevaluationassignment.presentation.screens.screenAddLead.inputField.InputFieldOption

sealed class AddLeadEvent {
    data class ChangeFirstName(val value: String) : AddLeadEvent()
    data class ChangeSecondName(val value: String) : AddLeadEvent()
    data class ChangeLeadIntention(val option: InputFieldOption) : AddLeadEvent()
    data class ChangeAdSource(val option: InputFieldOption) : AddLeadEvent()
    data class ChangeCountry(val option: InputFieldOption) : AddLeadEvent()
    data class ChangeLanguage(val option: InputFieldOption) : AddLeadEvent()
    data class ChangePhoneNumber(val value: String) : AddLeadEvent()
    object Save : AddLeadEvent()
}
