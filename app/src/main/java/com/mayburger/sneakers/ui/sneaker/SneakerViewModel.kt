package com.mayburger.sneakers.ui.sneaker

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.mayburger.sneakers.data.DataManager
import com.mayburger.sneakers.models.Brand
import com.mayburger.sneakers.models.Sneaker
import com.mayburger.sneakers.ui.base.BaseNavigator
import com.mayburger.sneakers.ui.base.BaseViewModel
import com.mayburger.sneakers.ui.main.MainNavigator
import com.mayburger.sneakers.util.rx.SchedulerProvider


class SneakerViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<SneakerNavigator>(dataManager, schedulerProvider) {
    override fun onEvent(obj: Any) {
    }

    val sneaker: MutableLiveData<Sneaker> = MutableLiveData()
    val brand: MutableLiveData<Brand> = MutableLiveData()
    val price: MutableLiveData<String> = MutableLiveData()
    val name: MutableLiveData<String> = MutableLiveData()

}