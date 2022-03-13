package com.mkdev.currencyexchange.ui.rate

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mkdev.currencyexchange.base.BaseAdapter
import com.mkdev.currencyexchange.databinding.ItemRateListBinding
import com.mkdev.domain.model.Rate
import javax.inject.Inject

class RateAdapter @Inject constructor() : BaseAdapter<Rate>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Rate>() {
        override fun areItemsTheSame(oldItem: Rate, newItem: Rate): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: Rate, newItem: Rate): Boolean {
            return oldItem.currencyName == newItem.currencyName
        }
    }

    override val differ: AsyncListDiffer<Rate> = AsyncListDiffer(this, diffCallback)

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemRateListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return VenueViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    inner class VenueViewHolder(private val binding: ItemRateListBinding) :
        RecyclerView.ViewHolder(binding.root), Binder<Rate> {
        override fun bind(item: Rate) {
            binding.apply {
                textViewCurrencyName.text = item.currencyName
                textViewRate.text = item.rate.toString()

                root.setOnClickListener {
                    onItemClickListener?.let { itemClick ->
                        itemClick(item)
                    }
                }
            }
        }
    }
}