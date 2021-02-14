package com.mayburger.sneakers.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.mayburger.sneakers.data.DataManager
import com.mayburger.sneakers.ui.base.BaseViewModel
import com.mayburger.sneakers.util.rx.SchedulerProvider

class MainViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<MainNavigator>(dataManager, schedulerProvider) {
    override fun onEvent(obj: Any) {
    }

    var currentIndex = MutableLiveData(0)
    var currentBrandName = MutableLiveData("adidas")
    val brands = dataManager.getBrands()
}