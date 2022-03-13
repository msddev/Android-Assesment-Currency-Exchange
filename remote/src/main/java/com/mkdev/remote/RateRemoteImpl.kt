package com.mkdev.remote

import com.mkdev.data.models.RateEntity
import com.mkdev.data.repository.RateRemote
import com.mkdev.remote.api.ExchangeService
import com.mkdev.remote.mappers.RateEntityMapper
import javax.inject.Inject

class RateRemoteImpl @Inject constructor(
    private val service: ExchangeService,
    private val mapper: RateEntityMapper
) : RateRemote {

    override suspend fun getRates(): List<RateEntity> =
        service.getRates().rates.map { (key, value) ->
            mapper.mapFromResponse(hashMapOf(key to value))
        }
}