package com.mkdev.currencyexchange.ui.rate

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mkdev.currencyexchange.base.BaseFragment
import com.mkdev.currencyexchange.databinding.FragmentRateBinding
import com.mkdev.currencyexchange.extension.observe
import com.mkdev.domain.model.Rate
import com.mkdev.domain.model.RateUIModel
import com.mkdev.presentation.viewModel.RateViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RateFragment : BaseFragment<FragmentRateBinding, RateViewModel>() {

    override val viewModel: RateViewModel by viewModels()

    override fun getViewBinding(): FragmentRateBinding =
        FragmentRateBinding.inflate(layoutInflater)

    @Inject
    lateinit var rateAdapter: RateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe(viewModel.rateList, ::onViewStateChange)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        viewModel.getRates()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewRate.apply {
            adapter = rateAdapter
        }

        rateAdapter.setItemClickListener {

        }
    }

    private fun onViewStateChange(event: RateUIModel) {
        if (event.isRedelivered) return
        when (event) {
            is RateUIModel.Loading -> {
                handleLoading(true)
            }
            is RateUIModel.Success -> {
                handleLoading(false)
                handleResponse(event.data)
            }
            is RateUIModel.Error -> {
                handleErrorMessage(event.error)
            }
        }
    }

    private fun handleResponse(items: List<Rate>) {
        rateAdapter.list = mutableListOf<Rate>().apply {
            addAll(items)
        }
    }
}