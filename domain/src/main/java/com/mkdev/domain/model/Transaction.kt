package com.mkdev.domain.model

data class Transaction(
    val fromCurrency: String,
    val toCurrency: String,
    val fromAmount: Double,
    val toAmount: Double,
    val currentBalance: Double,
    val commissionFee: Double
)