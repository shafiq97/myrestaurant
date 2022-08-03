package com.firstapp.myrestaurant.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firstapp.myrestaurant.dependencyInjection.RetroRepository
import com.firstapp.myrestaurant.dependencyInjection.RetroServiceInterface
import com.firstapp.myrestaurant.model.RestaurantDetail
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: RetroRepository): ViewModel() {

    @Inject
    lateinit var retroServiceInterface: RetroServiceInterface

    private var liveDataList: MutableLiveData<List<RestaurantDetail>> = MutableLiveData()

    fun getLiveDataObserver(): MutableLiveData<List<RestaurantDetail>> {
        return liveDataList
    }
    fun loadListOfData() {
        repository.makeApiCall(liveDataList)
    }
}
