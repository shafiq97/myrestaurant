package com.firstapp.myrestaurant.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firstapp.myrestaurant.R
import com.firstapp.myrestaurant.model.RestaurantDetail
import kotlinx.android.synthetic.main.activity_restaurant_openings.view.*
import kotlinx.android.synthetic.main.list_operating_hours.view.*

class RestaurantDetailAdapter : RecyclerView.Adapter<RestaurantDetailAdapter.MyViewHolder>()  {

    private var listData: List<RestaurantDetail>? = null

    fun setListData(listData: List<RestaurantDetail>?) {
        this.listData = listData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.list_operating_hours, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listData?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if(listData ==null)return 0
        return listData?.size!!
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val restaurantName = view.restaurant_name1
        val day = view.day

        fun bind(data: RestaurantDetail) {
            restaurantName.text = data.name
            day.text = data.operatingHours

        }
    }
}
