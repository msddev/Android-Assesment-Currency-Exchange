package com.mkdev.domain.intractor

import com.mkdev.domain.model.Balance
import com.mkdev.domain.model.BalanceParams
import com.mkdev.domain.model.BalanceUIModel
import com.mkdev.domain.model.Transaction
import com.mkdev.domain.repository.BalanceRepository
import com.mkdev.domain.repository.TransactionRepository
import com.mkdev.domain.utils.DomainConstants.COMMISSION_PERCENT
import com.mkdev.domain.utils.DomainConstants.FREE_COMMISSION_LIMIT
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateBalanceUseCase @Inject constructor(
    private val balanceRepository: BalanceRepository,
    private val transactionRepository: TransactionRepository
) : BaseUseCase<BalanceParams, Flow<BalanceUIModel>> {
    override suspend fun invoke(params: BalanceParams): Flow<BalanceUIModel> = flow {

        val transactionCount = transactionRepository.getTransactionCount().first()
        var commissionFee = 0.0

        val currentBuyBalances = balanceRepository.getBalance(params.buyRate.currencyName).first()
        val newBalance = currentBuyBalances.balance + params.buyAmount

        if (transactionCount >= FREE_COMMISSION_LIMIT) {
            commissionFee = ((COMMISSION_PERCENT * newBalance) / 100)
        }

        val currentSellBalances = balanceRepository.getBalance(params.sellRate.currencyName).first()
        val totalBalance = currentSellBalances.balance - (params.sellAmount + commissionFee)

        balanceRepository.saveBalances(
            listOf(
                Balance(currentSellBalances.currencyName, totalBalance),
                Balance(params.buyRate.currencyName, newBalance)
            )
        )

        val transaction = Transaction(
            fromCurrency = params.sellRate.currencyName,
            toCurrency = params.buyRate.currencyName,
            fromAmount = params.sellAmount,
            toAmount = params.buyAmount,
            currentBalance = totalBalance,
            commissionFee = commissionFee
        )
        transactionRepository.saveTransaction(transaction)

        val balanceList = balanceRepository.getBalances().first()
        emit(BalanceUIModel.Success(balanceList))
    }
}