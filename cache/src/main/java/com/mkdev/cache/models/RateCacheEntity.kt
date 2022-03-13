package com.mkdev.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mkdev.cache.utils.CacheConstants.RATE_TABLE_NAME

@Entity(tableName = RATE_TABLE_NAME)
data class RateCacheEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "currency_name")
    val currencyName: String = "",
    val rate: Double = 0.0
)