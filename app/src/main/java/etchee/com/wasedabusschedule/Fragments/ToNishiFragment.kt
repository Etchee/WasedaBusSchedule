package etchee.com.wasedabusschedule.Fragments

import android.database.Cursor
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import etchee.com.wasedabusschedule.Data.DataContract
import etchee.com.wasedabusschedule.R

/**
 * Fragment showing a list of departures to Nishi campus
 * Created by rikutoechigoya on 2017/05/24.
 */
class ToNishiFragment: Fragment() {
    val TAG:String = javaClass.simpleName
    var cursor: Cursor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cursor = context.contentResolver.query(
                DataContract.DB_TO_WASEDA().CONTENT_URI,
                null,
                null,
                null,
                null
        )
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_fragment_nishi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById(R.id.recyclerView_toNishi) as RecyclerView
        val layoutManager = LinearLayoutManager(context.applicationContext)
        val adapter = ToNishiAdapter(context.applicationContext, cursor)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        if (cursor != null) (cursor as Cursor).close()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
    }


}