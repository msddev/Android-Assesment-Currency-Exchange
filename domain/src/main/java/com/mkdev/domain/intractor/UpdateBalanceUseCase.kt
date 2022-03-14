package com.mkdev.domain.intractor

import com.mkdev.domain.model.Balance
import com.mkdev.domain.repository.BalanceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateBalanceUseCase @Inject constructor(
    private val repository: BalanceRepository
) : BaseUseCase<List<Balance>, Flow<Int>> {
    override suspend fun invoke(params: List<Balance>): Flow<Int> =
        repository.saveBalances(params)
}