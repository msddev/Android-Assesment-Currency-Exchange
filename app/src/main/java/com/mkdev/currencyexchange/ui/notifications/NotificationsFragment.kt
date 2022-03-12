package com.mkdev.currencyexchange.ui.notifications

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.mkdev.currencyexchange.base.BaseFragment
import com.mkdev.currencyexchange.databinding.FragmentNotificationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsFragment : BaseFragment<FragmentNotificationsBinding, NotificationsViewModel>() {

    override val viewModel: NotificationsViewModel by viewModels()
    override fun getViewBinding(): FragmentNotificationsBinding =
        FragmentNotificationsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView: TextView = binding.textNotifications
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }
}