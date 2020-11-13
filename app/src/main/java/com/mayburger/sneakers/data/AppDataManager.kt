package com.mayburger.sneakers.data

import android.content.Context
import com.google.gson.Gson
import com.mayburger.sneakers.R
import com.mayburger.sneakers.data.hawk.HawkHelper
import com.mayburger.sneakers.data.remote.ApiHelper
import com.mayburger.sneakers.models.Brand
import com.mayburger.sneakers.models.Sneaker
import com.mayburger.sneakers.models.SneakerResponseModel
import com.mayburger.sneakers.util.getJsonStringFromAssets
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppDataManager @Inject constructor(
    private val mContext: Context,
    private val mHawkHelper: HawkHelper,
    private val mApiHelper: ApiHelper
) : DataManager {
    override fun getBrands(): ArrayList<Brand> {
        return arrayListOf(
            Brand("adidas", 0, R.drawable.ic_adidas),
            Brand("nike", 1, R.drawable.ic_nike),
            Brand("reebok", 2, R.drawable.ic_reebok),
            Brand("puma", 3, R.drawable.ic_puma),
            Brand("vans", 4, R.drawable.ic_vans),
            Brand("jordan", 5, R.drawable.ic_jordan),
        )
    }

    override suspend fun getSneakers(limit: Int, brand: String): SneakerResponseModel {
        return Gson().fromJson<SneakerResponseModel>(getJsonStringFromAssets(mContext,"sneakers/sneakers.json"),SneakerResponseModel::class.java)
//        return mApiHelper.getSneakers(limit,brand)
    }

}