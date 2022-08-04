package com.firstapp.myrestaurant

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.firstapp.myrestaurant.adapter.RestaurantDetailAdapter
import com.firstapp.myrestaurant.viewmodel.MainActivityViewModel

class DetailedActivity : AppCompatActivity() {

    private lateinit var restaurantDetailAdapter: RestaurantDetailAdapter
    val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        val res:String? = intent.getStringExtra("operatingHours").toString()
        val resName:String? = intent.getStringExtra("resName").toString()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_openings)

        if (res != null) {
            val restaurantName : TextView = findViewById(R.id.restaurant_name1)
//            val day : TextView = findViewById(R.id.day)
            val resSplit = res.split(" ")

            restaurantName.text = resSplit.toString()
//            day.text = res
//            initRecyclerView()
//            initViewModel()

        }
    }


}
