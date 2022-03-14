package com.mkdev.domain.intractor

import com.mkdev.domain.model.Balance
import com.mkdev.domain.repository.BalanceRepository
import javax.inject.Inject

class UpdateBalanceUseCase @Inject constructor(
    private val repository: BalanceRepository
) : BaseUseCase<List<Balance>, Unit> {
    override suspend fun invoke(params: List<Balance>): Unit =
        repository.saveBalances(params)
}