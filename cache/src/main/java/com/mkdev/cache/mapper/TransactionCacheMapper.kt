package com.mkdev.cache.mapper

import com.mkdev.cache.models.TransactionCacheEntity
import com.mkdev.data.models.TransactionEntity
import javax.inject.Inject

class TransactionCacheMapper @Inject constructor() :
    CacheMapper<TransactionCacheEntity, TransactionEntity> {
    override fun mapFromCached(type: TransactionCacheEntity): TransactionEntity =
        TransactionEntity(
            fromCurrency = type.fromCurrency,
            toCurrency = type.toCurrency,
            fromAmount = type.fromAmount,
            toAmount = type.toAmount,
            currentBalance = type.currentBalance,
            commissionFee = type.commissionFee
        )

    override fun mapToCached(type: TransactionEntity): TransactionCacheEntity =
        TransactionCacheEntity(
            fromCurrency = type.fromCurrency,
            toCurrency = type.toCurrency,
            fromAmount = type.fromAmount,
            toAmount = type.toAmount,
            currentBalance = type.currentBalance,
            commissionFee = type.commissionFee
        )
}
