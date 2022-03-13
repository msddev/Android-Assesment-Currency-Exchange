package com.mkdev.currencyexchange.di

import com.mkdev.data.RateRepositoryImpl
import com.mkdev.domain.repository.RateRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideRateRepository(
        repositoryImpl: RateRepositoryImpl
    ): RateRepository = repositoryImpl
}