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
import kotlinx.android.synthetic.main.list_operating_hours.*

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

        var time: String = ""
        var time2: String = ""
        val data = ArrayList<String>()

        var first: String = ""
        var firstSplitted = listOf<String>()
        var weekDay = listOf<String>()
        var lastDay: String = ""
        var second: String = ""


        var firstSplittedArray : MutableList<String> = mutableListOf()

        if(res.contains("/")){

//            find day
            first = res.split("/")[0]
            firstSplitted = first.split(" ")
            weekDay = firstSplitted[0].split("-")
            lastDay = weekDay[1].replace(",", "")

//            firstSplittedArray = firstSplitted.toMutableList()
//            Log.d("fsplttedarray", firstSplittedArray.toString())

//            find time
            val regex = "-?[0-9]+(\\:[0-9]+)?".toRegex()
            for (elem in firstSplitted){
                Log.d("elem", elem.toString())
                if (elem.trim().equals("am", true)
                    || elem.trim().equals("pm", true)
                    || elem.matches(regex)){
                    firstSplittedArray.add(elem)
                }
            }
            for(elem in firstSplittedArray){
                if(elem == "am"){
                    time += "$elem-"
                }
                else if(elem == "pm"){
                    time += "$elem"
                }
                else if(elem.matches(regex)){
                    time += "$elem"
                }
                else{
                    "$elem"
                }
            }

//            Log.d("weekday", firstSplittedArray.toString())
//            Log.d("time", time.toString())

            for (day in days){
                data.add("$day $time")
                if (day == lastDay){
                    break
                }
            }



// ############################################################
            var secondSplitted = listOf<String>()
            var secondSplittedArray : MutableList<String> = mutableListOf()
            var day2: MutableList<String> = mutableListOf()

            second = res.split("/")[1]
            secondSplitted = second.split(" ")
            Log.d("secondsplit", secondSplitted.toString())
            for(elem in secondSplitted){
                if (!elem.matches(regex) && elem != "pm" && elem != "am" && elem != "-" && elem != "," && elem.isNotEmpty()){
                    day2.add(elem)
                }
            }
            Log.d("day2", day2.toString())
            for(elem in day2){
                lastDay = if (elem.contains("-")){
                    elem.split("-")[1]
                } else{
                    elem
                }
            }

//            firstSplittedArray = firstSplitted.toMutableList()
//            Log.d("fsplttedarray", firstSplittedArray.toString())

//            find time
            for (elem in secondSplitted){
                Log.d("elem", elem.toString())
                if (elem.trim().equals("am", true)
                    || elem.trim().equals("pm", true)
                    || elem.matches(regex)){

                    secondSplittedArray.add(elem)
                }
            }
            var i: Int = 0
            for(elem in secondSplittedArray){
                Log.d("ELEM", elem)
                Log.d("got here pm", (secondSplittedArray.size).toString())
                Log.d("got here pm2", (i.toString()))
                if(elem == "am"){
                    time2 += "$elem-"
                }
                else if(elem == "pm"){
                    time2 += "$elem"
                }
                else if(elem.matches(regex)){
                    time2 += "$elem"
                }
                else{
                    "$elem"
                }
                i++
            }


            var lastDay2: List<String> = mutableListOf()
            for(elem in day2){
                if(elem.contains("-")){
                    lastDay2 = elem.split("-")
                    lastDay = lastDay2[1]
                }
            }

            for (day in lastDay2){
                data.add("$day $time2")
                if (day == lastDay){
                    break
                }
            }

            if(first.contains("Sun") && lastDay!="Sun"){
                data.add("Sun $time")
            }

        }
        else{

            firstSplitted = res.split(" ").toMutableList()
            weekDay = firstSplitted[0].split("-")
            lastDay = weekDay[1]
            Log.d("WEEKDAY", lastDay.toString())

            //            find time
            val regex = "-?[0-9]+(\\:[0-9]+)?".toRegex()
            for (elem in firstSplitted){
                Log.d("elem", elem.toString())
                if (elem.trim().equals("am", true)
                    || elem.trim().equals("pm", true)
                    || elem.matches(regex)){
                    firstSplittedArray.add(elem)
                }
            }
            for(elem in firstSplittedArray){
                if(elem == "am"){
                    time += "$elem-"
                }
                else if(elem == "pm"){
                    time += "$elem"
                }
                else if(elem.matches(regex)){
                    time += "$elem"
                }
                else{
                    "$elem"
                }
            }

            for (day in days){
                data.add("$day $time")
                if (day == lastDay){
                    break
                }
            }

            if(firstSplitted.contains("Sun") && lastDay!="Sun"){
                data.add("Sun $time")
            }

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