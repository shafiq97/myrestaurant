package com.firstapp.myrestaurant.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firstapp.myrestaurant.R
import com.firstapp.myrestaurant.model.RestaurantDetail
import kotlinx.android.synthetic.main.activity_restaurant_openings.view.*
import kotlinx.android.synthetic.main.list_operating_hours.view.*

class RestaurantDetailAdapter(private val mList: List<String>) : RecyclerView.Adapter<RestaurantDetailAdapter.MyViewHolder>()  {

    private var listData: List<RestaurantDetail>? = null

    fun setListData(listData: List<RestaurantDetail>?) {
        this.listData = listData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.list_operating_hours, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val ItemsViewModel = mList[position]
        holder.textView.text = ItemsViewModel
//        holder.bind(mList[position]!!)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView: TextView = itemView.findViewById(R.id.day)
        val restaurantName = view.restaurant_name1
//        val day = view.day
//        fun bind(data: String) {
//            restaurantName.text = "haha"
//            day.text = "huhu"
//        }
    }

}
