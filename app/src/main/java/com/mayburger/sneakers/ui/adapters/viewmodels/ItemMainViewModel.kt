package com.mayburger.sneakers.ui.adapters.viewmodels

import com.mayburger.sneakers.models.Sneaker

class ItemMainViewModel(val sneaker: Sneaker){
    val brand = "${sneaker.brand?.get(0)?.toUpperCase()}${sneaker.brand?.subSequence(1,
        sneaker.brand.length
    )}"
    val title = "${sneaker.title?.get(0)?.toUpperCase()}${sneaker.title?.subSequence(1,
        sneaker.title.length
    )}"
    val price = "$${sneaker.retailPrice}"
}