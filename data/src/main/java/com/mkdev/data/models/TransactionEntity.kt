package com.mkdev.data.models

data class TransactionEntity(
    val fromCurrency: String,
    val toCurrency: String,
    val fromAmount: Double,
    val toAmount: Double,
    val currentBalance: Double,
    val commissionFee: Double
)