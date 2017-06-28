package etchee.com.wasedabusschedule.Fragments

import android.content.Context
import android.os.CountDownTimer
import android.os.Handler
import android.provider.Settings
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import etchee.com.wasedabusschedule.Data.DataContract
import etchee.com.wasedabusschedule.Data.DataList
import etchee.com.wasedabusschedule.R
import kotlinx.android.synthetic.main.layout_item_single.view.*
import java.util.*
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList
import etchee.com.wasedabusschedule.Data.DataContract.GlobalConstants


/**
 * Adapter for the RecyclerView
 * Created by rikutoechigoya on 2017/05/23.
 */
class MyAdapter(val context: Context, val toWasedaFragment: ToWasedaFragment?, val toNishiFragment: ToNishiFragment?,
                var arrayList: ArrayList<DataList.DataModel>, val ADAPTER_MODE:Int) :
        RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private var TAG: String = javaClass.simpleName
    var infoHolderArray = arrayListOf<InfoHolder>()
    private var mArrayList = arrayList

    override fun onCreateViewHolder(viewGroup: ViewGroup?, viewType: Int): ViewHolder? {

        val view:View = LayoutInflater.from(context).inflate(R.layout.layout_item_single, viewGroup, false)
        val viewHolder = MyAdapter.ViewHolder(view)
        view.setOnClickListener( {
            val position = viewHolder.adapterPosition
            Log.v(TAG, "Item $position clicked.")
            removeItemAt(viewHolder.adapterPosition)
        }
        )
        return viewHolder
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        //BACKGROUND
        if (ADAPTER_MODE == GlobalConstants().MODE_WASEDA){
            Glide.with(context)
                    .load(R.drawable.img_okuma)
                    .into(viewHolder.image_background)
        }else{
            Glide.with(context)
                    .load(R.drawable.nishi_improved)
                    .into(viewHolder.image_background)
        }


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
        }

        else if(isPastLastBus()){
            if (ADAPTER_MODE == GlobalConstants().MODE_WASEDA){
                toWasedaFragment?.setEmptyMode()
                return 0
            }else{
                toNishiFragment?.setEmptyMode()
                return 0
            }
        }

        else {
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


    fun isPastLastBus():Boolean{
        val time = getIntTime()
        val hour = time.hour
        val waseda_week_hour_key = 1825
        val waseda_saturday_hour_key = 1620
        val nishi_week_hour_key = 1810
        val nishi_sat_hour_key = 1635
        val min = time.min
        val day = time.day

        val key = (hour.toString() + min.toString()).toInt()
        /**
         *  Weekday: ToWaseda... 18:25
         *  Saturday: To Waseda... 16:20
         */

        when (day) {
            1 -> {
                return true
            }

            7 -> {    //土曜
                if (ADAPTER_MODE == GlobalConstants().MODE_WASEDA) return key >= waseda_saturday_hour_key
                else return key>= nishi_sat_hour_key

            }

            else -> { //Weekday
                if (ADAPTER_MODE == GlobalConstants().MODE_WASEDA) return key >= waseda_week_hour_key
                else return key>= nishi_week_hour_key
            }
        }

    }

    fun refreshDataSet(newArrayList:ArrayList<DataList.DataModel>){
        mArrayList.clear()
        mArrayList = newArrayList
        if (mArrayList.count()!=newArrayList.count()){
            notifyItemRangeInserted(0, newArrayList.count())
        }else{
            notifyDataSetChanged()
        }
    }

    fun removeItemAt(position: Int){
        mArrayList.removeAt(position)
        infoHolderArray.removeAt(position)
        this.notifyItemRemoved(position)
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
                    itemView.semiColon.visibility = View.GONE
                    itemView.semiColon2.visibility = View.GONE
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