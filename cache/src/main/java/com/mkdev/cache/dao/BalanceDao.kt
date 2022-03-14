package com.mkdev.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mkdev.cache.models.BalanceCacheEntity
import com.mkdev.cache.utils.CacheConstants.BALANCE_TABLE_NAME

@Dao
interface BalanceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBalance(balance: BalanceCacheEntity)

    @Query("select * from $BALANCE_TABLE_NAME")
    fun getBalances(): List<BalanceCacheEntity>
}