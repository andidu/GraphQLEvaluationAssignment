package com.adorastudios.graphqlevaluationassignment.data

import com.adorastudios.graphqlevaluationassignment.com.adorastudios.graphqlevaluationassignment.CreateLeadMutation
import com.adorastudios.graphqlevaluationassignment.com.adorastudios.graphqlevaluationassignment.FetchAdSourcesQuery
import com.adorastudios.graphqlevaluationassignment.com.adorastudios.graphqlevaluationassignment.FetchCountriesQuery
import com.adorastudios.graphqlevaluationassignment.com.adorastudios.graphqlevaluationassignment.FetchLeadIntentionTypesQuery
import com.adorastudios.graphqlevaluationassignment.com.adorastudios.graphqlevaluationassignment.FetchLeadsQuery
import com.adorastudios.graphqlevaluationassignment.com.adorastudios.graphqlevaluationassignment.LanguagesQuery
import com.adorastudios.graphqlevaluationassignment.data.network.LeadsApi
import com.adorastudios.graphqlevaluationassignment.domain.LeadRepository

class LeadRepositoryImpl(
    private val api: LeadsApi,
) : LeadRepository {
    override suspend fun queryLeads(): Result<FetchLeadsQuery.Data> {
        return try {
            Result.success(api.getApolloClient().query(FetchLeadsQuery()).execute().data!!)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun queryLanguages(): Result<LanguagesQuery.Data> {
        return try {
            Result.success(api.getApolloClient().query(LanguagesQuery()).execute().data!!)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun queryCountries(): Result<FetchCountriesQuery.Data> {
        return try {
            Result.success(api.getApolloClient().query(FetchCountriesQuery()).execute().data!!)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun queryAdSources(): Result<FetchAdSourcesQuery.Data> {
        return try {
            Result.success(api.getApolloClient().query(FetchAdSourcesQuery()).execute().data!!)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun queryIntentions(): Result<FetchLeadIntentionTypesQuery.Data> {
        return try {
            Result.success(
                api.getApolloClient().query(FetchLeadIntentionTypesQuery()).execute().data!!,
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun mutationLead(
        phoneNumber: String,
        countryId: Int,
        firstName: String,
        secondName: String,
        intentionId: Int,
        languageIds: List<Int>,
        adSource: Int,
    ): Result<CreateLeadMutation.Data> {
        return try {
            Result.success(
                api.getApolloClient().mutation(
                    CreateLeadMutation(
                        phoneNumber = phoneNumber,
                        countryId = countryId,
                        firstName = firstName,
                        secondName = secondName,
                        intentionId = intentionId,
                        languageIds = languageIds,
                        adSource = adSource,
                    ),
                ).execute().data!!,
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
