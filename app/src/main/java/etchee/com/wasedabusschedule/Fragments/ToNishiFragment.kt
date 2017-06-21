package etchee.com.wasedabusschedule.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import etchee.com.wasedabusschedule.R
import kotlinx.android.synthetic.main.layout_fragment_nishi.*
import kotlinx.android.synthetic.main.layout_fragment_waseda.*
import java.util.*

/**
 * Fragment showing a list of departures to Nishi campus
 * Created by rikutoechigoya on 2017/05/24.
 */
class ToNishiFragment: Fragment() {

    val TAG: String = javaClass.simpleName
    var mAdapter:ToNishiAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_fragment_nishi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val hint = getDayHintString(Calendar.getInstance().get(Calendar.DAY_OF_WEEK))
        Toast.makeText(context, hint, Toast.LENGTH_SHORT).show()

        //SHOW LIST IF THERE ARE BUS LEFT
        if (busExists()){

            nishi_empty_container.visibility = View.GONE
            nishi_fragment_layout_background.visibility = View.GONE

            //RecyclerView
            recyclerView_toNishi.layoutManager = LinearLayoutManager(context.applicationContext) as RecyclerView.LayoutManager?
            mAdapter = ToNishiAdapter(context)
            recyclerView_toNishi.adapter = mAdapter

            //PULL TO REFRESH
            nishi_swipetoRefreshContainer.setOnRefreshListener {
                nishi_swipetoRefreshContainer?.isRefreshing = false
            }
        }

        else if (!busExists()){
            nishi_empty_container.visibility = View.VISIBLE
            nishi_fragment_layout_background.visibility = View.VISIBLE
            nishi_empty_last_bus_time.text = lastBusTime()
        }
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

    fun busExists():Boolean{
        /**
         *  Last bus info:
         *
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
            7->finalBus_key = 1635

            else-> finalBus_key = 1810
        }

        return now_key < finalBus_key
    }

    fun formatDate(value:Int):String{

        if (value < 10){
            return "0" + value.toString()
        }

        else return value.toString()
    }


    private fun getDayHintString(day:Int):String {
        when (day) {
            1 -> return context.resources.getString(R.string.hint_text_sunday)
            2 -> return context.resources.getString(R.string.hint_text_monday)
            3 -> return context.resources.getString(R.string.hint_text_tuesday)
            4 -> return context.resources.getString(R.string.hint_text_wednesday)
            5 -> return context.resources.getString(R.string.hint_text_thursday)
            6 -> return context.resources.getString(R.string.hint_text_friday)
            7 -> return context.resources.getString(R.string.hint_text_saturday)
            else-> throw IllegalArgumentException(TAG + "Calendar did not recognize day.")
        }
    }

    override fun onResume() {
        super.onResume()
        val hint = getDayHintString(Calendar.getInstance().get(Calendar.DAY_OF_WEEK))
        Toast.makeText(context, hint, Toast.LENGTH_SHORT).show()
    }
}