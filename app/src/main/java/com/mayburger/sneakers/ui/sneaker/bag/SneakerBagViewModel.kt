package com.mayburger.sneakers.ui.sneaker.bag

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.mayburger.sneakers.data.DataManager
import com.mayburger.sneakers.models.Brand
import com.mayburger.sneakers.models.Sneaker
import com.mayburger.sneakers.models.events.BackEvent
import com.mayburger.sneakers.ui.base.BaseViewModel
import com.mayburger.sneakers.util.rx.SchedulerProvider

class SneakerBagViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<SneakerBagNavigator>(dataManager, schedulerProvider) {
    override fun onEvent(obj: Any) {
        when (obj) {
            is BackEvent -> {
                navigator?.onTriggerBack()
            }
        }
    }

    val sneaker: MutableLiveData<Sneaker> = MutableLiveData()
    val brand: MutableLiveData<Brand> = MutableLiveData()
    val price: MutableLiveData<String> = MutableLiveData()
    val name: MutableLiveData<String> = MutableLiveData()

    val size:MutableLiveData<String> = MutableLiveData()

    fun onClickBackground() {
        navigator?.onTriggerBack()
    }

}
