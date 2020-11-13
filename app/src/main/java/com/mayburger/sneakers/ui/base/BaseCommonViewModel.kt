package com.mayburger.sneakers.ui.base

import androidx.lifecycle.MutableLiveData

abstract class BaseCommonViewModel {
    val selected:MutableLiveData<Boolean> = MutableLiveData(false)
    abstract fun layoutId(): Int
}