package com.mkdev.currencyexchange.ui.transaction

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.mkdev.currencyexchange.base.BaseFragment
import com.mkdev.currencyexchange.databinding.FragmentTransactionBinding
import com.mkdev.currencyexchange.extension.makeGone
import com.mkdev.currencyexchange.extension.makeVisible
import com.mkdev.currencyexchange.extension.observe
import com.mkdev.domain.model.Transaction
import com.mkdev.domain.model.TransactionUIModel
import com.mkdev.presentation.viewModel.TransactionViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TransactionFragment : BaseFragment<FragmentTransactionBinding, TransactionViewModel>() {

    override val viewModel: TransactionViewModel by viewModels()

    override fun getViewBinding(): FragmentTransactionBinding =
        FragmentTransactionBinding.inflate(layoutInflater)

    @Inject
    lateinit var transactionAdapter: TransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe(viewModel.transactionList, ::onViewStateChange)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewTransaction.adapter = transactionAdapter

        viewModel.getTransactions()
    }

    private fun onViewStateChange(event: TransactionUIModel) {
        if (event.isRedelivered) return
        when (event) {
            is TransactionUIModel.Loading -> {
                handleLoading(true)
            }
            is TransactionUIModel.Success -> {
                handleLoading(false)
                handleResponse(event.data)
            }
            is TransactionUIModel.Error -> {
                handleErrorMessage(event.error)
            }
        }
    }

    private fun handleResponse(items: List<Transaction>) {
        if (items.isNotEmpty()) {
            binding.recyclerViewTransaction.makeVisible()
            binding.groupNothing.makeGone()
        }
        transactionAdapter.list = items
    }
}