package com.mayburger.sneakers.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.mayburger.sneakers.databinding.ItemEmptyBinding
import com.mayburger.sneakers.databinding.ItemMainBinding
import com.mayburger.sneakers.models.Sneaker
import com.mayburger.sneakers.ui.viewmodels.ItemMainViewModel
import com.mayburger.sneakers.ui.base.BaseViewHolder


class MainAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private val data: MutableList<ItemMainViewModel>
    private var mListener: Callback? = null

    init {
        this.data = ArrayList()
    }

    companion object {
        const val VIEW_TYPE_LOADING = 0
        const val VIEW_TYPE_NORMAL = 1
    }

    override fun getItemCount(): Int {
        return if (data.isNotEmpty()) {
            data.size
        } else {
            2
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemViewType(position: Int): Int {
        return if (data.isNotEmpty()) {
            VIEW_TYPE_NORMAL
        } else {
            VIEW_TYPE_LOADING
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_NORMAL -> {
                val viewBinding = ItemMainBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                MainViewHolder(viewBinding)
            }
            else -> {
                val viewBinding = ItemEmptyBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                BaseViewHolder.EmptyViewHolder(viewBinding)
            }
        }
    }

    fun addItems(data: ArrayList<ItemMainViewModel>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun clearItems() {
        data.clear()
        notifyDataSetChanged()
    }

    fun setListener(listener: Callback) {
        this.mListener = listener
    }

    interface Callback {
        fun onSelectedItem(sneaker: Sneaker,sharedCard:CardView)
    }

    inner class MainViewHolder(private val mBinding: ItemMainBinding) :
        BaseViewHolder(mBinding.root) {

        override fun onBind(position: Int) {
            if (data.isNotEmpty()) {
                val viewModel = data[position]
                ViewCompat.setTransitionName(mBinding.card, data[position].title)
                mBinding.root.setOnClickListener { mListener?.onSelectedItem(viewModel.sneaker,mBinding.card) }
                mBinding.viewModel = viewModel
            }
        }
    }
}