package com.mkdev.cache

import com.mkdev.cache.dao.BalanceDao
import com.mkdev.cache.mapper.BalanceCacheMapper
import com.mkdev.data.models.BalanceEntity
import com.mkdev.data.repository.BalanceCache
import javax.inject.Inject

class BalanceCacheImpl @Inject constructor(
    private val dao: BalanceDao,
    private val cacheMapper: BalanceCacheMapper,
) : BalanceCache {

    override suspend fun getBalances(): List<BalanceEntity> =
        dao.getBalances().map { cacheBalance ->
            cacheMapper.mapFromCached(cacheBalance)
        }

    override suspend fun updateBalances(balances: List<BalanceEntity>) {
        balances.map {
            dao.insertBalance(cacheMapper.mapToCached(it))
        }
    }

    override suspend fun isCached(): Boolean =
        dao.getBalances().isNotEmpty()
}