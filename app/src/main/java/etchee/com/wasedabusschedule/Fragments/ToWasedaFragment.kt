package etchee.com.wasedabusschedule.Fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import etchee.com.wasedabusschedule.R
import kotlinx.android.synthetic.main.layout_fragment_waseda.*
import java.util.*

/**
 * Fragment to actually display the list of bus departures
 * Created by rikutoechigoya on 2017/05/24.
 */
class ToWasedaFragment: android.support.v4.app.Fragment() {

    val TAG: String = javaClass.simpleName
    var mAdapter:ToWasedaAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_fragment_waseda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //SHOW LIST IF THERE ARE BUS LEFT
        if (busExists()){

            empty_container.visibility = View.GONE
            waseda_fragment_layout_background.visibility = View.GONE

            //RecyclerView
            recyclerView_toWaseda.layoutManager = LinearLayoutManager(context.applicationContext) as RecyclerView.LayoutManager?
            mAdapter = ToWasedaAdapter(context)
            recyclerView_toWaseda.adapter = mAdapter

            //PULL TO REFRESH
            waseda_swipetoRefreshContainer.setOnRefreshListener {
                waseda_swipetoRefreshContainer?.isRefreshing = false
            }
        }

        else if (!busExists()){
            empty_container.visibility = View.VISIBLE
            waseda_fragment_layout_background.visibility = View.VISIBLE
            empty_last_bus_time.text = lastBusTime()
        }
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

    fun busExists():Boolean{
        /**
         *  Last bus info:
         *
         *  ToWaseda
         *  Weekdays -> 18:25
         *  Saturday -> 16:20
         *
         *  ToNishi
         *  Weekdays -> 18:10
         *  Saturday -> 16:35
         */
        val calendar = Calendar.getInstance()
        val nowString = calendar.get(Calendar.HOUR_OF_DAY).toString() + formatDate(calendar.get(Calendar.MINUTE))
        val now_key = nowString.toInt()
        var finalBus_key = 0
        val day = calendar.get(Calendar.DAY_OF_WEEK)
        when (day) { // 1 is sunday
            1->finalBus_key = 0
            7->finalBus_key = 1620

            else-> finalBus_key = 1825
        }

        return now_key < finalBus_key
    }

    fun formatDate(value:Int):String{

        if (value < 10){
            return "0" + value.toString()
        }

        else return value.toString()
    }

    override fun onResume() {
        super.onResume()
    }
}