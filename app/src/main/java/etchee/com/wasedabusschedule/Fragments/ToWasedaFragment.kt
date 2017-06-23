package etchee.com.wasedabusschedule.Fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import etchee.com.wasedabusschedule.Data.DataContract
import etchee.com.wasedabusschedule.Data.DataList
import etchee.com.wasedabusschedule.R
import kotlinx.android.synthetic.main.layout_fragment_waseda.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * Fragment to actually display the list of bus departures
 * Created by rikutoechigoya on 2017/05/24.
 */
class ToWasedaFragment: android.support.v4.app.Fragment() {

    val TAG: String = javaClass.simpleName
    var mAdapter: MyAdapter? = null
    private var mArrayList = createArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_fragment_waseda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //SHOW LIST IF THERE ARE BUS LEFT
        if (!isPastLastBus()){

            empty_container.visibility = View.GONE
            waseda_fragment_layout_background.visibility = View.GONE

            recyclerView_toWaseda.layoutManager = LinearLayoutManager(context.applicationContext)
            mAdapter = MyAdapter(context, this , null ,mArrayList, DataContract.GlobalConstants().MODE_WASEDA)
            recyclerView_toWaseda.adapter = mAdapter

            //PULL TO REFRESH
            waseda_swipetoRefreshContainer.setOnRefreshListener {
                if (!isPastLastBus()){
                    (mAdapter as MyAdapter).refreshDataSet(createArrayList())
                    waseda_swipetoRefreshContainer?.isRefreshing = false
                }else{
                    setEmptyMode()
                    waseda_swipetoRefreshContainer?.isRefreshing = false
                }

            }

        }else{
            setEmptyMode()
            waseda_swipetoRefreshContainer.setOnRefreshListener{
                waseda_swipetoRefreshContainer?.isRefreshing = false
            }
        }
    }

    fun setEmptyMode(){
        empty_container.visibility = View.VISIBLE
        waseda_fragment_layout_background.visibility = View.VISIBLE
        empty_last_bus_time.text = lastBusTime()
    }


    fun lastBusTime():String{
        /**
         *  Last bus info:
         *
         *  ToWaseda
         *  Weekdays -> 18:25
         *  Saturday -> 16:20
         */
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_WEEK)
        when (day) { // 1 is sunday
            1->return getString(R.string.arimasen)
            7->return "16:20"
            else-> return "18:25"
        }
    }

    fun formatDate(value:Int):String{

        if (value < 10){
            return "0" + value.toString()
        }

        else return value.toString()
    }

    fun isPastLastBus():Boolean{
        val time = getIntTime()
        val hour = time.hour
        val hour_key = 1825
        val saturday_hour_key = 1620
        val min = time.min
        val day = time.day

        val key = (hour.toString() + min.toString()).toInt()
        Log.v(TAG, "Key is: $key")
        /**
         *  Weekday: ToWaseda... 18:25
         *  Saturday: To Waseda... 16:20
         */

        when (day) {
            1 -> {
                return true
            }

            7 -> {    //土曜
                return key > saturday_hour_key

            }

            else -> { //Weekday
                return key > hour_key
            }
        }

    }

    fun getIntTime(): MyAdapter.mIntTime {
        val calendar = Calendar.getInstance()
        val mIntTime = MyAdapter.mIntTime(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_WEEK),
                calendar.get(Calendar.DATE),
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND)
        )

        return mIntTime
    }

    override fun onResume() {
        super.onResume()
    }

    /**
     *  gets time, then create mArrayList of appropriate size.
     */
    private fun createArrayList():ArrayList<DataList.DataModel>{
        //Inside the mArrayList is like
        //DataModel("15", "35", 0, 1535)
        //get the current time in 1440 format, iterate until now > key
        val time = getStringTime()
        val hour = time.hour
        val min = time.min
        val key = Integer.parseInt(hour + min)
        val day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        val list = arrayListOf<DataList.DataModel>()
        var count = -1

        when(day){
        //Sunday
            1-> return list //No bus on Sunday
            7->{
                val model: java.util.ArrayList<DataList.DataModel> = DataList().createSat_WasedaData()
                mArrayList?.clear()
                for (i in model){
                    if (i.search > key){
                        list.add(i)
                        count++
                    }
                }

                return list
            }
            else->{//weekday
                val model: java.util.ArrayList<DataList.DataModel> = DataList().createWasedaData()
                mArrayList?.clear()
                for (i in model){
                    if (i.search > key){
                        list.add(i)
                        count++
                    }
                }

                return list
            }
        }
    }

    fun getStringTime(): MyAdapter.mStringTime {
        val calendar = Calendar.getInstance()
        val mStringTime = MyAdapter.mStringTime(
                calendar.get(Calendar.YEAR).toString(),
                (calendar.get(Calendar.MONTH) + 1).toString(),
                calendar.get(Calendar.DAY_OF_WEEK).toString(),
                calendar.get(Calendar.DATE),
                calendar.get(Calendar.HOUR_OF_DAY).toString(),
                calendar.get(Calendar.MINUTE).toString(),
                calendar.get(Calendar.SECOND).toString()
        )

        return mStringTime
    }
}