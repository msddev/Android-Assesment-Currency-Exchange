package com.mkdev.data.mapper

import com.mkdev.data.models.TransactionEntity
import com.mkdev.domain.model.Transaction
import javax.inject.Inject

class TransactionMapper @Inject constructor() : Mapper<TransactionEntity, Transaction> {
    override fun mapFromEntity(type: TransactionEntity?): Transaction =
        Transaction(
            fromCurrency = type?.fromCurrency ?: "",
            toCurrency = type?.toCurrency ?: "",
            fromAmount = type?.fromAmount ?: 0.0,
            toAmount = type?.toAmount ?: 0.0,
            currentBalance = type?.currentBalance ?: 0.0,
            commissionFee = type?.commissionFee ?: 0.0
        )

    override fun mapToEntity(type: Transaction?): TransactionEntity =
        TransactionEntity(
            fromCurrency = type?.fromCurrency ?: "",
            toCurrency = type?.toCurrency ?: "",
            fromAmount = type?.fromAmount ?: 0.0,
            toAmount = type?.toAmount ?: 0.0,
            currentBalance = type?.currentBalance ?: 0.0,
            commissionFee = type?.commissionFee ?: 0.0
        )
}
