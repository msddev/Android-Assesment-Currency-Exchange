package com.mkdev.currencyexchange.extension

import android.view.View
import android.widget.Spinner
import com.mkdev.currencyexchange.ui.exchange.SpinnerCurrencyAdapter
import com.mkdev.domain.model.Rate

/**
 * Shorthand extension function to make view gone
 */
fun View.makeGone() {
    this.visibility = View.GONE
}

/**
 * Shorthand extension function to make view visible
 */
fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun SpinnerCurrencyAdapter.updateSpinner(
    data: List<Rate>,
    latestSelectedItems: String?,
    defaultItem: String?,
    spinner: Spinner
) {
    clear()
    refreshDataSource(data)
    addAll(data.map { it.currencyName })
    notifyDataSetChanged()

    val selectedItem = latestSelectedItems?.let { latest ->
        getPosition(data.find { it.currencyName == latest }?.currencyName)
    } ?: run {
        getPosition(data.find { it.currencyName == defaultItem }?.currencyName)
    }
    spinner.setSelection(selectedItem)
}