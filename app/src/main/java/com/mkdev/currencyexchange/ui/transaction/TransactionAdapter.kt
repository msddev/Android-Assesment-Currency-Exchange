package com.mkdev.currencyexchange.ui.transaction

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mkdev.currencyexchange.base.BaseAdapter
import com.mkdev.currencyexchange.databinding.ItemTransactionListBinding
import com.mkdev.currencyexchange.extension.formatTwoDecimalNumber
import com.mkdev.domain.model.Transaction
import javax.inject.Inject

class TransactionAdapter @Inject constructor() : BaseAdapter<Transaction>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Transaction>() {
        override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
            return oldItem.currencyBalance == newItem.currencyBalance
        }
    }

    override val differ: AsyncListDiffer<Transaction> = AsyncListDiffer(this, diffCallback)

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemTransactionListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return VenueViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    inner class VenueViewHolder(private val binding: ItemTransactionListBinding) :
        RecyclerView.ViewHolder(binding.root), Binder<Transaction> {
        override fun bind(item: Transaction) {
            binding.apply {
                textViewTransferFromHeader.text = item.fromCurrency.uppercase()
                textViewTransferToHeader.text = item.toCurrency.uppercase()
                textViewTransferFrom.text = item.fromCurrency.uppercase()
                textViewTransferTo.text = item.toCurrency.uppercase()
                textViewTransferFromAmount.text =
                    item.fromAmount.formatTwoDecimalNumber().toString()
                textViewTransferToAmount.text = item.toAmount.formatTwoDecimalNumber().toString()
                textViewCurrentBalance.text =
                    item.currencyBalance.formatTwoDecimalNumber().toString()
                textViewCommissionFee.text = item.commissionFee.formatTwoDecimalNumber().toString()

                root.setOnClickListener {
                    onItemClickListener?.let { itemClick ->
                        itemClick(item)
                    }
                }
            }
        }
    }
}