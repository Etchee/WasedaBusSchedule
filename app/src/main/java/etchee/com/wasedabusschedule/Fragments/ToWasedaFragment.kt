package etchee.com.wasedabusschedule.Fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.Toast
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
        val hint = getDayHintString(Calendar.getInstance().get(Calendar.DAY_OF_WEEK))
        Toast.makeText(context, hint, Toast.LENGTH_SHORT).show()
        //RecyclerView
        recyclerView_toWaseda.layoutManager = LinearLayoutManager(context.applicationContext)
        mAdapter = ToWasedaAdapter(context)
        recyclerView_toWaseda.adapter = mAdapter

        //PULL TO REFRESH
        waseda_swipetoRefreshContainer.setOnRefreshListener {
            waseda_swipetoRefreshContainer?.isRefreshing = false
        }
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