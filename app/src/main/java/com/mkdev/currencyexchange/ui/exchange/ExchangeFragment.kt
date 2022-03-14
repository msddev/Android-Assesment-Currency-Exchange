package com.mkdev.currencyexchange.ui.exchange

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mkdev.currencyexchange.base.BaseFragment
import com.mkdev.currencyexchange.databinding.FragmentExchangeBinding
import com.mkdev.currencyexchange.extension.observe
import com.mkdev.domain.model.Balance
import com.mkdev.domain.model.BalanceUIModel
import com.mkdev.domain.model.Rate
import com.mkdev.domain.model.RateUIModel
import com.mkdev.presentation.viewModel.RateViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ExchangeFragment : BaseFragment<FragmentExchangeBinding, RateViewModel>() {

    private lateinit var currencySellAdapter: SpinnerCurrencyAdapter
    private lateinit var currencyBuyAdapter: SpinnerCurrencyAdapter

    @Inject
    lateinit var balanceAdapter: BalanceAdapter

    override val viewModel: RateViewModel by viewModels()
    override fun getViewBinding(): FragmentExchangeBinding =
        FragmentExchangeBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe(viewModel.rateList, ::onRateViewStateChange)
        observe(viewModel.balanceList, ::onBalanceViewStateChange)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        viewModel.getRates()

        binding.buttonSubmit.setOnClickListener {
            val sellCurrency =
                currencySellAdapter.getItem(binding.spinnerCurrencySell.selectedItemPosition) as Rate
            val buyCurrency =
                currencyBuyAdapter.getItem(binding.spinnerCurrencyBuy.selectedItemPosition) as Rate

            /*Log.d("ddddd", sellCurrency.currencyName)
            Log.d("ddddd", sellCurrency.rate.toString())*/
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewMyBalances.apply {
            adapter = balanceAdapter
        }

        balanceAdapter.setItemClickListener {}
    }

    private fun onRateViewStateChange(event: RateUIModel) {
        if (event.isRedelivered) return
        when (event) {
            is RateUIModel.Loading -> {
                handleLoading(true)
            }
            is RateUIModel.Success -> {
                handleLoading(false)
                viewModel.getBalances()
                updateCurrencySpinner(event.data)
            }
            is RateUIModel.Error -> {
                handleErrorMessage(event.error)
            }
        }
    }

    private fun onBalanceViewStateChange(event: BalanceUIModel) {
        if (event.isRedelivered) return
        when (event) {
            is BalanceUIModel.Loading -> {
                handleLoading(true)
            }
            is BalanceUIModel.Success -> {
                handleLoading(false)
                updateBalanceList(event.data)
            }
            is BalanceUIModel.Error -> {
                handleErrorMessage(event.error)
            }
        }
    }

    private fun updateCurrencySpinner(data: List<Rate>) {
        currencySellAdapter = SpinnerCurrencyAdapter(requireContext(), data)
        binding.spinnerCurrencySell.adapter = currencySellAdapter

        currencyBuyAdapter = SpinnerCurrencyAdapter(requireContext(), data)
        binding.spinnerCurrencyBuy.adapter = currencyBuyAdapter
    }

    private fun updateBalanceList(items: List<Balance>) {
        balanceAdapter.list = mutableListOf<Balance>().apply {
            addAll(balanceAdapter.list)
            addAll(items)
        }
    }
}