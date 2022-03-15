package com.mkdev.cache

import com.mkdev.cache.dao.TransactionDao
import com.mkdev.cache.mapper.TransactionCacheMapper
import com.mkdev.data.models.TransactionEntity
import com.mkdev.data.repository.TransactionCache
import javax.inject.Inject

class TransactionCacheImpl @Inject constructor(
    private val dao: TransactionDao,
    private val cacheMapper: TransactionCacheMapper,
) : TransactionCache {
    override suspend fun getTransactions(): List<TransactionEntity> =
        dao.getTransactions().map { cache ->
            cacheMapper.mapFromCached(cache)
        }

    override suspend fun saveTransaction(data: TransactionEntity) =
        dao.insertTransaction(cacheMapper.mapToCached(data))
}