package com.mkdev.data.repository

import com.mkdev.data.models.TransactionEntity

interface TransactionDataSource {
    // Cache
    suspend fun getTransactions(): List<TransactionEntity>
}
