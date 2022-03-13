package com.mkdev.domain.model

sealed class RateUIModel : UiAwareModel() {
    object Loading : RateUIModel()
    data class Success(val data: List<Rate>) : RateUIModel()
    data class Error(var error: String) : RateUIModel()
}
