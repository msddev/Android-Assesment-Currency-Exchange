package com.mkdev.domain.model

data class Transaction(
    val fromCurrency: String,
    val toCurrency: String,
    val fromAmount: Double,
    val toAmount: Double,
    val currencyBalance: Double,
    val commissionFee: Double
)