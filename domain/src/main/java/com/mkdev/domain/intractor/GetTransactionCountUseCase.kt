package com.mkdev.domain.intractor

import com.mkdev.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTransactionCountUseCase @Inject constructor(
    private val repository: TransactionRepository
) : BaseUseCase<Unit, Flow<Int>> {
    override suspend fun invoke(params: Unit): Flow<Int> =
        repository.getTransactionCount()
}