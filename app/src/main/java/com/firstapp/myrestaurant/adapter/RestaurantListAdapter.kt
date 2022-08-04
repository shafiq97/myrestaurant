package com.firstapp.myrestaurant.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firstapp.myrestaurant.R
import com.firstapp.myrestaurant.model.RestaurantDetail
import kotlinx.android.synthetic.main.list_item_restaurant.view.*

class RestaurantListAdapter(private val onItemClicked: (position: Int) -> Unit) : RecyclerView.Adapter<RestaurantListAdapter.MyViewHolder>() {

    private var listData: List<RestaurantDetail>? = null

    fun setListData(listData: List<RestaurantDetail>?) {
        this.listData = listData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_restaurant, parent, false)

        return MyViewHolder(view, onItemClicked)
    }

    fun getItem(position: Int): RestaurantDetail? {
        return listData?.get(position)
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


    class MyViewHolder(itemView: View, private val onItemClicked: (position: Int) -> Unit) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        val restaurantName = itemView.restaurant_name

        fun bind(data: RestaurantDetail) {
            restaurantName.text = data.name
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            onItemClicked(position)
        }

    }

}
