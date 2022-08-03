package com.firstapp.myrestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.firstapp.myrestaurant.adapter.RestaurantDetailAdapter
import com.firstapp.myrestaurant.adapter.RestaurantListAdapter
import com.firstapp.myrestaurant.model.RestaurantDetail
import com.firstapp.myrestaurant.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_list_restaurants.*
import kotlinx.android.synthetic.main.activity_restaurant_openings.*
import kotlinx.android.synthetic.main.activity_restaurant_openings.view.*
import kotlinx.android.synthetic.main.list_operating_hours.view.*

class DetailedActivity : AppCompatActivity() {

    private lateinit var restaurantDetailAdapter: RestaurantDetailAdapter
    val viewModel: MainActivityViewModel by viewModels()

    private val res = intent.getParcelableExtra<RestaurantDetail>("restaurant")

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_openings)

        if (res != null) {
            val restaurantName : TextView = findViewById(R.id.restaurant_name1)
            val day : TextView = findViewById(R.id.day)

            restaurantName.text = res.name
            day.text = res.operatingHours
//            initRecyclerView()
//            initViewModel()

        }
    }


}
