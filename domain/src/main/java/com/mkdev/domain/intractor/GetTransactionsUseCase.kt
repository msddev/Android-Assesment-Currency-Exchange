package com.mkdev.domain.intractor

import com.mkdev.domain.model.Transaction
import com.mkdev.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTransactionsUseCase @Inject constructor(
    private val repository: TransactionRepository
) : BaseUseCase<Unit, Flow<List<Transaction>>> {
    override suspend fun invoke(params: Unit): Flow<List<Transaction>> =
        repository.getTransactions()
}