package com.mkdev.currencyexchange.ui.exchange

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mkdev.currencyexchange.base.BaseAdapter
import com.mkdev.currencyexchange.databinding.ItemMyBalanceListBinding
import com.mkdev.domain.model.Balance
import javax.inject.Inject

class BalanceAdapter @Inject constructor() : BaseAdapter<Balance>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Balance>() {
        override fun areItemsTheSame(oldItem: Balance, newItem: Balance): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: Balance, newItem: Balance): Boolean {
            return oldItem.balance == newItem.balance
        }
    }

    override val differ: AsyncListDiffer<Balance> = AsyncListDiffer(this, diffCallback)

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemMyBalanceListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return VenueViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    inner class VenueViewHolder(private val binding: ItemMyBalanceListBinding) :
        RecyclerView.ViewHolder(binding.root), Binder<Balance> {
        override fun bind(item: Balance) {
            binding.apply {
                textViewBalance.text = "${item.balance} ${item.currencyName.uppercase()}"

                root.setOnClickListener {
                    onItemClickListener?.let { itemClick ->
                        itemClick(item)
                    }
                }
            }
        }
    }
}