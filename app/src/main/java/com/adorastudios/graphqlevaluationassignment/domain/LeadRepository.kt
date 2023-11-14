package com.adorastudios.graphqlevaluationassignment.domain

import com.adorastudios.graphqlevaluationassignment.com.adorastudios.graphqlevaluationassignment.CreateLeadMutation
import com.adorastudios.graphqlevaluationassignment.com.adorastudios.graphqlevaluationassignment.FetchAdSourcesQuery
import com.adorastudios.graphqlevaluationassignment.com.adorastudios.graphqlevaluationassignment.FetchCountriesQuery
import com.adorastudios.graphqlevaluationassignment.com.adorastudios.graphqlevaluationassignment.FetchLeadIntentionTypesQuery
import com.adorastudios.graphqlevaluationassignment.com.adorastudios.graphqlevaluationassignment.FetchLeadsQuery
import com.adorastudios.graphqlevaluationassignment.com.adorastudios.graphqlevaluationassignment.LanguagesQuery

interface LeadRepository {
    suspend fun queryLeads(): Result<FetchLeadsQuery.Data>
    suspend fun queryLanguages(): Result<LanguagesQuery.Data>
    suspend fun queryCountries(): Result<FetchCountriesQuery.Data>
    suspend fun queryAdSources(): Result<FetchAdSourcesQuery.Data>
    suspend fun queryIntentions(): Result<FetchLeadIntentionTypesQuery.Data>
    suspend fun mutationLead(
        phoneNumber: String,
        countryId: Int,
        firstName: String,
        secondName: String,
        intentionId: Int,
        languageIds: List<Int>,
        adSource: Int,
    ): Result<CreateLeadMutation.Data>
}
