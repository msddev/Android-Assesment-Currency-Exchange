package com.mkdev.currencyexchange.ui.dashboard

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.mkdev.currencyexchange.base.BaseFragment
import com.mkdev.currencyexchange.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {

    override val viewModel: DashboardViewModel by viewModels()
    override fun getViewBinding(): FragmentDashboardBinding =
        FragmentDashboardBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView: TextView = binding.textDashboard
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }
}