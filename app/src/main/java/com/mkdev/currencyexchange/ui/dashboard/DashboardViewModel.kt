package com.mkdev.currencyexchange.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mkdev.presentation.utils.CoroutineContextProvider
import com.mkdev.presentation.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider
) : BaseViewModel(contextProvider) {

    override val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, exception ->
            /*_venuesList.postValue(
                VenueUIModel.Error(
                    exception.message ?: "Occurred ViewModel Exception!"
                )
            )*/
        }

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
}