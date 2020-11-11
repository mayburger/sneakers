package com.mayburger.sneakers.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Brand(val name:String, val id:Int, val image:Int):Parcelable