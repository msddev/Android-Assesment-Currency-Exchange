package com.mkdev.currencyexchange.ui.exchange

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.mkdev.currencyexchange.R
import com.mkdev.domain.model.Rate

class SpinnerCurrencyAdapter(
    context: Context,
    private val dataSource: MutableList<Rate> = mutableListOf()
) : ArrayAdapter<String>(context, 0, dataSource.map { it.currencyName }) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return (convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_currency_list, parent, false)).apply {
            val label: TextView = findViewById(R.id.textViewCurrency)
            label.text = getItem(position)

        }
    }

    fun getDataSourceItem(position: Int): Rate {
        return dataSource[position]
    }

    fun refreshDataSource(data: List<Rate>) {
        dataSource.clear()
        dataSource.addAll(data)
    }
}