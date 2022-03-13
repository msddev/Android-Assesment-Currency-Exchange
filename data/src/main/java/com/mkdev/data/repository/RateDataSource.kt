package com.mkdev.data.repository

import com.mkdev.data.models.RateEntity

interface RateDataSource {
    // Remote and cache
    suspend fun getRates(): List<RateEntity>

    // Cache
    suspend fun getRateByCurrencyName(name: String): RateEntity
    suspend fun saveRates(rateList: List<RateEntity>)
    suspend fun isCached(): Boolean
}
