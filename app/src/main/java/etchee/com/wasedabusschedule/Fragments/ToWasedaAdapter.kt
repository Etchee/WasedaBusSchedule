package etchee.com.wasedabusschedule.Fragments

import android.content.Context
import android.database.Cursor
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import etchee.com.wasedabusschedule.Data.DataContract
import etchee.com.wasedabusschedule.R
import java.text.SimpleDateFormat
import java.util.*


/**
 * Adapter for the RecyclerView
 * Created by rikutoechigoya on 2017/05/23.
 */
class ToWasedaAdapter(val context:Context) : RecyclerView.Adapter<ToWasedaAdapter.ViewHolder>() {

    private var TAG: String = javaClass.simpleName
    var handler: Handler? = null
    var runnable: Runnable? = null


    override fun onCreateViewHolder(viewGroup: ViewGroup?, viewType: Int): ViewHolder? {
        val date = Date()

        return ViewHolder(viewGroup)
     }

    /**
     *  Position 0 → next bus leaving Waseda.
     *
     *  Get the current time, query the SQL bus schedule table and then display from there.
     */
    override fun onBindViewHolder(viewHolder: ViewHolder?, position: Int) {
        //get data from the Waseda Bus Schedule Table
        var cursor: Cursor? = null
        try {
            cursor = context.contentResolver.query(
                    DataContract.DB_TO_WASEDA().CONTENT_URI,
                    null,
                    null,
                    null,
                    null
                    )

            if (cursor?.moveToFirst() as Boolean) {
                countDownStart(viewHolder as ViewHolder)
            } else {
                throw IllegalArgumentException(TAG + ": Cursor null for full query.")
            }
        } catch(e: Exception) {
            e.printStackTrace()
        } finally {
            cursor?.close()
        }

    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onViewRecycled(holder: ViewHolder?) {
        super.onViewRecycled(holder)
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

                        viewHolder.hour_text.text = String.format("%02d", hours)
                        viewHolder.min_text.text = String.format("%02d", minutes)
                        viewHolder.sec_text.text = String.format("%02d", seconds)
                    } else {
//                        linearLayout1.setVisibility(View.VISIBLE)
//                        linearLayout2.setVisibility(View.GONE)
//                        tvEvent.setText("Android Event Start")
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


    class ViewHolder(view: View?) : RecyclerView.ViewHolder(view) {
        val hour_text = view?.findViewById(R.id.item_hour) as TextView
        val min_text = view?.findViewById(R.id.item_min) as TextView
        val sec_text = view?.findViewById(R.id.item_sec) as TextView
    }
}