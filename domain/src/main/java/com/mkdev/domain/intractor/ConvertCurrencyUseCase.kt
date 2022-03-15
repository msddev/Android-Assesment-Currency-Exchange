package com.mkdev.domain.intractor

import com.mkdev.domain.model.ConvertCurrencyUIModel
import com.mkdev.domain.model.ExchangeParams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ConvertCurrencyUseCase @Inject constructor() :
    BaseUseCase<ExchangeParams, Flow<ConvertCurrencyUIModel>> {
    override suspend fun invoke(params: ExchangeParams): Flow<ConvertCurrencyUIModel> = flow {
        if (params.amount <= 0.0) {
            emit(ConvertCurrencyUIModel.Error("Sell amount is less than zero"))
            return@flow
        }

        val buyCurrency = params.buyRate.rate * params.amount

        //TODO(CHECK BALANCE VALUE)
        /*if (sellBalance.balance < amountDouble) {
            emit(Resource.failed(message = "Amount is not greater than current balance"))
            return@flow
        }*/
        emit(ConvertCurrencyUIModel.Success(buyCurrency))
    }
}