package etchee.com.wasedabusschedule.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import etchee.com.wasedabusschedule.Data.DataContract
import etchee.com.wasedabusschedule.Data.DataList
import etchee.com.wasedabusschedule.R
import kotlinx.android.synthetic.main.layout_fragment_nishi.*
import java.util.*

/**
 * Fragment showing a list of departures to Nishi campus
 * Created by rikutoechigoya on 2017/05/24.
 */
class ToNishiFragment: Fragment() {
    //Finished
    val TAG: String = javaClass.simpleName
    var mAdapter: MyAdapter? = null
    private var mArrayList = createArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_fragment_nishi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //SHOW LIST IF THERE ARE BUS LEFT
        if (!isPastLastBus()){

            nishi_empty_container.visibility = View.GONE
            nishi_fragment_layout_background.visibility = View.GONE

            recyclerView_toNishi.layoutManager = LinearLayoutManager(context.applicationContext)
            mAdapter = MyAdapter(context, null , this ,mArrayList, DataContract.GlobalConstants().MODE_NISHI)
            recyclerView_toNishi.adapter = mAdapter

            //PULL TO REFRESH
            nishi_swipetoRefreshContainer.setOnRefreshListener {
                if (!isPastLastBus()){
                    (mAdapter as MyAdapter).refreshDataSet(createArrayList())
                    nishi_swipetoRefreshContainer?.isRefreshing = false
                }else{
                    setEmptyMode()
                    nishi_swipetoRefreshContainer?.isRefreshing = false
                }

            }

        }else{
            setEmptyMode()
            nishi_swipetoRefreshContainer.setOnRefreshListener{
                nishi_swipetoRefreshContainer?.isRefreshing = false
            }
        }
    }

    fun setEmptyMode(){
        nishi_empty_container.visibility = View.VISIBLE
        nishi_fragment_layout_background.visibility = View.VISIBLE
        nishi_empty_last_bus_time.text = lastBusTime()
    }


    fun lastBusTime():String{
        /**
         *  Last bus info:
         *
         *  ToNishi
         *  Weekdays -> 18:10
         *  Saturday -> 16:35
         */
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_WEEK)
        when (day) { // 1 is sunday
            1->return getString(R.string.arimasen)
            7->return "16:35"
            else-> return "18:10"
        }
    }

    fun isPastLastBus():Boolean{
        val time = getIntTime()
        val hour = time.hour
        val hour_key = 1810
        val saturday_hour_key = 1635
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
                val model: java.util.ArrayList<DataList.DataModel> = DataList().createSat_NishiData()
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
                val model: java.util.ArrayList<DataList.DataModel> = DataList().createNishiData()
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