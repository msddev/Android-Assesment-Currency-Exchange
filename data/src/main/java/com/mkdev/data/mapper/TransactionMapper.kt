package com.mkdev.data.mapper

import com.mkdev.data.models.TransactionEntity
import com.mkdev.domain.model.Transaction
import javax.inject.Inject

class TransactionMapper @Inject constructor() : Mapper<TransactionEntity, Transaction> {
    override fun mapFromEntity(type: TransactionEntity): Transaction =
        Transaction(
            fromCurrency = type.fromCurrency,
            toCurrency = type.toCurrency,
            fromAmount = type.fromAmount,
            toAmount = type.toAmount,
            currencyBalance = type.currencyBalance,
            commissionFee = type.commissionFee
        )

    override fun mapToEntity(type: Transaction): TransactionEntity =
        TransactionEntity(
            fromCurrency = type.fromCurrency,
            toCurrency = type.toCurrency,
            fromAmount = type.fromAmount,
            toAmount = type.toAmount,
            currencyBalance = type.currencyBalance,
            commissionFee = type.commissionFee
        )
}
