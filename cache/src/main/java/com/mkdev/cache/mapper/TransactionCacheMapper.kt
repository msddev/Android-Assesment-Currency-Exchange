package com.mkdev.cache.mapper

import com.mkdev.cache.models.TransactionCacheEntity
import com.mkdev.data.models.TransactionEntity
import javax.inject.Inject

class TransactionCacheMapper @Inject constructor() :
    CacheMapper<TransactionCacheEntity, TransactionEntity> {
    override fun mapFromCached(type: TransactionCacheEntity?): TransactionEntity =
        TransactionEntity(
            fromCurrency = type?.fromCurrency ?: "",
            toCurrency = type?.toCurrency ?: "",
            fromAmount = type?.fromAmount ?: 0.0,
            toAmount = type?.toAmount ?: 0.0,
            currentBalance = type?.currentBalance ?: 0.0,
            commissionFee = type?.commissionFee ?: 0.0
        )

    override fun mapToCached(type: TransactionEntity?): TransactionCacheEntity =
        TransactionCacheEntity(
            fromCurrency = type?.fromCurrency ?: "",
            toCurrency = type?.toCurrency ?: "",
            fromAmount = type?.fromAmount ?: 0.0,
            toAmount = type?.toAmount ?: 0.0,
            currentBalance = type?.currentBalance ?: 0.0,
            commissionFee = type?.commissionFee ?: 0.0
        )
}
