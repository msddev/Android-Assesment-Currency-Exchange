package com.mkdev.domain.repository

import com.mkdev.domain.model.Balance
import kotlinx.coroutines.flow.Flow

interface BalanceRepository {
    suspend fun getBalances(): Flow<List<Balance>>
    suspend fun getBalance(currencyName:String): Flow<Balance>
    suspend fun saveBalances(balances: List<Balance>): Flow<Int>
}