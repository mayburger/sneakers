package com.mayburger.sneakers.data

import com.mayburger.sneakers.data.hawk.HawkHelper
import com.mayburger.sneakers.data.remote.ApiHelper
import com.mayburger.sneakers.models.Brand


interface DataManager : HawkHelper,ApiHelper{
    fun getBrands():ArrayList<Brand>
}