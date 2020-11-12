package com.mayburger.sneakers.ui.main.fragments

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.mayburger.sneakers.data.DataManager
import com.mayburger.sneakers.models.Sneaker
import com.mayburger.sneakers.ui.adapters.viewmodels.ItemMainViewModel
import com.mayburger.sneakers.ui.base.BaseViewModel
import com.mayburger.sneakers.ui.main.MainNavigator
import com.mayburger.sneakers.util.rx.SchedulerProvider
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainFragmentViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<MainFragmentNavigator>(dataManager, schedulerProvider) {
    override fun onEvent(obj: Any) {
    }

    var currentBrandName = MutableLiveData("")
    val isLoaded = MutableLiveData(false)

    val sneakers = currentBrandName.switchMap {
        liveData(Dispatchers.IO) {
            try {
                if (it != "") {
                    emit(dataManager.getSneakers(15, it).results?.filter {
                        it?.media?.imageUrl != null
                    }?.filter{
                        it?.media?.imageUrl?.contains("Placeholder")?.not() ?: true
                    }?.map {
                        ItemMainViewModel(it ?: Sneaker())
                    })
                    isLoaded.postValue(true)
                }
            } catch (e: Exception) {
                isLoaded.postValue(true)
                e.printStackTrace()
            }
        }
    }
}