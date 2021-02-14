package com.mayburger.sneakers.ui.viewmodels

import com.mayburger.sneakers.R
import com.mayburger.sneakers.ui.base.BaseCommonViewModel

class ItemSizesViewModel(val size:String): BaseCommonViewModel() {

    override fun layoutId(): Int {
        return R.layout.item_sizes
    }
}