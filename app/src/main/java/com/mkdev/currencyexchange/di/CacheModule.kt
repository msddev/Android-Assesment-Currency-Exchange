package com.mkdev.currencyexchange.di

import android.content.Context
import com.mkdev.cache.BalanceCacheImpl
import com.mkdev.cache.RateCacheImpl
import com.mkdev.cache.TransactionCacheImpl
import com.mkdev.cache.dao.BalanceDao
import com.mkdev.cache.dao.RateDao
import com.mkdev.cache.dao.TransactionDao
import com.mkdev.cache.database.ExchangeDatabase
import com.mkdev.cache.utils.CachePreferencesHelper
import com.mkdev.data.repository.BalanceCache
import com.mkdev.data.repository.RateCache
import com.mkdev.data.repository.TransactionCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): ExchangeDatabase {
        return ExchangeDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideRateDao(database: ExchangeDatabase): RateDao {
        return database.cachedRateDao()
    }

    @Provides
    @Singleton
    fun provideBalanceDao(database: ExchangeDatabase): BalanceDao {
        return database.cachedBalanceDao()
    }

    @Provides
    @Singleton
    fun provideTransactionDao(database: ExchangeDatabase): TransactionDao {
        return database.cachedTransactionDao()
    }

    @Provides
    @Singleton
    fun provideCachePreferenceHelper(@ApplicationContext context: Context): CachePreferencesHelper {
        return CachePreferencesHelper(context)
    }

    @Provides
    @Singleton
    fun provideRateCache(cacheImpl: RateCacheImpl): RateCache = cacheImpl

    @Provides
    @Singleton
    fun provideBalanceCache(cacheImpl: BalanceCacheImpl): BalanceCache = cacheImpl

    @Provides
    @Singleton
    fun provideTransactionCache(cacheImpl: TransactionCacheImpl): TransactionCache = cacheImpl
}
