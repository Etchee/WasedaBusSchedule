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
import java.util.*

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

        cursor?.close()
    }

    /**
     *  今の時刻を取得して、それ以降に出るバスの分の情報をCursorに乗っけて持ってくる！
     */
    fun createCursor(): Cursor? {
        val cursor:Cursor?

        //Get the current time as an instance
        val now = Date()
        val calendar = Calendar.getInstance(Locale.JAPAN)
        calendar.time = now

        //extract the numbers
        val day = calendar.get(Calendar.DAY_OF_WEEK)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val min = calendar.get(Calendar.MINUTE)
        Log.v(TAG, "Fetched Day:" + day.toString() + " hour:" + hour.toString() + " min:" + min.toString())
        /*
                The table:

                |Hour column| |Minute Column|

                Compare the current hour against the hour column,
                then compare the minute column.
         */

        when(day){
            1->{    //No bus on Sunday
                cursor = null
            }

            7->{    //Saturday Table
                cursor = context.contentResolver.query(
                        DataContract.SATURDAY_DB_TO_WASEDA().CONTENT_URI,
                        null,
                        null,
                        null,
                        null
                )
            }

            else->{ //WeekDay Table
                cursor = context.contentResolver.query(
                        DataContract.DB_TO_WASEDA().CONTENT_URI,
                        null,   //projection
                        null,   //selection
                        null,
                        null
                )
            }
        }

        return cursor

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
    }
}