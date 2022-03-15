package com.mkdev.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mkdev.cache.models.TransactionCacheEntity
import com.mkdev.cache.utils.CacheConstants.TRANSACTION_TABLE_NAME

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransaction(transaction: TransactionCacheEntity)

    @Query("SELECT * FROM $TRANSACTION_TABLE_NAME")
    fun getTransactions(): List<TransactionCacheEntity>
}