package etchee.com.wasedabusschedule.Fragments

import android.database.Cursor
import android.database.DatabaseUtils
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import etchee.com.wasedabusschedule.Data.DataContract
import etchee.com.wasedabusschedule.R

/**
 * Fragment to actually display the list of bus departures
 * Created by rikutoechigoya on 2017/05/24.
 */
class ToWasedaFragment: Fragment() {

    val TAG: String = javaClass.simpleName
    lateinit var cursor: Cursor
    lateinit var adapter:ToWasedaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cursor = context.contentResolver.query(
                DataContract.DB_TO_WASEDA().CONTENT_URI,
                null,
                null,
                null,
                null
        )
        Log.v(TAG, "CURSOR CONTENTS GOING INTO ADAPTER" + DatabaseUtils.dumpCursorToString(cursor))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_fragment_waseda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById(R.id.recyclerView_toWaseda) as RecyclerView
        val layoutManager = LinearLayoutManager(context.applicationContext)
        adapter = ToWasedaAdapter(context.applicationContext, cursor)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        if (cursor != null) (cursor as Cursor).close()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
    }
}