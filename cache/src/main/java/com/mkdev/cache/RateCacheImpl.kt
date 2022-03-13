package com.mkdev.cache

import com.mkdev.cache.dao.RateDao
import com.mkdev.cache.mapper.RateCacheMapper
import com.mkdev.data.models.RateEntity
import com.mkdev.data.repository.RateCache
import javax.inject.Inject

class RateCacheImpl @Inject constructor(
    private val rateDao: RateDao,
    private val cacheMapper: RateCacheMapper,
) : RateCache {

    override suspend fun getRates(): List<RateEntity> =
        rateDao.getRates().map { cacheRate ->
            cacheMapper.mapFromCached(cacheRate)
        }

    override suspend fun getRateByCurrencyName(name: String): RateEntity =
        cacheMapper.mapFromCached(rateDao.getRateByCurrencyName(name))

    override suspend fun saveRates(listRates: List<RateEntity>) {
        listRates.map {
            rateDao.insertRate(cacheMapper.mapToCached(it))
        }
    }

    override suspend fun isCached(): Boolean =
        rateDao.getRates().isNotEmpty()
}