package com.mkdev.cache.mapper

import com.mkdev.cache.models.BalanceCacheEntity
import com.mkdev.data.models.BalanceEntity
import javax.inject.Inject

class BalanceCacheMapper @Inject constructor() : CacheMapper<BalanceCacheEntity, BalanceEntity> {
    override fun mapFromCached(type: BalanceCacheEntity): BalanceEntity =
        BalanceEntity(
            currencyName = type.currencyName,
            balance = type.balance
        )

    override fun mapToCached(type: BalanceEntity): BalanceCacheEntity =
        BalanceCacheEntity(
            currencyName = type.currencyName,
            balance = type.balance,
        )
}
