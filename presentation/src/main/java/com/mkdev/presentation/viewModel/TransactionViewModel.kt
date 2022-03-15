package com.mkdev.presentation.viewModel

import androidx.lifecycle.LiveData
import com.mkdev.domain.model.TransactionUIModel
import com.mkdev.presentation.utils.CoroutineContextProvider
import com.mkdev.presentation.utils.UiAwareLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider
) : BaseViewModel(contextProvider) {

    override val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, exception ->
            _transactionList.postValue(
                TransactionUIModel.Error(
                    exception.message ?: "Occurred Exception!"
                )
            )
        }

    private val _transactionList = UiAwareLiveData<TransactionUIModel>()
    val transactionList: LiveData<TransactionUIModel> = _transactionList
    fun getTransactions() {
        _transactionList.postValue(TransactionUIModel.Loading)
        launchCoroutineIO {
            //loadRates()
        }
    }
}