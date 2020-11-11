package com.mayburger.sneakers.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mayburger.sneakers.databinding.ItemEmptyBinding

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun onBind(position: Int)


    class EmptyViewHolder(private val mBinding: ItemEmptyBinding) :
        BaseViewHolder(mBinding.root) {
        override fun onBind(position: Int) {
        }
    }
}
