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
    val brandData = arrayOf(
        Brand("adidas", 0, R.drawable.ic_adidas),
        Brand("nike", 1, R.drawable.ic_nike),
        Brand("reebok", 2, R.drawable.ic_reebok),
        Brand("puma", 3, R.drawable.ic_puma),
        Brand("vans", 4, R.drawable.ic_vans),
        Brand("jordan", 5, R.drawable.ic_jordan),
    )
}