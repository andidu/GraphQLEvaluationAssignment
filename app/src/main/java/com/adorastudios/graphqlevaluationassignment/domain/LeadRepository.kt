package com.adorastudios.graphqlevaluationassignment.domain

import com.adorastudios.graphqlevaluationassignment.com.adorastudios.graphqlevaluationassignment.CreateLeadMutation
import com.adorastudios.graphqlevaluationassignment.com.adorastudios.graphqlevaluationassignment.FetchAdSourcesQuery
import com.adorastudios.graphqlevaluationassignment.com.adorastudios.graphqlevaluationassignment.FetchCountriesQuery
import com.adorastudios.graphqlevaluationassignment.com.adorastudios.graphqlevaluationassignment.FetchLeadIntentionTypesQuery
import com.adorastudios.graphqlevaluationassignment.com.adorastudios.graphqlevaluationassignment.FetchLeadsQuery
import com.adorastudios.graphqlevaluationassignment.com.adorastudios.graphqlevaluationassignment.LanguagesQuery
import com.apollographql.apollo3.api.ApolloResponse

interface LeadRepository {
    suspend fun queryLeads(): ApolloResponse<FetchLeadsQuery.Data>
    suspend fun queryLanguages(): ApolloResponse<LanguagesQuery.Data>
    suspend fun queryCountries(): ApolloResponse<FetchCountriesQuery.Data>
    suspend fun queryAdSources(): ApolloResponse<FetchAdSourcesQuery.Data>
    suspend fun queryIntentions(): ApolloResponse<FetchLeadIntentionTypesQuery.Data>
    suspend fun mutationLead(
        phoneNumber: String,
        countryId: Int,
        firstName: String,
        secondName: String,
        intentionId: Int,
        languageIds: List<Int>,
        adSource: Int,
    ): ApolloResponse<CreateLeadMutation.Data>
}
