package etchee.com.wasedabusschedule.Fragments

import android.database.Cursor
import android.database.DatabaseUtils
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.Toast
import etchee.com.wasedabusschedule.Data.DataContract
import etchee.com.wasedabusschedule.R
import kotlinx.android.synthetic.main.layout_fragment_nishi.*
import kotlinx.android.synthetic.main.layout_fragment_nishi.view.*
import java.util.*

/**
 * Fragment showing a list of departures to Nishi campus
 * Created by rikutoechigoya on 2017/05/24.
 */
class ToNishiFragment: Fragment() {


    val TAG: String = javaClass.simpleName
    lateinit var mAdapter:ToNishiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_fragment_nishi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //view assignment

        //create cursor containing appropriate table depending on the day
        mAdapter = ToNishiAdapter(context, createCursor())

        //RecyclerView init
        recyclerView_toNishi.layoutManager = LinearLayoutManager(context.applicationContext)
        recyclerView_toNishi.adapter = mAdapter

        //Pull to refresh setting
        nishi_swipeToRefresh.setOnRefreshListener {
            refreshAdapter()
        }
    }

    fun refreshAdapter(){
        mAdapter.swapCursor(createCursor())
        nishi_swipeToRefresh.isRefreshing = false
    }

    /**
     *  今の時刻を取得して、それ以降に出るバスの分の情報をCursorに乗っけて持ってくる！
     */
    fun createCursor(): Cursor? {
        var cursor:Cursor?

        //Get the current time as an instance
        val now = Date()
        val calendar = Calendar.getInstance(Locale.JAPAN)
        calendar.time = now

        //extract the numbers
        val day = calendar.get(Calendar.DAY_OF_WEEK)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val min = calendar.get(Calendar.MINUTE)
        val search_key = getKey(min, hour)  //get the integer value of the current time. i.g. 9:09AM → 909
        //So just need to find rows with key bigger than 909
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
                val selection = DataContract.SATURDAY_DB_TO_NISHI().COLUMN_SEARCH + " > ?"
                val selectionArgs = arrayOf(search_key.toString())
                cursor = context.contentResolver.query(
                        DataContract.SATURDAY_DB_TO_NISHI().CONTENT_URI,
                        null,
                        selection,
                        selectionArgs,
                        null
                )
            }

            else->{ //WeekDay Table
                val selection = DataContract.DB_TO_NISHI().COLUMN_SEARCH + " > ?"
                val selectionArgs = arrayOf(search_key.toString())
                cursor = context.contentResolver.query(
                        DataContract.DB_TO_NISHI().CONTENT_URI,
                        null,
                        selection,
                        selectionArgs,
                        null
                )
            }
        }
        Log.v(TAG, "Generated concat current time value is: " + search_key.toString())
//        Log.v(TAG, DatabaseUtils.dumpCursorToString(cursor))
        return cursor
    }

    override fun onResume() {
        super.onResume()
        refreshAdapter()
    }

    private fun processMin(min:Int):String {
        val str:String
        if (min < 10) {
            str = "0" + min.toString()
            return str
        } else return min.toString()
    }

    private fun getKey(min: Int, hour:Int):Int{
        //when min is less than 10, append 0
        val minStr = processMin(min)
        val str = hour.toString() + minStr  //so this becomes like 909 for 9:09AM

        return Integer.parseInt(str)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
    }

}