package com.mkdev.currencyexchange.di

import com.mkdev.currencyexchange.BuildConfig
import com.mkdev.data.repository.RateRemote
import com.mkdev.remote.RateRemoteImpl
import com.mkdev.remote.api.ExchangeService
import com.mkdev.remote.api.ServiceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Singleton
    @Provides
    fun provideExchangeService(): ExchangeService =
        ServiceFactory.create(BuildConfig.DEBUG)

    @Singleton
    @Provides
    fun provideRateRemote(remoteImpl: RateRemoteImpl): RateRemote = remoteImpl
}