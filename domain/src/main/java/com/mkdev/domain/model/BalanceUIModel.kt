package com.mkdev.domain.model

sealed class BalanceUIModel : UiAwareModel() {
    object Loading : BalanceUIModel()
    data class Success(val data: List<Balance>) : BalanceUIModel()
    data class SuccessWithMessage(val data: Transaction) : BalanceUIModel()
    data class Error(var error: String) : BalanceUIModel()
}
