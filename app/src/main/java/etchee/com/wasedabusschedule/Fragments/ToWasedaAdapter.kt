package etchee.com.wasedabusschedule.Fragments

import android.content.Context
import android.database.Cursor
import android.os.CountDownTimer
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
import java.util.*
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit


/**
 * Adapter for the RecyclerView
 * Created by rikutoechigoya on 2017/05/23.
 */
class ToWasedaAdapter(val context: Context, val cursor: Cursor?) : RecyclerView.Adapter<ToWasedaAdapter.ViewHolder>() {

    private var TAG: String = javaClass.simpleName
    var handler: Handler? = null
    var runnable: Runnable? = null
    var itemsArray = arrayListOf<RecyclerScrollTemp>()

    val nowYear = Calendar.getInstance(Locale.JAPAN).get(Calendar.YEAR)
    val nowMonth = Calendar.getInstance(Locale.JAPAN).get(Calendar.MONTH) + 1
    val nowDate = Calendar.getInstance(Locale.JAPAN).get(Calendar.DATE)

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
        var hour = ""
        var min = ""
        var departureTime = ""

        //if cursor is null, do not do anything
        if (cursor == null) {

        }else {
            //background image is the same for all.
            Glide.with(context)
                    .load(R.drawable.img_okuma)
                    .into(viewHolder.image_background)

            // new data, create.
            if (itemsArray.size >= position) {
                cursor.moveToPosition(position)
                //extract the time info from the database
                hour = cursor.getString(cursor.getColumnIndex(DataContract.DB_TO_WASEDA().COLUMN_HOUR))
                min = cursor.getString(cursor.getColumnIndex(DataContract.DB_TO_WASEDA().COLUMN_MIN))
                departureTime = hour + ":" + min

                //set the departure time
                viewHolder.departure_time_text.text = departureTime

                //start the countdown
                countDownStart(
                        viewHolder,
                        hour,
                        min
                )

                //and save for restore.
                itemsArray.add(position, RecyclerScrollTemp(hour, min, departureTime))

            } else {    //Data has been created before, restore.
                val hour1 = itemsArray.get(position).hour_text
                val min1 = itemsArray.get(position).min_text
                val departureTime1 = itemsArray.get(position).departure_time_text

                //set the departure time
                viewHolder.departure_time_text.text = departureTime1

                //start the countdown
                countDownStart(
                        viewHolder,
                        hour1,
                        min1
                )
            }
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
/*        handler = Handler()
        runnable = object : Runnable {
            override fun run() {
                (handler)?.postDelayed(this, 1000)
                try {
                    val now = Date()
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.JAPAN)

                    //bus departure time
                    val departure = dateFormat.parse("$nowYear-$nowMonth-$nowDate-$hour-$min-00")

                    if (now.before(departure)) {
                        var diff = departure.time - now.time

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

        handler?.postDelayed(runnable, 0)*/

        val now = Calendar.getInstance().timeInMillis
        val dateFormat = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.JAPAN)

        //bus departure time
        val departure = dateFormat.parse("$nowYear-$nowMonth-$nowDate-$hour-$min-00")

        val cdt = object : CountDownTimer(departure.time, 1000) {  //throw in the bus schedule param here
            override fun onTick(timeremaining: Long) {
                var remaining = timeremaining
                val days = TimeUnit.MILLISECONDS.toDays(remaining)
                remaining -= TimeUnit.DAYS.toMillis(days)

                val hours = TimeUnit.MILLISECONDS.toHours(remaining)
                remaining -= TimeUnit.HOURS.toMillis(hours)

                val minutes = TimeUnit.MILLISECONDS.toMinutes(remaining)
                remaining -= TimeUnit.MINUTES.toMillis(minutes)

                val seconds = TimeUnit.MILLISECONDS.toSeconds(remaining)

                viewHolder.hour_text.text = hours.toString()
                viewHolder.min_text.text = minutes.toString()
                viewHolder.sec_text.text = seconds.toString()
            }

            override fun onFinish() {

            }
        }

        cdt.start()
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val hour_text = view.findViewById(R.id.item_hour) as TextView
        val min_text = view.findViewById(R.id.item_min) as TextView
        val sec_text = view.findViewById(R.id.item_sec) as TextView
        val image_background = view.findViewById(R.id.item_image) as ImageView
        val departure_time_text =view.findViewById(R.id.departure_time) as TextView
    }

    /**
     * Temporarily save recyclerView contents data to preserve contents after scrolling
     * Created by rikutoechigoya on 2017/06/02.
     */
    data class RecyclerScrollTemp(
            val hour_text:String,
            val min_text: String,
            val departure_time_text:String
    )
}