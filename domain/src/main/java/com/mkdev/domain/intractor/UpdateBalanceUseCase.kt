package com.mkdev.domain.intractor

import com.mkdev.domain.model.Balance
import com.mkdev.domain.model.BalanceParams
import com.mkdev.domain.model.BalanceUIModel
import com.mkdev.domain.repository.BalanceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateBalanceUseCase @Inject constructor(
    private val repository: BalanceRepository
) : BaseUseCase<BalanceParams, Flow<BalanceUIModel>> {
    override suspend fun invoke(params: BalanceParams): Flow<BalanceUIModel> = flow {

        val currentSellBalances = repository.getBalance(params.sellRate.currencyName).first()
        val newSellBalance = currentSellBalances.balance - params.sellAmount

        repository.saveBalances(
            listOf(
                Balance(currentSellBalances.currencyName, newSellBalance),
                Balance(params.buyRate.currencyName, params.buyAmount)
            )
        ).collect {
            //TODO(ADD TRANSACTION)

            val balanceList = repository.getBalances().first()
            emit(BalanceUIModel.Success(balanceList))
        }
    }
}