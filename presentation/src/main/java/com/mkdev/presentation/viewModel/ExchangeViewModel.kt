package com.mkdev.presentation.viewModel

import androidx.lifecycle.LiveData
import com.mkdev.domain.intractor.GetRatesUseCase
import com.mkdev.domain.model.RateUIModel
import com.mkdev.presentation.utils.CoroutineContextProvider
import com.mkdev.presentation.utils.UiAwareLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class ExchangeViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private val getRatesUseCase: GetRatesUseCase
) : BaseViewModel(contextProvider) {

    override val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, exception ->
            _rateList.postValue(
                RateUIModel.Error(
                    exception.message ?: "Occurred ViewModel Exception!"
                )
            )
        }

    private val _rateList = UiAwareLiveData<RateUIModel>()
    val rateList: LiveData<RateUIModel> = _rateList
    fun getRates() {
        _rateList.postValue(RateUIModel.Loading)
        launchCoroutineIO {
            loadRates()
        }
    }

    private suspend fun loadRates() {
        getRatesUseCase(Unit).collect {
            _rateList.postValue(RateUIModel.Success(it))
        }
    }
}