package etchee.com.wasedabusschedule.Fragments

import android.content.Context
import android.database.Cursor
import android.database.DatabaseUtils
import android.os.CountDownTimer
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import etchee.com.wasedabusschedule.Data.DataContract
import etchee.com.wasedabusschedule.R
import java.util.*
import java.text.SimpleDateFormat


/**
 * Adapter for the RecyclerView
 * Created by rikutoechigoya on 2017/05/23.
 */
class ToWasedaAdapter(val context: Context, val cursor: Cursor?) : RecyclerView.Adapter<ToWasedaAdapter.ViewHolder>() {

    private var TAG: String = javaClass.simpleName
    var restoreDataList = arrayListOf<RecyclerScrollTemp>()  //to store data while scrolling
    //Users won't be using the bus around midnight -- no need to perform real-time date query.
    val nowYear = Calendar.getInstance(Locale.JAPAN).get(Calendar.YEAR)
    val nowMonth = Calendar.getInstance(Locale.JAPAN).get(Calendar.MONTH) + 1
    val nowDate = Calendar.getInstance(Locale.JAPAN).get(Calendar.DATE)
    val hourFormat = SimpleDateFormat("HH", Locale.JAPAN)
    val minFormat = SimpleDateFormat("mm", Locale.JAPAN)
    val secFormat = SimpleDateFormat("ss", Locale.JAPAN)

    var timerList = arrayListOf<CountDownTimer>()

    var viewHolderList = arrayListOf<ViewHolder>()

    override fun onCreateViewHolder(viewGroup: ViewGroup?, viewType: Int): ViewHolder? {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_single, viewGroup, false))
     }

    /**
     *  Position 0 → next bus leaving Waseda.
     *
     *  Get the current time, query the SQL bus schedule table and then display from there.
     */
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        //declare all the item contents
        var dep_hour = ""
        var dep_min = ""
        var departureTime = ""
        val flag:Int

        //if cursor is null, do not do anything
        if (cursor == null) {

        }else {
            //background image is the same for all.
            Glide.with(context)
                    .load(R.drawable.img_okuma)
                    .into(viewHolder.image_background)

            // --NEW VIEWHOLDER--
            if (viewHolderList.size == position) {

                //save the viewHolder in the list
                viewHolderList.add(viewHolder)

                //get data
                cursor.moveToPosition(position)
                //DEPARTURE TIME TEXTS
                dep_hour = cursor.getString(cursor.getColumnIndex(DataContract.DB_TO_WASEDA().COLUMN_HOUR))
                dep_min = cursor.getString(cursor.getColumnIndex(DataContract.DB_TO_WASEDA().COLUMN_MIN))
                departureTime = dep_hour + ":" + dep_min    //Concat text for display
                //ROUTE OPTION TEXTS
                flag = cursor.getInt(cursor.getColumnIndex(DataContract.DB_TO_WASEDA().COLUMN_FLAG))
                val routeOption = getRouteOption(flag)

                //AND DISPLAY
                viewHolderList[position].departure_time_text.text = departureTime
                viewHolderList[position].route_option_text.text = routeOption

                //GET COUNTDOWN COMPONENTS
                val dateFormat = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.JAPAN)
                val dep_time_obj:Date = dateFormat.parse("$nowYear-$nowMonth-$nowDate-$dep_hour-$dep_min-00")
                //cancel timer if exists, just in case
                viewHolderList[position].countdown?.cancel()
                //and start a new one
                timerList.add(position, mTimer(dep_time_obj, viewHolder, position))

                //SAVE THE STATE
                restoreDataList.add(position, RecyclerScrollTemp(dep_hour, dep_min, departureTime, dep_time_obj))

            } else {
                //--SAVED VIEWHOLDER--
                val dep_time_obj:Date = restoreDataList[position].departure_obj
                viewHolderList[position].departure_time_text.text = restoreDataList[position].departure_Time

                //cancel timer if exists
                viewHolderList[position].countdown?.cancel()
                //and start a new one
                timerList[position].cancel()
                timerList[position] = mTimer(dep_time_obj, viewHolder, position)

                viewHolderList[position].departure_time_text.text = departureTime
            }
        }
    }

    override fun getItemCount(): Int {
        if (cursor == null) {
            Log.e(TAG, "Cursor is null")
            return 0
        } else {
//            Log.v(TAG, DatabaseUtils.dumpCursorToString(cursor))
            return cursor.count
        }
    }

    private fun getRouteOption(flag:Int):String {
        when(flag) {
            0 -> {
                return context.resources.getString(R.string.waseda_flag_0)
            }
            1 -> {
                return context.resources.getString(R.string.waseda_flag_1)
            }
            2 -> {
                return context.resources.getString(R.string.waseda_flag_2)
            }
            3 -> {
                return context.resources.getString(R.string.waseda_flag_3)
            }
            4 -> {
                return context.resources.getString(R.string.waseda_flag_4)
            }
            5 -> {
                return context.resources.getString(R.string.waseda_flag_5)
            }
            6 -> {
                return context.resources.getString(R.string.waseda_flag_6)
            }
            7 -> {
                return context.resources.getString(R.string.waseda_flag_7)
            }
            8 -> {
                return context.resources.getString(R.string.waseda_flag_8)
            }
            else -> return ""
        }
    }

    private fun mTimer(dep_obj:Date, viewHolder:ViewHolder, position:Int):CountDownTimer{

        val remaining = dep_obj.time - Date().time

        val cdt = object : CountDownTimer(remaining, 1000) {  //throw in the bus schedule param here
            override fun onTick(milliSeconds: Long) {

                val hours = (milliSeconds / (1000*60*60)) % 24
                val minutes =  (milliSeconds / (1000*60)) % 60
                val seconds = (milliSeconds / 1000) % 60

                viewHolderList[position].hour_text.text = countFormatter(hours)
                viewHolderList[position].min_text.text = countFormatter(minutes)
                viewHolderList[position].sec_text.text = countFormatter(seconds)
//                viewholder.hour_text.text = hourFormat.format(hours).toString()
//                viewholder.min_text.text = minFormat.format(minutes).toString()
//                viewholder.sec_text.text = secFormat.format(seconds).toString()
                viewHolder.hour_text.text = hours.toString()
                viewHolder.min_text.text = minutes.toString()
                viewHolder.sec_text.text = seconds.toString()

                Log.v(TAG, "Remaining time computed: " + hours.toString() + ":"
                        + minutes.toString() + ":"
                        + seconds.toString() )
            }

            override fun onFinish() {

            }
        }

        return cdt
    }

    private fun countFormatter(num:Long):String{
        var str = ""
        if (num < 10) {
            str = "0" + num.toString()
        }else{
            str = num.toString()
        }

        return str
    }


    override fun onViewRecycled(holder: ViewHolder?) {
        super.onViewRecycled(holder)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val hour_text = view.findViewById(R.id.item_hour) as TextView
        val min_text = view.findViewById(R.id.item_min) as TextView
        val sec_text = view.findViewById(R.id.item_sec) as TextView
        val image_background = view.findViewById(R.id.item_image) as ImageView
        val departure_time_text = view.findViewById(R.id.departure_time) as TextView
        val route_option_text = view.findViewById(R.id.hint_route_text)as TextView
        val countdown:CountDownTimer? = null
    }

    /**
     * Temporarily save recyclerView contents data to preserve contents after scrolling
     * Created by rikutoechigoya on 2017/06/02.
     */
    data class RecyclerScrollTemp(
            val hour_text:String,
            val min_text: String,
            val departure_Time:String,
            val departure_obj:Date
    )
}