package com.mkdev.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mkdev.presentation.utils.CoroutineContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

@HiltViewModel
class RateViewModel @Inject constructor(
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
        value = "This is Rate Fragment"
    }
    val text: LiveData<String> = _text
}