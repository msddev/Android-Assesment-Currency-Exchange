package com.mkdev.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mkdev.cache.dao.RateDao
import com.mkdev.cache.models.RateCacheEntity
import com.mkdev.cache.utils.CacheConstants
import com.mkdev.cache.utils.Migrations
import javax.inject.Inject

@Database(
    entities = [RateCacheEntity::class],
    version = Migrations.DB_VERSION,
    exportSchema = false
)
abstract class ExchangeDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedRateDao(): RateDao

    companion object {
        @Volatile
        private var INSTANCE: ExchangeDatabase? = null

        fun getInstance(context: Context): ExchangeDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ExchangeDatabase::class.java,
            CacheConstants.DB_NAME
        ).build()
    }
}
