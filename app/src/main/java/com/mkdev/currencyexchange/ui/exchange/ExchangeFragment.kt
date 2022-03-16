package com.mkdev.currencyexchange.ui.exchange

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import com.mkdev.currencyexchange.R
import com.mkdev.currencyexchange.base.BaseFragment
import com.mkdev.currencyexchange.core.dialog.showDialog
import com.mkdev.currencyexchange.databinding.FragmentExchangeBinding
import com.mkdev.currencyexchange.extension.*
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

        viewModel.repeatRequest()
        viewModel.getBalances()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        binding.recyclerViewMyBalances.adapter = balanceAdapter

        binding.editTextSell.addTextChangedListener(object : TextWatcher {
            private var debounceJob: Job? = null
            private val DELAY: Long = 500L
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.editTextBuy.setText("")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

            override fun afterTextChanged(amount: Editable) {
                debounceJob?.cancel()
                if (amount.toString().isEmpty()) {
                    binding.editTextBuy.setText("")
                    return
                }
                debounceJob = MainScope().launch {
                    delay(DELAY)
                    hideKeyboard()
                    convertCurrency(amount.toString())
                }
            }
        })

        binding.buttonSubmit.setOnClickListener {
            if (binding.editTextSell.text.isNullOrEmpty()) {
                handleErrorMessage(getString(R.string.empty_sell_amount))
                return@setOnClickListener
            }
            if (binding.editTextBuy.text.isNullOrEmpty()) {
                handleErrorMessage(getString(R.string.empty_buy_amount))
                return@setOnClickListener
            }

            val sellAmount = binding.editTextSell.text.toString().toDouble()
            val buyAmount = binding.editTextBuy.text.toString().toDouble()

            val sellRate = getSelectedRates().first
            val buyRate = getSelectedRates().second

            if (sellAmount <= 0) {
                handleErrorMessage(getString(R.string.sell_amount_is_less_than_zero))
                return@setOnClickListener
            }

            viewModel.updateBalance(
                BalanceParams(
                    sellAmount = sellAmount,
                    sellRate = sellRate,
                    buyAmount = buyAmount,
                    buyRate = buyRate
                )
            )
        }
    }

    private fun onRateViewStateChange(event: RateUIModel) {
        when (event) {
            is RateUIModel.Loading -> {
                binding.textViewRefreshRate.makeVisible()
            }
            is RateUIModel.Success -> {
                binding.textViewRefreshRate.makeGone()
                updateCurrencySpinner(event.data.sortedBy { it.currencyName })
            }
            is RateUIModel.Error -> {
                handleErrorMessage(event.error)
            }
        }
    }

    private fun onBalanceViewStateChange(event: BalanceUIModel) {
        when (event) {
            is BalanceUIModel.Success -> {
                balanceAdapter.list = event.data
            }
            is BalanceUIModel.SuccessWithMessage -> {
                showMessageDialog(event.data)
            }
            is BalanceUIModel.Loading -> Unit
            is BalanceUIModel.Error -> Unit
        }
        binding.editTextSell.setText("")
        binding.editTextBuy.setText("")
    }

    private fun showMessageDialog(data: Transaction) {
        showDialog(
            message = getString(
                R.string.transaction_message,
                data.fromAmount.formatTwoDecimalNumber().toString(),
                data.fromCurrency.uppercase(),
                data.toAmount.formatTwoDecimalNumber().toString(),
                data.toCurrency.uppercase(),
                data.commissionFee.formatTwoDecimalNumber().toString(),
                data.fromCurrency.uppercase(),
            ),
            cancelable = false,
            textPositive = getString(R.string.ok_string),
            positiveListener = {}
        )
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