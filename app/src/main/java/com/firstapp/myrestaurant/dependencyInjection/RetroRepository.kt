package com.firstapp.myrestaurant.dependencyInjection

import androidx.lifecycle.MutableLiveData
import com.firstapp.myrestaurant.model.RestaurantDetail
import com.firstapp.myrestaurant.model.RestaurantList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetroRepository @Inject constructor(private val retroInstance: RetroServiceInterface) {

    fun makeApiCall(liveDataList: MutableLiveData<List<RestaurantDetail>>) {
        val call: Call<RestaurantList> = retroInstance.getDataFromAPI()
        call?.enqueue(object : Callback<RestaurantList>{
            override fun onFailure(call: Call<RestaurantList>, t: Throwable) {
                liveDataList.postValue(null)
            }

            override fun onResponse(call: Call<RestaurantList>, response: Response<RestaurantList>) {
                liveDataList.postValue(response.body()?.restaurants!!)
            }
        })

    }
}
