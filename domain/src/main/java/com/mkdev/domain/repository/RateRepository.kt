package com.mkdev.domain.repository

import com.mkdev.domain.model.Rate
import kotlinx.coroutines.flow.Flow

interface RateRepository {
    // Remote and cache
    fun getRates(isForced:Boolean): Flow<List<Rate>>
    fun getRateByCurrencyName(name: String): Flow<Rate>

    // Cache
    suspend fun saveRates(rateList: List<Rate>)
}