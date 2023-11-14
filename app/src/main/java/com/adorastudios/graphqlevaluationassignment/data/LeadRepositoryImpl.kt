package com.adorastudios.graphqlevaluationassignment.data

import com.adorastudios.graphqlevaluationassignment.com.adorastudios.graphqlevaluationassignment.CreateLeadMutation
import com.adorastudios.graphqlevaluationassignment.com.adorastudios.graphqlevaluationassignment.FetchAdSourcesQuery
import com.adorastudios.graphqlevaluationassignment.com.adorastudios.graphqlevaluationassignment.FetchCountriesQuery
import com.adorastudios.graphqlevaluationassignment.com.adorastudios.graphqlevaluationassignment.FetchLeadIntentionTypesQuery
import com.adorastudios.graphqlevaluationassignment.com.adorastudios.graphqlevaluationassignment.FetchLeadsQuery
import com.adorastudios.graphqlevaluationassignment.com.adorastudios.graphqlevaluationassignment.LanguagesQuery
import com.adorastudios.graphqlevaluationassignment.data.network.LeadsApi
import com.adorastudios.graphqlevaluationassignment.domain.LeadRepository
import com.apollographql.apollo3.api.ApolloResponse

class LeadRepositoryImpl(
    private val api: LeadsApi,
) : LeadRepository {
    override suspend fun queryLeads(): ApolloResponse<FetchLeadsQuery.Data> {
        return api.getApolloClient().query(FetchLeadsQuery()).execute()
    }

    override suspend fun queryLanguages(): ApolloResponse<LanguagesQuery.Data> {
        return api.getApolloClient().query(LanguagesQuery()).execute()
    }

    override suspend fun queryCountries(): ApolloResponse<FetchCountriesQuery.Data> {
        return api.getApolloClient().query(FetchCountriesQuery()).execute()
    }

    override suspend fun queryAdSources(): ApolloResponse<FetchAdSourcesQuery.Data> {
        return api.getApolloClient().query(FetchAdSourcesQuery()).execute()
    }

    override suspend fun queryIntentions(): ApolloResponse<FetchLeadIntentionTypesQuery.Data> {
        return api.getApolloClient().query(FetchLeadIntentionTypesQuery()).execute()
    }

    override suspend fun mutationLead(
        phoneNumber: String,
        countryId: Int,
        firstName: String,
        secondName: String,
        intentionId: Int,
        languageIds: List<Int>,
        adSource: Int,
    ): ApolloResponse<CreateLeadMutation.Data> {
        return api.getApolloClient().mutation(
            CreateLeadMutation(
                phoneNumber = phoneNumber,
                countryId = countryId,
                firstName = firstName,
                secondName = secondName,
                intentionId = intentionId,
                languageIds = languageIds,
                adSource = adSource,
            ),
        ).execute()
    }
}
