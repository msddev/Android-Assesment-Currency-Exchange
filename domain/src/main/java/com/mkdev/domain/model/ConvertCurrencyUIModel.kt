package com.mkdev.domain.model

sealed class ConvertCurrencyUIModel : UiAwareModel() {
    object Loading : ConvertCurrencyUIModel()
    data class Success(val data: Double) : ConvertCurrencyUIModel()
    data class Error(var error: String) : ConvertCurrencyUIModel()
}
