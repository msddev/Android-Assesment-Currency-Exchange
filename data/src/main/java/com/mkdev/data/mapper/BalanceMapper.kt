package com.mkdev.data.mapper

import com.mkdev.data.models.BalanceEntity
import com.mkdev.domain.model.Balance
import javax.inject.Inject

class BalanceMapper @Inject constructor() : Mapper<BalanceEntity, Balance> {
    override fun mapFromEntity(type: BalanceEntity): Balance =
        Balance(
            currencyName = type.currencyName,
            balance = type.balance
        )

    override fun mapToEntity(type: Balance): BalanceEntity =
        BalanceEntity(
            currencyName = type.currencyName,
            balance = type.balance
        )
}
