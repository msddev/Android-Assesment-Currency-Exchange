package com.mkdev.data

import com.mkdev.data.mapper.BalanceMapper
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
        val list = dataSource.getBalances()
            .map { entity ->
                mapper.mapFromEntity(entity)
            }
        emit(list)
    }

    override suspend fun saveBalances(balances: List<Balance>) {
        val entities = balances.map { balance ->
            mapper.mapToEntity(balance)
        }
        dataSource.updateBalances(entities)
    }
}