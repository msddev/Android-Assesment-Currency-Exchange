package com.mkdev.data

import com.mkdev.data.mapper.TransactionMapper
import com.mkdev.data.source.TransactionCacheDataSource
import com.mkdev.domain.model.Transaction
import com.mkdev.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val dataSource: TransactionCacheDataSource,
    private val mapper: TransactionMapper
) : TransactionRepository {
    override suspend fun getTransactions(): Flow<List<Transaction>> = flow {
        val list = dataSource.getTransactions()
            .map { entity ->
                mapper.mapFromEntity(entity)
            }
        emit(list)
    }

    override suspend fun saveTransaction(transaction: Transaction) =
        dataSource.saveTransaction(mapper.mapToEntity(transaction))
}