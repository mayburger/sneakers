package com.mayburger.sneakers.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.mayburger.sneakers.R
import com.mayburger.sneakers.data.DataManager
import com.mayburger.sneakers.models.Brand
import com.mayburger.sneakers.models.Sneaker
import com.mayburger.sneakers.ui.adapters.viewmodels.ItemMainViewModel
import com.mayburger.sneakers.ui.base.BaseViewModel
import com.mayburger.sneakers.util.rx.SchedulerProvider
import kotlinx.coroutines.Dispatchers.IO
import java.lang.Exception

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