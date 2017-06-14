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
import android.widget.Toast
import com.bumptech.glide.Glide
import etchee.com.wasedabusschedule.Data.DataContract
import etchee.com.wasedabusschedule.R
import kotlinx.android.synthetic.main.layout_item_single.view.*
import java.util.*
import java.text.SimpleDateFormat


/**
 * Adapter for the RecyclerView
 * Created by rikutoechigoya on 2017/05/23.
 */
class ToWasedaAdapter(val context: Context, var cursor: Cursor?) : RecyclerView.Adapter<ToWasedaAdapter.ViewHolder>() {


    private var TAG: String = javaClass.simpleName
    var handler: Handler? = null
    var runnable: Runnable? = null
    var viewHolderArray = arrayListOf<ToWasedaAdapter.ViewHolder>()
    var infoHolderArray = arrayListOf<InfoHolder>()
    var viewHolderCreatedCount = 0

    /*
    *   TODO: 今一番上のカウントダウンが止まったときに全部のタイマーがとまる。それを直す
    * */
    override fun onCreateViewHolder(viewGroup: ViewGroup?, viewType: Int): ViewHolder? {
//        Log.v(TAG, "WASEDA ADAPTER RECEIVING THE CURSOR OF: " + DatabaseUtils.dumpCursorToString(cursor))


        val view:View = LayoutInflater.from(context).inflate(R.layout.layout_item_single, viewGroup, false)
        val viewHolder = ToWasedaAdapter.ViewHolder(view)
        view.setOnClickListener( {
            val position = viewHolder.adapterPosition
            Log.v(TAG, "Item $position clicked.")
        }
        )
//        Log.v(TAG,"Viewholder#$viewHolderCreatedCount")
//        viewHolderCreatedCount++
        //Don't add viewHolder to array yet: Viewholder must have position value
        return viewHolder
    }


    /**
     *  Position 0 → next bus leaving Waseda.
     *
     *  Get the current time, query the SQL bus schedule table and then display from there.
     *
     *  VIEWHOLDER IS RECYCLED FROM viewHolder#7 (position = 6) ITEM
     *
     */
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        //Background image is the same for every view
        Glide.with(context)
                .load(R.drawable.img_okuma)
                .into(viewHolder.image_background)
        Log.v(TAG, "Position is $position")

        cursor?.moveToPosition(position)



        //Already made viewholder
        if (infoHolderArray.size > position && infoHolderArray[position].hour_holder.isNotEmpty()) {
            //values
            val routeOption = infoHolderArray[position].routeOption_holder
            val hourValue = infoHolderArray[position].hour_holder
            val minValue = infoHolderArray[position].min_holder

            viewHolder.bindStaticInfo(position, hourValue, minValue, routeOption)

            viewHolder.bindCountDown(hourValue, minValue, "00")

        }else{  //Newly created viewholder
            //Indices
            val hourIndex = cursor?.getColumnIndex(DataContract.DB_TO_WASEDA().COLUMN_HOUR)
            val minIndex = cursor?.getColumnIndex(DataContract.DB_TO_WASEDA().COLUMN_MIN)
            val routeOptionIndex = cursor?.getColumnIndex(DataContract.DB_TO_WASEDA().COLUMN_FLAG)

            //values
            val routeOption = getRouteOption(cursor?.getInt(routeOptionIndex!!)!!)
            val hourValue = cursor?.getString(hourIndex!!)!!
            val minValue = cursor?.getString(minIndex!!)!!

            //save to data model
            val infoHolder =  InfoHolder(
                    routeOption,
                    timeFormatter(hourValue),
                    timeFormatter(minValue)
            )
            infoHolderArray.add(infoHolder)

            //Display the info
            viewHolder.bindStaticInfo(
                    position,
                    timeFormatter(hourValue),
                    timeFormatter(minValue),
                    routeOption
            )

            countDownStart(viewHolder, position,  getCurrentDateText() + "$hourValue-$minValue-00")
        }
    }

    override fun getItemCount(): Int {
        if (cursor == null) {
            Log.e(TAG, "Cursor is null")
            return 0
        } else {
            return cursor!!.count
        }
    }

    fun getCurrentDateText():String{
        val calendar = Calendar.getInstance()
        val year:String = calendar.get(Calendar.YEAR).toString()
        val month:String = (calendar.get(Calendar.MONTH) +1).toString()
        val date:String = calendar.get(Calendar.DATE).toString()

        return "$year-$month-$date-"
    }

    override fun onViewRecycled(holder: ViewHolder?) {
        super.onViewRecycled(holder)
//        Log.v(TAG, "RECYCLING VIEWHOLDER #" + holder?.layoutPosition)
        //Below code gives error upon onNotifyDatasetChanged() because array would be initialized®
//        holder?.bindCountDown(
//                viewHolderArray[holder.holder_position].hour_holder,
//                viewHolderArray[holder.holder_position].min_holder,
//                "00"
//        )
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
    fun countDownStart(viewHolder:ViewHolder, position:Int, dept_time:String) {
        handler = Handler()
        runnable = object : Runnable {
            override fun run() {
                (handler)?.postDelayed(this, 1000)
                try {
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd-kk-mm-ss", Locale.JAPAN)
                    // Here Set your Event Date
                    val eventDate = dateFormat.parse(dept_time)
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

                        viewHolder.bindCountDown(hourText, minText, secText)

                    } else {
                        handler?.removeCallbacks(runnable)
                        handler?.removeMessages(0)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        handler?.postDelayed(runnable, 0)
    }

    private fun timeFormatter(num:String):String{
        var str = ""
        if (num.toInt() < 10) {
            str = "0" + num
        }else{
            str = num
        }

        return str
    }

    fun swapCursor(cursor:Cursor?){
        this.cursor = cursor
//        Log.v(TAG, "NEW CURSOR: " + DatabaseUtils.dumpCursorToString(cursor))
        viewHolderArray.clear()
        notifyDataSetChanged()
    }

    data class InfoHolder (
            var routeOption_holder:String,
            var hour_holder:String,
            var min_holder:String
    )


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //property access cannot be used because of Glide library limitation
        val image_background = view.findViewById(R.id.item_image) as ImageView

        //and PlaceHolders for saving data for scrolling
        var routeOption_holder:String = ""
        var hour_holder:String = ""
        var min_holder:String = ""

        fun bindStaticInfo(position:Int, hour:String?, min:String?, routeOption:String?){
            //save in placeholder
            routeOption_holder = routeOption as String
            hour_holder = hour as String
            min_holder = min as String
//            holder_position = position
            itemView.hint_route_text.text = routeOption
            itemView.departure_time.text = hour + min
        }

        fun bindCountDown(hour:String?, min:String?, sec:String?){
            itemView.item_hour.text = hour
            itemView.item_min.text = min
            itemView.item_sec.text = sec
        }
    }
}