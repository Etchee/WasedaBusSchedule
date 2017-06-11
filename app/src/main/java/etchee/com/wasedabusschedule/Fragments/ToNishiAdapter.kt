package etchee.com.wasedabusschedule.Fragments

import android.content.Context
import android.database.Cursor
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import etchee.com.wasedabusschedule.R
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.android.synthetic.main.layout_fragment_nishi.*
import kotlinx.android.synthetic.main.layout_item_single.view.*

/**
 * RecyclerView Adapter for the ToNishi fragment
 * Created by rikutoechigoya on 2017/05/24.
 */
class ToNishiAdapter(val context: Context, val cursor: Cursor?) : android.support.v7.widget.RecyclerView.Adapter<ToNishiAdapter.ViewHolder>() {

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
                .load(R.drawable.nishi_improved)
                .into(viewHolder.image_background)

        countDownStart(viewHolder)
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

    override fun onViewRecycled(holder: ViewHolder?) {
        super.onViewRecycled(holder)
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

    //function to start countdown
    fun countDownStart(viewHolder: ViewHolder) {
        handler = Handler()
        runnable = object : Runnable {
            override fun run() {
                (handler)?.postDelayed(this, 1000)
                try {
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.JAPAN)
                    // Here Set your Event Date
                    val eventDate = dateFormat.parse("2017-12-30")
                    val currentDate = Date()
                    if (!currentDate.after(eventDate)) {
                        var diff = eventDate.time - currentDate.time
                        val days = diff / (24 * 60 * 60 * 1000)
                        diff -= days * (24 * 60 * 60 * 1000)
                        val hours = diff / (60 * 60 * 1000)
                        diff -= hours * (60 * 60 * 1000)
                        val minutes = diff / (60 * 1000)
                        diff -= minutes * (60 * 1000)
                        val seconds = diff / 1000

                        val hourText = String.format("%02d", hours)
                        val minText = String.format("%02d", minutes)
                        val secText = String.format("%02d", seconds)

                        viewHolder.bindTexts(hourText, minText, secText)

                    } else {
                        handler?.removeCallbacks(runnable)
                        // handler.removeMessages(0);
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        handler?.postDelayed(runnable, 0)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //property access cannot be used because of Glide library limitation
        val image_background = view.findViewById(R.id.item_image) as ImageView

        fun bindTexts(hour:String, min:String, sec:String){
            itemView.item_hour.text = hour
            itemView.item_min.text = min
            itemView.item_sec.text = sec
        }
    }
}