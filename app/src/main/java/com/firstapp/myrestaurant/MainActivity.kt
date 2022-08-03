package com.firstapp.myrestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.Observer
import com.firstapp.myrestaurant.adapter.RestaurantListAdapter
import com.firstapp.myrestaurant.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_list_restaurants.*
import kotlinx.android.synthetic.main.list_item_restaurant.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var restaurantListAdapter: RestaurantListAdapter
    val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_restaurants)

        initRecyclerView()
        initViewModel()

    }

    private fun initRecyclerView() {
        recycler_view_restaurants.layoutManager = LinearLayoutManager(this)
        restaurantListAdapter = RestaurantListAdapter()
        recycler_view_restaurants.adapter =restaurantListAdapter


//        restaurantListAdapter.setOnItemClickListener(object : RestaurantListAdapter.onItemClickListener {
//            override fun onItemClick(position: Int) {
//                val intent = Intent(this@MainActivity, DetailedActivity::class.java)
//                intent.putExtra("name",)
//
//
//            }
//
//        })
    }



    private fun initViewModel() {
        viewModel.getLiveDataObserver().observe(this, Observer {
            if(it != null) {
                restaurantListAdapter.setListData(it)
                restaurantListAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.loadListOfData()
    }
}
