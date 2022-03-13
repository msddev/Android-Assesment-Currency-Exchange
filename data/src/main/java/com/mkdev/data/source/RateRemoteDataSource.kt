package com.mkdev.data.source

import com.mkdev.data.models.RateEntity
import com.mkdev.data.repository.RateDataSource
import com.mkdev.data.repository.RateRemote
import javax.inject.Inject

class RateRemoteDataSource @Inject constructor(
    private val remote: RateRemote
) : RateDataSource {
    override suspend fun getRates(): List<RateEntity> =
        remote.getRates()

    override suspend fun getRateByCurrencyName(name: String): RateEntity {
        throw UnsupportedOperationException("Get RateByCurrencyName is not supported for RemoteDataSource.")
    }

    override suspend fun saveRates(rateList: List<RateEntity>) {
        throw UnsupportedOperationException("Save Rate is not supported for RemoteDataSource.")
    }

    override suspend fun isCached(): Boolean {
        throw UnsupportedOperationException("Check Cache is not supported for RemoteDataSource.")
    }
}