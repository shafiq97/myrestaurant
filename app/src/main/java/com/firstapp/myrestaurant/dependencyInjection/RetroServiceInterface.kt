package com.firstapp.myrestaurant.dependencyInjection

import com.firstapp.myrestaurant.model.RestaurantList
import retrofit2.Call
import retrofit2.http.GET

interface RetroServiceInterface {

    @GET("homework")
    fun getDataFromAPI(): Call<RestaurantList>

}
