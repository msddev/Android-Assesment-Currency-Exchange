package com.mkdev.currencyexchange.ui.exchange

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import com.mkdev.currencyexchange.R
import com.mkdev.currencyexchange.base.BaseFragment
import com.mkdev.currencyexchange.databinding.FragmentExchangeBinding
import com.mkdev.currencyexchange.extension.formatTwoDecimalNumber
import com.mkdev.currencyexchange.extension.hideKeyboard
import com.mkdev.currencyexchange.extension.observe
import com.mkdev.currencyexchange.utils.AppConstants.CURRENCY_NAME_EUR
import com.mkdev.currencyexchange.utils.AppConstants.CURRENCY_NAME_USD
import com.mkdev.domain.model.*
import com.mkdev.presentation.viewModel.RateViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
        observe(viewModel.convertedCurrency, ::onConvertCurrencyViewStateChange)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getRates()
        initViews()
    }

    private fun initViews() {
        setupRecyclerView()

        binding.editTextSell.addTextChangedListener(object : TextWatcher {
            private var debounceJob: Job? = null
            private val DELAY: Long = 1500L
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

            override fun afterTextChanged(amount: Editable) {
                debounceJob?.cancel()
                debounceJob = MainScope().launch {
                    delay(DELAY)
                    hideKeyboard()
                    convertCurrency(amount.toString())
                }
            }
        })

        /*binding.buttonSubmit.setOnClickListener {

            if (binding.editTextSell.text?.isBlank() == false) {
                val sellCurrency =
                    currencySellAdapter.getDataSourceItem(binding.spinnerCurrencySell.selectedItemPosition)
                val buyCurrency =
                    currencyBuyAdapter.getDataSourceItem(binding.spinnerCurrencyBuy.selectedItemPosition)

                myViewModel.submit(
                    KEY_IS_SELL_TYPE,
                    balanceSell,
                    balanceReceive,
                    editTextSell.text.toString(),
                )
            } else {
                showSnackBarMessage("Invalid input")
            }
        }*/
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
                updateCurrencySpinner(event.data.sortedBy { it.currencyName })
            }
            is RateUIModel.Error -> {
                handleErrorMessage(event.error)
            }
        }
    }

    private fun onBalanceViewStateChange(event: BalanceUIModel) {
        updateBalanceList((event as BalanceUIModel.Success).data)
    }

    private fun onConvertCurrencyViewStateChange(event: ConvertCurrencyUIModel) {
        when (event) {
            is ConvertCurrencyUIModel.Loading -> Unit
            is ConvertCurrencyUIModel.Success -> {
                binding.editTextBuy.setText(event.data.formatTwoDecimalNumber().toString())
            }
            is ConvertCurrencyUIModel.Error -> {
                handleErrorMessage(event.error)
            }
        }
    }

    private fun updateCurrencySpinner(data: List<Rate>) {
        currencySellAdapter = SpinnerCurrencyAdapter(requireContext(), data).also {
            binding.spinnerCurrencySell.apply {
                it.setDropDownViewResource(R.layout.item_currency_list)
                adapter = it
                setSelection(it.getPosition(data.find { it.currencyName == CURRENCY_NAME_EUR }?.currencyName))
            }
        }

        currencyBuyAdapter = SpinnerCurrencyAdapter(requireContext(), data).also {
            binding.spinnerCurrencyBuy.apply {
                it.setDropDownViewResource(R.layout.item_currency_list)
                adapter = it
                setSelection(it.getPosition(data.find { it.currencyName == CURRENCY_NAME_USD }?.currencyName))
            }
        }
    }

    private fun updateBalanceList(items: List<Balance>) {
        balanceAdapter.list = mutableListOf<Balance>().apply {
            addAll(items)
        }
    }

    private fun getSelectedRates(): Pair<Rate, Rate> {
        val sellCurrency =
            currencySellAdapter.getDataSourceItem(binding.spinnerCurrencySell.selectedItemPosition)
        val buyCurrency =
            currencyBuyAdapter.getDataSourceItem(binding.spinnerCurrencyBuy.selectedItemPosition)

        return Pair(sellCurrency, buyCurrency)
    }

    private fun convertCurrency(amount: String) {
        val rates = getSelectedRates()
        viewModel.convertCurrency(ExchangeParams(amount.toDouble(), rates.first, rates.second))
    }
}