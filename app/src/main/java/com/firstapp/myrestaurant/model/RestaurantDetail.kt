package com.firstapp.myrestaurant.model

import android.os.Parcel
import android.os.Parcelable

data class RestaurantDetail(
    val name: String,
    val operatingHours: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(operatingHours)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RestaurantDetail> {
        override fun createFromParcel(parcel: Parcel): RestaurantDetail {
            return RestaurantDetail(parcel)
        }

        override fun newArray(size: Int): Array<RestaurantDetail?> {
            return arrayOfNulls(size)
        }
    }
}
