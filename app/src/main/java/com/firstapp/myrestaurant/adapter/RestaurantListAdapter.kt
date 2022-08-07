package com.firstapp.myrestaurant.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firstapp.myrestaurant.R
import com.firstapp.myrestaurant.model.RestaurantDetail
import kotlinx.android.synthetic.main.list_item_restaurant.view.*
import java.text.SimpleDateFormat
import java.util.*

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
        val opening_state = itemView.item_opening_state

        fun bind(data: RestaurantDetail) {
            restaurantName.text = data.name
            opening_state.text = openingState(data)
        }

        private fun getTime(operating_day: String): String{
            val firstSplitted = operating_day.split(" ")
            var firstSplittedArray : MutableList<String> = mutableListOf()
            var time: String = ""
            val regex = "-?[0-9]+(\\:[0-9]+)?".toRegex()
            for (elem in firstSplitted){
                Log.d("elem", elem.toString())
                if ( elem.matches(regex)){
                    firstSplittedArray.add(elem)
                }
            }
            for(elem in firstSplittedArray){
                if(elem.matches(regex)){
                    time += "$elem "
                }
            }
            time = time.replace("-","")
            var opening = ""
            var closing = " "
            opening = time.split(" ")[0]
            closing = time.split(" ")[1]
            Log.d("TIME ", time)
            Log.d("TIME opening ", opening)
            Log.d("TIME closing ", closing)

            val displayFormat = SimpleDateFormat("HH:mm")
            val parseFormat = SimpleDateFormat("hh:mm a")
            val dateOpening: Date = parseFormat.parse("$opening AM") as Date
            val dateClosing: Date = parseFormat.parse("$closing PM") as Date

            val date = Date()
            date.hours = date.hours
            System.out.println(date)
            val simpDate: SimpleDateFormat
            simpDate = SimpleDateFormat("HH:mm")
            println(simpDate.format(date))

            if(simpDate.format(date) > displayFormat.format(dateOpening) && simpDate.format(date) < displayFormat.format(dateClosing)){
                return "Open"
            }
            return "Close"
        }

        private fun openingState(data: RestaurantDetail): CharSequence? {

            val currentTime = Calendar.getInstance()
            val hour = currentTime.get(Calendar.HOUR_OF_DAY)
            val minute = currentTime.get(Calendar.MINUTE)
            var day = currentTime.get(Calendar.DAY_OF_WEEK)

            val days: Array<String> = arrayOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat" , "Sun")

            val operating_hours = data.operatingHours
            var operating_days = mutableListOf<String>()
            var operating_days2 = mutableListOf<String>()
            var splitted = mutableListOf<String>()


            val map = mapOf(2 to "Mon", 3 to "Tue" , 4 to "Wed", 5 to "Thu", 6 to "Fri", 7 to "Sat", 1 to "Sun")
            val time = mutableListOf<String>()

            if (operating_hours.contains("/")){
                splitted = operating_hours.split("/").toMutableList()
                Log.d("here1", splitted.toString())
                for(s in splitted){
                    Log.d("here2", s)
                    if (s.contains(map.getValue(day))){
                        Log.d("here3", s)
                        return getTime(s)
                        break
                    }
                }
//                operating_days = splitted[0].split("-").toMutableList()
//                for(operating_day in operating_days){
//                    if (operating_day.contains(map.getValue(day))){
//                        getTime(operating_day)
////                        Log.d("here", splitted.toString())
////                        Log.d("here", operating_day)
////                        Log.d("here", "Open")
//                    }
//                }
            }

            return "Close"
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            onItemClicked(position)
        }

    }

}
