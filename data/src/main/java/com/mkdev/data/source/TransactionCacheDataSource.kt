package com.mkdev.data.source

import com.mkdev.data.models.TransactionEntity
import com.mkdev.data.repository.TransactionCache
import com.mkdev.data.repository.TransactionDataSource
import javax.inject.Inject

class TransactionCacheDataSource @Inject constructor(
    private val cache: TransactionCache
) : TransactionDataSource {
    override suspend fun getTransactions(): List<TransactionEntity> =
        cache.getTransactions()

    override suspend fun saveTransaction(data: TransactionEntity) =
        cache.saveTransaction(data)
}