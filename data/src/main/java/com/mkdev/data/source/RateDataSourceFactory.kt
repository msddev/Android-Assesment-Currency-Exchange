package com.mkdev.data.source

import com.mkdev.data.repository.RateDataSource
import javax.inject.Inject

class RateDataSourceFactory @Inject constructor(
    private val cacheDataSource: RateCacheDataSource,
    private val remoteDataSource: RateRemoteDataSource
) {
    fun getDataStore(isCached: Boolean): RateDataSource {
        return if (isCached) {
            getCacheDataSource()
        } else {
            getRemoteDataSource()
        }
    }

    fun getRemoteDataSource(): RateDataSource {
        return remoteDataSource
    }

    fun getCacheDataSource(): RateDataSource {
        return cacheDataSource
    }
}