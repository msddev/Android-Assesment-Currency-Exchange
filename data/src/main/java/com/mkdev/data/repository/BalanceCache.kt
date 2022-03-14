package com.mkdev.data.repository

import com.mkdev.data.models.BalanceEntity

interface BalanceCache {
    suspend fun getBalances(): List<BalanceEntity>
    suspend fun updateBalances(balances: List<BalanceEntity>)
}
