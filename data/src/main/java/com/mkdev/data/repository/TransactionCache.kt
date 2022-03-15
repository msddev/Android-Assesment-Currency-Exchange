package com.mkdev.data.repository

import com.mkdev.data.models.TransactionEntity

interface TransactionCache {
    suspend fun getTransactions(): List<TransactionEntity>
    suspend fun saveTransaction(data: TransactionEntity)
}
