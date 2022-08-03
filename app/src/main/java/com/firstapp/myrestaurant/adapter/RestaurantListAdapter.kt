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

    private lateinit var mListener: onItemClickListener

//    var onItemClick: ((RestaurantDetail) -> Unit)? = null

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    fun setListData(listData: List<RestaurantDetail>?) {
        this.listData = listData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_restaurant, parent, false)
        return MyViewHolder(view, mListener)
//        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.bind(listData?.get(position)!!)

        val m = listData?.get(position)
//        holder.bind(m!!)



    }

    override fun getItemCount(): Int {
        if (listData == null) return 0
        return listData?.size!!
    }

        class  MyViewHolder(val view: View, listener: onItemClickListener): RecyclerView.ViewHolder(view) {
//    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val restaurantName = view.restaurant_name




        init {
            itemView.setOnClickListener {
                listener.onItemClick(bindingAdapterPosition)
            }
        }

//        fun bind(data: RestaurantDetail) {
//            restaurantName.text = data.name
//        }
    }

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }
}
