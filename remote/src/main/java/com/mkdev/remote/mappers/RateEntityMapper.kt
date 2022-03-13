package com.mkdev.remote.mappers

import com.mkdev.data.models.RateEntity
import javax.inject.Inject

class RateEntityMapper @Inject constructor() :
    EntityMapper<HashMap<String, Double>, RateEntity> {
    override fun mapFromResponse(data: HashMap<String, Double>): RateEntity {
        return RateEntity(
            currencyName = "",
            rate = 0.0
        )
    }
}
