package com.mkdev.data.source

import com.mkdev.data.models.BalanceEntity
import com.mkdev.data.repository.BalanceCache
import com.mkdev.data.repository.BalanceDataSource
import javax.inject.Inject

class BalanceCacheDataSource @Inject constructor(
    private val cache: BalanceCache
) : BalanceDataSource {
    override suspend fun getBalances(): List<BalanceEntity> =
        cache.getBalances()

    override suspend fun updateBalances(balances: List<BalanceEntity>) =
        cache.updateBalances(balances)
}