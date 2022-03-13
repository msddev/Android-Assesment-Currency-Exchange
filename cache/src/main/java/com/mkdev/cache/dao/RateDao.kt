package com.mkdev.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mkdev.cache.models.RateCacheEntity
import com.mkdev.cache.utils.CacheConstants.RATE_TABLE_NAME

@Dao
interface RateDao {

    @Query("select * from $RATE_TABLE_NAME")
    fun getRates(): List<RateCacheEntity>

    @Query("select * from $RATE_TABLE_NAME where currency_name= :name")
    fun getRateByCurrencyName(name: String): RateCacheEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRate(rate: RateCacheEntity)
}
