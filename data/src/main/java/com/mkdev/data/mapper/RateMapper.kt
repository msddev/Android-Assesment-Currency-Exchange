package com.mkdev.data.mapper

import com.mkdev.data.models.RateEntity
import com.mkdev.domain.model.Rate
import javax.inject.Inject

class RateMapper @Inject constructor() : Mapper<RateEntity, Rate> {
    override fun mapFromEntity(type: RateEntity): Rate =
        Rate(
            currencyName = type.currencyName,
            rate = type.rate
        )

    override fun mapToEntity(type: Rate): RateEntity =
        RateEntity(
            currencyName = type.currencyName,
            rate = type.rate
        )
}
