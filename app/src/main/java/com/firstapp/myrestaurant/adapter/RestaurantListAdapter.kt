package com.firstapp.myrestaurant.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firstapp.myrestaurant.DetailedActivity
import com.firstapp.myrestaurant.R
import com.firstapp.myrestaurant.model.RestaurantDetail
import kotlinx.android.synthetic.main.list_item_restaurant.view.*


class RestaurantListAdapter() : RecyclerView.Adapter<RestaurantListAdapter.MyViewHolder>() {

    private var listData: List<RestaurantDetail>? = null

    fun setListData(listData: List<RestaurantDetail>?) {
        this.listData = listData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_restaurant, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.bind(listData?.get(position)!!)

        val m = listData?.get(position)
        holder.bind(m!!)
    }

    override fun getItemCount(): Int {
        if (listData == null) return 0
        return listData?.size!!
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val restaurantName = view.restaurant_name

        fun bind(data: RestaurantDetail) {
            restaurantName.text = data.name
        }
    }

}
