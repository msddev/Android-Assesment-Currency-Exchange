package com.mkdev.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mkdev.cache.utils.CacheConstants

@Entity(tableName = CacheConstants.TRANSACTION_TABLE_NAME)
data class TransactionCacheEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "from_currency")
    var fromCurrency: String = "",
    @ColumnInfo(name = "to_currency")
    var toCurrency: String = "",
    @ColumnInfo(name = "from_amount")
    var fromAmount: Double = 0.0,
    @ColumnInfo(name = "to_amount")
    var toAmount: Double = 0.0,
    @ColumnInfo(name = "current_balance")
    var currentBalance: Double = 0.0,
    @ColumnInfo(name = "commission_fee")
    var commissionFee: Double = 0.0
)