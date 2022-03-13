package com.mkdev.data.repository

import com.mkdev.data.models.RateEntity

interface RateCache {
    suspend fun getRates(): List<RateEntity>
    suspend fun getRateByCurrencyName(name: String): RateEntity
    suspend fun saveRates(listRates: List<RateEntity>)
    suspend fun isCached(): Boolean
}
