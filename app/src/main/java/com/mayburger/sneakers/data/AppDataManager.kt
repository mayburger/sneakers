package com.mayburger.sneakers.data

import android.content.Context
import com.mayburger.sneakers.data.hawk.HawkHelper
import com.mayburger.sneakers.data.remote.ApiHelper
import com.mayburger.sneakers.models.Sneaker
import com.mayburger.sneakers.models.SneakerResponseModel
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppDataManager @Inject constructor(
    private val mContext: Context,
    private val mHawkHelper: HawkHelper,
    private val mApiHelper: ApiHelper
) : DataManager {

    override suspend fun getSneakers(limit: Int, brand: String): SneakerResponseModel {
        return mApiHelper.getSneakers(limit, brand)
    }

}