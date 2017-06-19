package etchee.com.wasedabusschedule.Fragments

import android.database.Cursor
import android.database.DatabaseUtils
import android.os.Bundle
import android.provider.CalendarContract
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.Toast
import etchee.com.wasedabusschedule.Data.DataContract
import etchee.com.wasedabusschedule.Data.DataList
import etchee.com.wasedabusschedule.Interface.DatasetUpdate
import etchee.com.wasedabusschedule.R
import etchee.com.wasedabusschedule.R.id.recyclerView_toWaseda
import etchee.com.wasedabusschedule.R.id.waseda_swipetoRefreshContainer
import kotlinx.android.synthetic.main.layout_fragment_waseda.*
import java.util.*

/**
 * Fragment to actually display the list of bus departures
 * Created by rikutoechigoya on 2017/05/24.
 */
class ToWasedaFragment: android.support.v4.app.Fragment() {

    val TAG: String = javaClass.simpleName
    var mAdapter:ToWasedaAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_fragment_waseda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //view assignment

        //create cursor containing appropriate table depending on the day
        mAdapter = ToWasedaAdapter(context)

        //RecyclerView init
        recyclerView_toWaseda.layoutManager = LinearLayoutManager(context.applicationContext)
        recyclerView_toWaseda.adapter = mAdapter

        //Pull to refresh setting
        waseda_swipetoRefreshContainer.setOnRefreshListener {
            waseda_swipetoRefreshContainer?.isRefreshing = false
        }
    }

    private fun getKey(min: Int, hour:Int):Int{
        //when min is less than 10, append 0
        val minStr = processMin(min)
        val str = hour.toString() + minStr  //so this becomes like 909 for 9:09AM

        return Integer.parseInt(str)
    }

    override fun onResume() {
        super.onResume()
//        refreshAdapter()
    }

    private fun processMin(min:Int):String {
        val str:String
        if (min < 10) {
            str = "0" + min.toString()
            return str
        } else return min.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
    }
}