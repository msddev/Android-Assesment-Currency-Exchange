package com.mkdev.currencyexchange.ui.rate

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.mkdev.currencyexchange.base.BaseFragment
import com.mkdev.currencyexchange.databinding.FragmentRateBinding
import com.mkdev.presentation.viewModel.RateViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RateFragment : BaseFragment<FragmentRateBinding, RateViewModel>() {

    override val viewModel: RateViewModel by viewModels()
    override fun getViewBinding(): FragmentRateBinding =
        FragmentRateBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView: TextView = binding.textDashboard
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }
}