package com.mkdev.domain.model

data class ExchangeParams(
    val amount: Double,
    val sellRate: Rate,
    val buyRate: Rate
)