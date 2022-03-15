package com.mkdev.data

import com.mkdev.data.mapper.BalanceMapper
import com.mkdev.data.models.BalanceEntity
import com.mkdev.data.source.BalanceCacheDataSource
import com.mkdev.domain.model.Balance
import com.mkdev.domain.repository.BalanceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BalanceRepositoryImpl @Inject constructor(
    private val dataSource: BalanceCacheDataSource,
    private val mapper: BalanceMapper
) : BalanceRepository {

    override suspend fun getBalances(): Flow<List<Balance>> = flow {
        if (!dataSource.isCached()) {
            dataSource.updateBalances(listOf(BalanceEntity("EUR", 1000.00)))
        }
        val list = dataSource.getBalances()
            .map { entity ->
                mapper.mapFromEntity(entity)
            }
        emit(list)
    }

    override suspend fun getBalance(currencyName: String): Flow<Balance> = flow {
        val balance = mapper.mapFromEntity(dataSource.getBalance(currencyName) ?: BalanceEntity())
        emit(balance)
    }

    override suspend fun saveBalances(balances: List<Balance>) {
        val entities = balances.map { balance ->
            mapper.mapToEntity(balance)
        }
        dataSource.updateBalances(entities)
    }
}