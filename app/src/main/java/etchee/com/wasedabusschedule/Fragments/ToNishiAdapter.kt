package etchee.com.wasedabusschedule.Fragments

import android.content.Context
import android.database.Cursor
import android.database.DatabaseUtils
import android.os.CountDownTimer
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
import etchee.com.wasedabusschedule.Data.DataList
import etchee.com.wasedabusschedule.R
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.android.synthetic.main.layout_item_single.view.*

/**
 * RecyclerView Adapter for the ToNishi fragment
 * Created by rikutoechigoya on 2017/05/24.
 */
class ToNishiAdapter(val context: Context) :
        android.support.v7.widget.RecyclerView.Adapter<ToNishiAdapter.ViewHolder>() {

    private var TAG: String = javaClass.simpleName
    var infoHolderArray = arrayListOf<InfoHolder>()
    private val mArrayList = createArrayList()

    override fun onCreateViewHolder(viewGroup: ViewGroup?, viewType: Int): ViewHolder? {

        val view:View = LayoutInflater.from(context).inflate(R.layout.layout_item_single, viewGroup, false)
        val viewHolder = ToNishiAdapter.ViewHolder(view)
        view.setOnClickListener( {
            val position = viewHolder.adapterPosition
            Log.v(TAG, "Item $position clicked.")
        }
        )
        return viewHolder
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        //BACKGROUND
        Glide.with(context)
                .load(R.drawable.nishi_improved)
                .into(viewHolder.image_background)

        //Already made viewHolder
        if (infoHolderArray.size > position && infoHolderArray[position].hour_holder.isNotEmpty()) {
            //GET
            val routeOption = infoHolderArray[position].routeOption_holder
            val hourValue = infoHolderArray[position].hour_holder
            val minValue = infoHolderArray[position].min_holder
            //BIND
            viewHolder.bindStaticInfo(position, hourValue, minValue, routeOption)
            //RESET TIMER
            viewHolder.startTimer()

        }else{
            //NEW viewHolder

            //GET
            val hourValue = mArrayList[position].hour
            val minValue = mArrayList[position].min
            val routeOption = getRouteOption(mArrayList[position].flag)

            //SAVE
            val infoHolder =  InfoHolder(
                    routeOption,
                    hourValue,
                    minValue,
                    "NA",
                    "NA",
                    "NA",
                    "NA"
            )
            infoHolderArray.add(infoHolder)

            //BIND
            viewHolder.bindStaticInfo(
                    position,
                    hourValue,
                    minValue,
                    routeOption
            )
            //START TIMER
            viewHolder.startTimer()
        }
    }

    override fun getItemCount(): Int {
        if (mArrayList.size == 0) {
            Log.e(TAG, "ArrayList is null")
            return 0
        } else {
            return mArrayList.size
        }
    }

    fun getIntTime():mIntTime{
        val calendar = Calendar.getInstance()
        val mIntTime = mIntTime(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_WEEK),
                calendar.get(Calendar.DATE),
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND)
        )

        return mIntTime
    }

    fun getStringTime():mStringTime{
        val calendar = Calendar.getInstance()
        val mStringTime = mStringTime(
                calendar.get(Calendar.YEAR).toString(),
                (calendar.get(Calendar.MONTH) + 1).toString(),
                calendar.get(Calendar.DAY_OF_WEEK).toString(),
                calendar.get(Calendar.DATE),
                calendar.get(Calendar.HOUR_OF_DAY).toString(),
                calendar.get(Calendar.MINUTE).toString(),
                calendar.get(Calendar.SECOND).toString()
        )

        return mStringTime
    }


    data class mIntTime(val year:Int,
                        val month:Int,
                        val day:Int,
                        val date:Int,
                        val hour:Int,
                        val min:Int,
                        val sec:Int)

    data class mStringTime(val year:String,
                           val month:String,
                           val day:String,
                           val date:Int,
                           val hour:String,
                           val min:String,
                           val sec:String)



    private fun getDayString(day:Int):String {
        when (day) {
            1 -> return "Sunday"
            2 -> return "Monday"
            3 -> return "Tuesday"
            4 -> return "Wednesday"
            5 -> return "Thursday"
            6 -> return "Friday"
            7 -> return "Saturday"

            else-> throw IllegalArgumentException(TAG + "Calendar did not recognize day.")
        }
    }

    fun getCurrentDateText():String{
        val calendar = Calendar.getInstance()
        val year:String = calendar.get(Calendar.YEAR).toString()
        val month:String = (calendar.get(Calendar.MONTH) +1).toString()
        val date:String = calendar.get(Calendar.DATE).toString()

        return "$year-$month-$date-"
    }

    /**
     *  gets time, then create mArrayList of appropriate size.
     */
    private fun createArrayList():ArrayList<DataList.DataModel>{
        //Inside the mArrayList is like
        //DataModel("15", "35", 0, 1535)
        //get the current time in 1440 format, iterate until now > key
        val time = getStringTime()
        val hour = time.hour
        val min = time.min
        val key = Integer.parseInt(hour + min)
        val day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        val list = arrayListOf<DataList.DataModel>()
        var count = -1

        when(day){
        //Sunday
            1-> return list //No bus on Sunday
            7->{
                val model: java.util.ArrayList<DataList.DataModel> = DataList().createSat_NishiData()
                mArrayList?.clear()
                for (i in model){
                    if (i.search > key){
                        list.add(i)
                        count++
                    }
                }

                return list
            }
            else->{//weekday
                val model: java.util.ArrayList<DataList.DataModel> = DataList().createNishiData()
                mArrayList?.clear()
                for (i in model){
                    if (i.search > key){
                        list.add(i)
                        count++
                    }
                }

                return list
            }
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

    private fun timeFormatter(num:String):String{
        var str = ""
        if (num.toInt() < 10) {
            str = "0" + num
        }else{
            str = num
        }

        return str
    }

    data class InfoHolder (
            var routeOption_holder:String,
            var hour_holder:String,
            var min_holder:String,
            var sec_holder:String,
            var hour_remaining:String,
            var min_remaining:String,
            var sec_remaining:String
    )


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //property access cannot be used because of Glide library limitation
        val image_background = view.findViewById(R.id.item_image) as ImageView

        //and PlaceHolders for saving data for scrolling
        var routeOption_holder:String = ""
        var hour_holder:String = ""
        var min_holder:String = ""
        var runnable:Runnable? = null
        var handler: Handler? = null
        var mTimer:CountDownTimer? = null
        val dateFormat = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.JAPAN)

        fun bindStaticInfo(position:Int, hour:String?, min:String?, routeOption:String?){
            //save in placeholder
            routeOption_holder = routeOption as String
            hour_holder = hour as String
            min_holder = min as String
//            holder_position = position
            itemView.hint_route_text.text = routeOption
            itemView.departure_time.text = hour + ":" +  min
        }

        fun bindCountDown(hour:String?, min:String?, sec:String?){
            itemView.item_hour.text = hour
            itemView.item_min.text = min
            itemView.item_sec.text = sec
        }

        fun startTimer() {
            //TIMER ALREADY RUNNING
            mTimer?.cancel()
            val dep_time_obj: Date = dateFormat.parse(getCurrentDateText() + "$hour_holder-$min_holder-00")
            mTimer = mTimer(dep_time_obj).start()
        }

        private fun mTimer(dep_obj:Date): CountDownTimer {
            val hourFormat = SimpleDateFormat("HH", Locale.JAPAN)
            val minFormat = SimpleDateFormat("mm", Locale.JAPAN)
            val secFormat = SimpleDateFormat("ss", Locale.JAPAN)

            val remaining = dep_obj.time - Date().time

            val cdt = object : CountDownTimer(remaining, 1000) {  //throw in the bus schedule param here
                override fun onTick(timerRemaining: Long) {
                    val hours = (timerRemaining / (1000*60*60)) % 24
                    val hourText = countFormatter(hours)
                    val minutes =  (timerRemaining / (1000*60)) % 60
                    val minText = countFormatter(minutes)
                    val seconds = (timerRemaining / 1000) % 60
                    val secText = countFormatter(seconds)

//                    Log.v("MTIMER", "Second is: $secText")

                    bindCountDown(hourText, minText, secText)
                }

                override fun onFinish() {
                    bindCountDown("GONE", "", "")
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

        fun getCurrentDateText():String{
            val calendar = Calendar.getInstance()
            val year:String = calendar.get(Calendar.YEAR).toString()
            val month:String = (calendar.get(Calendar.MONTH) +1).toString()
            val date:String = calendar.get(Calendar.DATE).toString()

            return "$year-$month-$date-"
        }
    }
}