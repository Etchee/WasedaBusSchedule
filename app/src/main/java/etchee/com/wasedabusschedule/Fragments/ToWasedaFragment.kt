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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_fragment_waseda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //RecyclerView
        recyclerView_toWaseda.layoutManager = LinearLayoutManager(context.applicationContext)
        mAdapter = ToWasedaAdapter(context)
        recyclerView_toWaseda.adapter = mAdapter

        //PULL TO REFRESH
        waseda_swipetoRefreshContainer.setOnRefreshListener {
            waseda_swipetoRefreshContainer?.isRefreshing = false
        }
    }

    override fun onResume() {
        super.onResume()
    }
}