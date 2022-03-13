package com.mkdev.cache.mapper

import com.mkdev.cache.models.RateCacheEntity
import com.mkdev.data.models.RateEntity
import javax.inject.Inject

class RateCacheMapper @Inject constructor() : CacheMapper<RateCacheEntity, RateEntity> {
    override fun mapFromCached(type: RateCacheEntity): RateEntity =
        RateEntity(
            currencyName = type.currencyName,
            rate = type.rate
        )

    override fun mapToCached(type: RateEntity): RateCacheEntity =
        RateCacheEntity(
            currencyName = type.currencyName,
            rate = type.rate,
        )
}
