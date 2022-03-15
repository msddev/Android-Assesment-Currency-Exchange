package com.mkdev.currencyexchange.ui.exchange

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.mkdev.currencyexchange.R

class SpinnerCurrencyAdapter(context: Context, dataSource: List<String>) :
    ArrayAdapter<String>(context, 0, dataSource) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_currency_list, parent, false)
        val currencyName: String? = getItem(position)
        val label: TextView = view?.findViewById(R.id.textViewCurrency) as TextView
        label.text = currencyName
        return view
    }
}