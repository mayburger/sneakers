package com.mayburger.sneakers.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class SneakerResponseModel(
	val count: Int? = null,
	val results: List<Sneaker?>? = null
)


@Parcelize
data class Sneaker(
	val gender: String? = null,
	val releaseDate: String? = null,
	val year: Int? = null,
	val styleId: String? = null,
	val name: String? = null,
	val colorway: String? = null,
	val id: String? = null,
	val media: Media? = null,
	val title: String? = null,
	val brand: String? = null,
	val retailPrice: Int? = null,
	val shoe: String? = null
): Parcelable

@Parcelize
data class Media(
	val smallImageUrl: String? = null,
	val imageUrl: String? = null,
	val thumbUrl: String? = null
):Parcelable

