package com.mkdev.domain.model

data class BalanceParams(
    val sellAmount: Double,
    val sellRate: Rate,
    val buyAmount: Double,
    val buyRate: Rate
)