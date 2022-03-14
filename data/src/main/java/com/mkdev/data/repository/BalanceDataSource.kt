package com.mkdev.data.repository

import com.mkdev.data.models.BalanceEntity

interface BalanceDataSource {
    // Cache
    suspend fun getBalances(): List<BalanceEntity>
    suspend fun updateBalances(balances: List<BalanceEntity>)
}
