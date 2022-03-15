package com.mkdev.domain.model

sealed class TransactionUIModel : UiAwareModel() {
    object Loading : TransactionUIModel()
    data class Success(val data: List<Transaction>) : TransactionUIModel()
    data class Error(var error: String) : TransactionUIModel()
}
