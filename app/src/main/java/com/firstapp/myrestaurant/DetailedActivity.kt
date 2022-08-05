package com.firstapp.myrestaurant

import android.os.Bundle
import android.text.TextUtils.split
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firstapp.myrestaurant.adapter.RestaurantDetailAdapter
import kotlinx.android.synthetic.main.activity_list_restaurants.*
import kotlinx.android.synthetic.main.activity_restaurant_openings.*

class DetailedActivity : AppCompatActivity() {

    private lateinit var restaurantDetailAdapter: RestaurantDetailAdapter
    val res: String = ""

    //    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        val res:String? = intent.getStringExtra("operatingHours").toString()
        val resName:String? = intent.getStringExtra("resName").toString()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_openings)

        if (res != null) {
            val restaurantName : TextView = findViewById(R.id.restaurant_name1)
//            val day : TextView = findViewById(R.id.day)
            val resSplit = res.split(" ")

            restaurantName.text = resName
            initRecyclerView(res)
//            initViewModel()
        }
    }

    private fun initRecyclerView(res: String) {

        val days: Array<String> = arrayOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat" , "Sun")
        var openingDays: MutableList<String> = ArrayList()
        var openingDays2: MutableList<String> = ArrayList()

        var time: String = ""
        var time2: String = ""
        val data = ArrayList<String>()

        var first: String = ""
        var firstSplitted = listOf<String>()
        var weekDay = listOf<String>()
        var second: String = ""
        if(res.contains("/")){

            first = res.split("/")[0]
            firstSplitted = first.split(" ")

            weekDay = firstSplitted[0].split("-")

            Log.d("weekday", weekDay.toString())

            for(elem in firstSplitted){
                if (elem != null && elem.toString() != ""){
                    Log.d("fSPLITTED", elem.toString())
                }
            }



            time = first.split(",").takeLast(1).toString().split("Sun")[1].replace("]", "")

            second  = res.split("/")[1]
            time2 = second.split(",").takeLast(1).toString().split(" ")[1]

            for (day in days){
                if (first.contains(day)){
                    openingDays.add(day)
                }
                if (second.contains(day)){
                    openingDays2.add(day)
                }
            }
            for(day in days){
                if(day != openingDays[1] || day != openingDays2[1]) {
                    if(day != openingDays[1] ){
                        data.add("$day $time")
                    }
                    else{
                        data.add("$day $time2")
                    }

                }
            }
        }
        else{
            first = "no /"
            second= "no /"
        }


        val recyclerview = findViewById<RecyclerView>(R.id.recycler_view_openings)

        val mLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL ,false)
        recyclerview.layoutManager = mLayoutManager
        restaurantDetailAdapter = RestaurantDetailAdapter(data)
        recyclerview.adapter =restaurantDetailAdapter

    }

//    private fun initViewModel() {
//        viewModel.getLiveDataObserver().observe(this, Observer {
//            if(it != null) {
//                restaurantDetailAdapter.setListData(it)
//                restaurantDetailAdapter.notifyDataSetChanged()
//            } else {
//                Toast.makeText(this, "error in getting data", Toast.LENGTH_SHORT).show()
//            }
//        })
//        viewModel.loadListOfData()
//    }


}
