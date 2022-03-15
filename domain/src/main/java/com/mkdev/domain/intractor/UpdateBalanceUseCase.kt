package com.mkdev.domain.intractor

import com.mkdev.domain.model.Balance
import com.mkdev.domain.model.BalanceParams
import com.mkdev.domain.model.BalanceUIModel
import com.mkdev.domain.model.Transaction
import com.mkdev.domain.repository.BalanceRepository
import com.mkdev.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class UpdateBalanceUseCase @Inject constructor(
    private val balanceRepository: BalanceRepository,
    private val transactionRepository: TransactionRepository
) : BaseUseCase<BalanceParams, Flow<BalanceUIModel>> {
    override suspend fun invoke(params: BalanceParams): Flow<BalanceUIModel> = flow {

        val currentSellBalances = balanceRepository.getBalance(params.sellRate.currencyName).first()
        val newSellBalance = currentSellBalances.balance - params.sellAmount

        val currentBuyBalances = balanceRepository.getBalance(params.buyRate.currencyName).first()
        val newBuyBalance = currentBuyBalances.balance + params.buyAmount

        balanceRepository.saveBalances(
            listOf(
                Balance(currentSellBalances.currencyName, newSellBalance),
                Balance(params.buyRate.currencyName, newBuyBalance)
            )
        )

        val transaction = Transaction(
            fromCurrency = params.sellRate.currencyName,
            toCurrency = params.buyRate.currencyName,
            fromAmount = params.sellAmount,
            toAmount = params.buyAmount,
            currentBalance = 0.0,
            commissionFee = 0.0
        )
        transactionRepository.saveTransaction(transaction)

        val balanceList = balanceRepository.getBalances().first()
        emit(BalanceUIModel.Success(balanceList))
    }
}