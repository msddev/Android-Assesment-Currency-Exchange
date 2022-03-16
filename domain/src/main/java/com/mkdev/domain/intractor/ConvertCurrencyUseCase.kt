package com.mkdev.domain.intractor

import com.mkdev.domain.model.ConvertCurrencyUIModel
import com.mkdev.domain.model.ExchangeParams
import com.mkdev.domain.repository.BalanceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ConvertCurrencyUseCase @Inject constructor(
    private val balanceRepository: BalanceRepository,
) :
    BaseUseCase<ExchangeParams, Flow<ConvertCurrencyUIModel>> {
    override suspend fun invoke(params: ExchangeParams): Flow<ConvertCurrencyUIModel> = flow {
        if (params.amount <= 0.0) {
            emit(ConvertCurrencyUIModel.Error("Sell amount is less than zero"))
            return@flow
        }

        val currentSellBalances = balanceRepository.getBalance(params.sellRate.currencyName).first()
        if (params.amount >= currentSellBalances.balance) {
            emit(ConvertCurrencyUIModel.Error("Current balance is less than sell amount"))
            return@flow
        }

        val buyCurrency = params.buyRate.rate * params.amount
        emit(ConvertCurrencyUIModel.Success(buyCurrency))
    }
}