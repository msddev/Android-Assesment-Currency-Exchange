package com.mkdev.domain.repository

import com.mkdev.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    // Cache
    fun getTransactions(): Flow<List<Transaction>>
}