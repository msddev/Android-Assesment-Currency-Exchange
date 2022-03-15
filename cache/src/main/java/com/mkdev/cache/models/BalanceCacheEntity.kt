package com.mkdev.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mkdev.cache.utils.CacheConstants.BALANCE_TABLE_NAME

@Entity(tableName = BALANCE_TABLE_NAME)
data class BalanceCacheEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "currency_name")
    var currencyName: String = "",
    var balance: Double = 0.0
)