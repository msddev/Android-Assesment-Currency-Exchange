package com.mkdev.domain.repository

import com.mkdev.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    // Cache
    suspend fun getTransactions(): Flow<List<Transaction>>
    suspend fun getTransactionCount(): Flow<Int>
    suspend fun saveTransaction(transaction: Transaction)
}