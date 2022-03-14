package com.mkdev.domain.intractor

import com.mkdev.domain.model.Balance
import com.mkdev.domain.model.Rate
import com.mkdev.domain.repository.BalanceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBalanceUseCase @Inject constructor(
    private val repository: BalanceRepository
) : BaseUseCase<Unit, Flow<List<Balance>>> {
    override suspend fun invoke(params: Unit): Flow<List<Balance>> =
        repository.getBalances()
}