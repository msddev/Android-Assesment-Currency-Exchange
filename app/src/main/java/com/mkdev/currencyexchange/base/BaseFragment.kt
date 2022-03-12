package com.mkdev.currencyexchange.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.mkdev.currencyexchange.core.dialog.dismissLoadingDialog
import com.mkdev.currencyexchange.core.dialog.showLoadingDialog
import com.mkdev.currencyexchange.extension.showSnackBar
import com.mkdev.presentation.viewModel.BaseViewModel

abstract class BaseFragment<VB : ViewBinding, ViewModel : BaseViewModel> : Fragment() {

    protected lateinit var binding: VB
    protected abstract val viewModel: ViewModel

    abstract fun getViewBinding(): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = getViewBinding()
        return binding.root
    }

    protected open fun handleLoading(isLoading: Boolean) {
        if (isLoading) showLoadingDialog() else dismissLoadingDialog()
    }

    protected open fun handleErrorMessage(message: String?) {
        if (message.isNullOrBlank()) return
        dismissLoadingDialog()
        Log.e("error", message)
        showSnackBar(binding.root, message)
    }
}
