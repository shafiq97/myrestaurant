package com.firstapp.myrestaurant.model

import android.os.Parcel
import android.os.Parcelable

data class RestaurantList(
    val restaurants: List<RestaurantDetail>
)
