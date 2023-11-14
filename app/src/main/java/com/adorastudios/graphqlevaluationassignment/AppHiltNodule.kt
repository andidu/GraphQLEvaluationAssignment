package com.adorastudios.graphqlevaluationassignment

import com.adorastudios.graphqlevaluationassignment.data.LeadRepositoryImpl
import com.adorastudios.graphqlevaluationassignment.data.network.LeadsApi
import com.adorastudios.graphqlevaluationassignment.domain.LeadRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppHiltNodule {
    @Provides
    @Singleton
    fun provideLeadsApi(): LeadsApi {
        return LeadsApi()
    }

    @Provides
    @Singleton
    fun provideLeadRepository(api: LeadsApi): LeadRepository {
        return LeadRepositoryImpl(api)
    }
}
