package etchee.com.wasedabusschedule.Fragments

import android.content.Context
import android.database.Cursor
import android.database.DatabaseUtils
import android.os.Handler
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
import java.text.SimpleDateFormat
import java.util.*


/**
 * Adapter for the RecyclerView
 * Created by rikutoechigoya on 2017/05/23.
 */
class ToWasedaAdapter(val context: Context, val cursor: Cursor?) : RecyclerView.Adapter<ToWasedaAdapter.ViewHolder>() {

    private var TAG: String = javaClass.simpleName
    var handler: Handler? = null
    var runnable: Runnable? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup?, viewType: Int): ViewHolder? {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_single, viewGroup, false))
     }

    /**
     *  Position 0 → next bus leaving Waseda.
     *
     *  Get the current time, query the SQL bus schedule table and then display from there.
     */
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Glide.with(context)
                .load(R.drawable.img_okuma)
                .into(viewHolder.image_background)

        if (cursor == null) {
            //don't do anything
        }else{
            cursor.moveToPosition(position)
            //extract the time info from the database
            val hour = cursor.getString(cursor.getColumnIndex(DataContract.DB_TO_WASEDA().COLUMN_HOUR))
            val min = cursor.getString(cursor.getColumnIndex(DataContract.DB_TO_WASEDA().COLUMN_MIN))
            val departureTime = hour + ":" + min

            //set the departure time
            viewHolder.departure_time_text.text = departureTime

            //start the countdown
            countDownStart(
                    viewHolder,
                    hour,
                    min
            )
        }

    }

    override fun getItemCount(): Int {
        if (cursor == null) {
            Log.e(TAG, "Cursor is null")
            return 0
        } else {
            return cursor.count
        }
    }

    override fun onViewRecycled(holder: ViewHolder?) {
        super.onViewRecycled(holder)
    }

    //function to start countdown
    fun countDownStart(viewHolder: ViewHolder, hour:String?, min:String?) {
        handler = Handler()
        runnable = object : Runnable {
            override fun run() {
                (handler)?.postDelayed(this, 1000)
                try {
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.JAPAN)
                    // Here Set your Event Date
                    val eventDate = dateFormat.parse("2017-12-30-$hour-$min-00")
                    val currentTime = Date()

                    if (!currentTime.after(eventDate)) {
                        var diff = eventDate.time - currentTime.time
                        val days = diff / (24 * 60 * 60 * 1000)
                        diff -= days * (24 * 60 * 60 * 1000)
                        val hours = diff / (60 * 60 * 1000)
                        diff -= hours * (60 * 60 * 1000)
                        val minutes = diff / (60 * 1000)
                        diff -= minutes * (60 * 1000)
                        val seconds = diff / 1000

                        viewHolder.hour_text.text = String.format("%02d", hours)
                        viewHolder.min_text.text = String.format("%02d", minutes)
                        viewHolder.sec_text.text = String.format("%02d", seconds)
                    } else {
                        handler?.removeCallbacks(runnable)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        handler?.postDelayed(runnable, 0)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val hour_text = view.findViewById(R.id.item_hour) as TextView
        val min_text = view.findViewById(R.id.item_min) as TextView
        val sec_text = view.findViewById(R.id.item_sec) as TextView
        val image_background = view.findViewById(R.id.item_image) as ImageView
        val departure_time_text =view.findViewById(R.id.departure_time) as TextView
    }
}