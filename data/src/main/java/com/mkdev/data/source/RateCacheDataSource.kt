package com.mkdev.data.source

import com.mkdev.data.models.RateEntity
import com.mkdev.data.repository.RateCache
import com.mkdev.data.repository.RateDataSource
import javax.inject.Inject

class RateCacheDataSource @Inject constructor(
    private val cache: RateCache
) : RateDataSource {

    override suspend fun getRates(): List<RateEntity> =
        cache.getRates()

    override suspend fun getRateByCurrencyName(name: String): RateEntity =
        cache.getRateByCurrencyName(name)

    override suspend fun saveRates(rateList: List<RateEntity>) =
        cache.saveRates(rateList)

    override suspend fun isCached(): Boolean =
        cache.isCached()
}