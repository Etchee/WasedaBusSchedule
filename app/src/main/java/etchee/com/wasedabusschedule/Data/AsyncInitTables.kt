package etchee.com.wasedabusschedule.Data

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.net.Uri
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast


/**
 * Table init utility
 * Created by rikutoechigoya on 2017/05/29.
 */
class AsyncInitTables(val context:Context): AsyncTask<Void, Int, Boolean>() {

    val contentResolver: ContentResolver = context.applicationContext.contentResolver
    val TAG = javaClass.simpleName

    override fun onPreExecute() {
        super.onPreExecute()
        Toast.makeText(context, "データ登録中・・・" , Toast.LENGTH_SHORT).show()
    }

    override fun doInBackground(vararg params: Void?): Boolean {
        initWasedaTable()
//        initNishiTable()
//        initSat_NishiTable()
//        initSat_WasedaTable()

        return true
    }
    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)
        Log.v(TAG, "DATABASE HAS BEEN CREATED.")
    }

    /**
     * delete, then add.
     */
    private fun initWasedaTable(): Boolean {
        //for URI shortcut
        val data: DataContract.DB_TO_WASEDA = DataContract.DB_TO_WASEDA()
        //dump all the values in this
        val values: ContentValues = ContentValues()

        var count:Int = 0   //Counter to use in loop
        var flag:Boolean = true     //If there is an error make this false
        var uri: Uri? = null    //For error checking

        try{

            contentResolver.delete(data.CONTENT_URI, null, null)

            while(count < 27) {
                when(count) {
                    0 -> {
                        values.put(data.COLUMN_HOUR, "09")
                        values.put(data.COLUMN_MIN, "20")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 920)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    1 -> {
                        values.put(data.COLUMN_HOUR, "09")
                        values.put(data.COLUMN_MIN, "35")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 935)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    2 -> {
                        values.put(data.COLUMN_HOUR, "09")
                        values.put(data.COLUMN_MIN, "50")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 950)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    3 -> {
                        values.put(data.COLUMN_HOUR, "10")
                        values.put(data.COLUMN_MIN, "05")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1005)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    4 -> {
                        values.put(data.COLUMN_HOUR, "10")
                        values.put(data.COLUMN_MIN, "35")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1035)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    5 -> {
                        values.put(data.COLUMN_HOUR, "10")
                        values.put(data.COLUMN_MIN, "45")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1045)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    6 -> {
                        values.put(data.COLUMN_HOUR, "11")
                        values.put(data.COLUMN_MIN, "00")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1100)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    7 -> {
                        values.put(data.COLUMN_HOUR, "11")
                        values.put(data.COLUMN_MIN, "30")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1130)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    8 -> {
                        values.put(data.COLUMN_HOUR, "12")
                        values.put(data.COLUMN_MIN, "05")
                        values.put(data.COLUMN_FLAG, 1)
                        values.put(data.COLUMN_SEARCH, 1205)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    9 -> {
                        values.put(data.COLUMN_HOUR, "12")
                        values.put(data.COLUMN_MIN, "20")
                        values.put(data.COLUMN_FLAG, 2)
                        values.put(data.COLUMN_SEARCH, 1215)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    10 -> {
                        values.put(data.COLUMN_HOUR, "12")
                        values.put(data.COLUMN_MIN, "45")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1245)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    11 -> {
                        values.put(data.COLUMN_HOUR, "13")
                        values.put(data.COLUMN_MIN, "15")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1315)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    12 -> {
                        values.put(data.COLUMN_HOUR, "13")
                        values.put(data.COLUMN_MIN, "00")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1300)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    13 -> {
                        values.put(data.COLUMN_HOUR, "13")
                        values.put(data.COLUMN_MIN, "30")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1330)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    14 -> {
                        values.put(data.COLUMN_HOUR, "14")
                        values.put(data.COLUMN_MIN, "35")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1435)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    15 -> {
                        values.put(data.COLUMN_HOUR, "14")
                        values.put(data.COLUMN_MIN, "35")
                        values.put(data.COLUMN_FLAG, 3)
                        values.put(data.COLUMN_SEARCH, 1435)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    16 -> {
                        values.put(data.COLUMN_HOUR, "14")
                        values.put(data.COLUMN_MIN, "50")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1450)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    17 -> {
                        values.put(data.COLUMN_HOUR, "15")
                        values.put(data.COLUMN_MIN, "05")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1505)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    18 -> {
                        values.put(data.COLUMN_HOUR, "15")
                        values.put(data.COLUMN_MIN, "20")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1520)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    19 -> {
                        values.put(data.COLUMN_HOUR, "15")
                        values.put(data.COLUMN_MIN, "35")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1535)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    20 -> {
                        values.put(data.COLUMN_HOUR, "15")
                        values.put(data.COLUMN_MIN, "50")
                        values.put(data.COLUMN_FLAG, 6)
                        values.put(data.COLUMN_SEARCH, 1550)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    21 -> {
                        values.put(data.COLUMN_HOUR, "16")
                        values.put(data.COLUMN_MIN, "20")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1620)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    22 -> {
                        values.put(data.COLUMN_HOUR, "16")
                        values.put(data.COLUMN_MIN, "35")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1635)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    23 -> {
                        values.put(data.COLUMN_HOUR, "17")
                        values.put(data.COLUMN_MIN, "00")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1700)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    24 -> {
                        values.put(data.COLUMN_HOUR, "17")
                        values.put(data.COLUMN_MIN, "20")
                        values.put(data.COLUMN_FLAG, 8)
                        values.put(data.COLUMN_SEARCH, 1720)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    25 -> {
                        values.put(data.COLUMN_HOUR, "18")
                        values.put(data.COLUMN_MIN, "10")
                        values.put(data.COLUMN_FLAG, 8)
                        values.put(data.COLUMN_SEARCH, 1810)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    26 -> {
                        values.put(data.COLUMN_HOUR, "18")
                        values.put(data.COLUMN_MIN, "25")
                        values.put(data.COLUMN_FLAG, 8)
                        values.put(data.COLUMN_SEARCH, 1825)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }
                }
            }

        }catch (e: SQLException){
            e.printStackTrace()
        }finally {
            if (uri == null) {
                flag = false
            }
        }

        return flag
    }

    private fun initSat_WasedaTable(){
        val data: DataContract.SATURDAY_DB_TO_WASEDA = DataContract.SATURDAY_DB_TO_WASEDA()
        //dump all the values in here
        val values: ContentValues = ContentValues()
        //Counter to use in loop
        var count:Int = 0
        var uri:Uri? = null

        try{

            contentResolver.delete(data.CONTENT_URI, null, null)
            while(count < 11) {
                when(count) {
                    0 -> {
                        values.put(data.COLUMN_HOUR, "09")
                        values.put(data.COLUMN_MIN, "20")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 920)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    1 -> {
                        values.put(data.COLUMN_HOUR, "10")
                        values.put(data.COLUMN_MIN, "20")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1020)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    2 -> {
                        values.put(data.COLUMN_HOUR, "10")
                        values.put(data.COLUMN_MIN, "50")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1050)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    3 -> {
                        values.put(data.COLUMN_HOUR, "11")
                        values.put(data.COLUMN_MIN, "30")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1130)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    4 -> {
                        values.put(data.COLUMN_HOUR, "12")
                        values.put(data.COLUMN_MIN, "00")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1200)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    5 -> {
                        values.put(data.COLUMN_HOUR, "12")
                        values.put(data.COLUMN_MIN, "30")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1230)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    6 -> {
                        values.put(data.COLUMN_HOUR, "13")
                        values.put(data.COLUMN_MIN, "45")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1345)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    7 -> {
                        values.put(data.COLUMN_HOUR, "14")
                        values.put(data.COLUMN_MIN, "15")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1415)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }
                    8 -> {
                        values.put(data.COLUMN_HOUR, "14")
                        values.put(data.COLUMN_MIN, "50")
                        values.put(data.COLUMN_FLAG, 4)
                        values.put(data.COLUMN_SEARCH, 1450)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    9 -> {
                        values.put(data.COLUMN_HOUR, "15")
                        values.put(data.COLUMN_MIN, "50")
                        values.put(data.COLUMN_FLAG, 5)
                        values.put(data.COLUMN_SEARCH, 1250)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    10 -> {
                        values.put(data.COLUMN_HOUR, "16")
                        values.put(data.COLUMN_MIN, "35")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1635)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                }
            }

        }catch (e: SQLException){
            e.printStackTrace()
        }finally {
            if (uri == null) {
                throw SQLException("The last insertion has failed")
            }
        }
    }
    private fun initNishiTable(){
        val data: DataContract.DB_TO_NISHI = DataContract.DB_TO_NISHI()
        //dump all the values in here
        val values: ContentValues = ContentValues()
        //Counter to use in loop
        var count:Int = 0
        var flag:Boolean = true
        var uri:Uri? = null

        try{

            contentResolver.delete(data.CONTENT_URI, null, null)
            while(count < 24) {
                when(count){
                    0->{
                        values.put(data.COLUMN_HOUR, "09")
                        values.put(data.COLUMN_MIN, "20")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 920)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    1->{
                        values.put(data.COLUMN_HOUR, "09")
                        values.put(data.COLUMN_MIN, "35")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 935)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    2->{
                        values.put(data.COLUMN_HOUR, "9")
                        values.put(data.COLUMN_MIN, "50")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 950)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    3->{
                        values.put(data.COLUMN_HOUR, "10")
                        values.put(data.COLUMN_MIN, "05")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1005)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    4->{
                        values.put(data.COLUMN_HOUR, "10")
                        values.put(data.COLUMN_MIN, "35")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1035)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    5->{
                        values.put(data.COLUMN_HOUR, "10")
                        values.put(data.COLUMN_MIN, "45")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1045)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    6->{
                        values.put(data.COLUMN_HOUR, "11")
                        values.put(data.COLUMN_MIN, "00")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1100)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    7->{
                        values.put(data.COLUMN_HOUR, "11")
                        values.put(data.COLUMN_MIN, "30")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1130)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }
                    8->{
                        values.put(data.COLUMN_HOUR, "12")
                        values.put(data.COLUMN_MIN, "05")
                        values.put(data.COLUMN_FLAG, 4)
                        values.put(data.COLUMN_SEARCH, 1205)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    9->{
                        values.put(data.COLUMN_HOUR, "12")
                        values.put(data.COLUMN_MIN, "20")
                        values.put(data.COLUMN_FLAG, 5)
                        values.put(data.COLUMN_SEARCH, 1220)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    10->{
                        values.put(data.COLUMN_HOUR, "12")
                        values.put(data.COLUMN_MIN, "45")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1245)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    11->{
                        values.put(data.COLUMN_HOUR, "13")
                        values.put(data.COLUMN_MIN, "00")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1300)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    12->{
                        values.put(data.COLUMN_HOUR, "13")
                        values.put(data.COLUMN_MIN, "30")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1330)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    13->{
                        values.put(data.COLUMN_HOUR, "14")
                        values.put(data.COLUMN_MIN, "05")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1405)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    14->{
                        values.put(data.COLUMN_HOUR, "14")
                        values.put(data.COLUMN_MIN, "35")
                        values.put(data.COLUMN_FLAG, 6)
                        values.put(data.COLUMN_SEARCH, 1435)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    15->{
                        values.put(data.COLUMN_HOUR, "14")
                        values.put(data.COLUMN_MIN, "50")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1450)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    16->{
                        values.put(data.COLUMN_HOUR, "15")
                        values.put(data.COLUMN_MIN, "05")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1505)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    17->{
                        values.put(data.COLUMN_HOUR, "15")
                        values.put(data.COLUMN_MIN, "20")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1520)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    18->{
                        values.put(data.COLUMN_HOUR, "15")
                        values.put(data.COLUMN_MIN, "35")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1535)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    19->{
                        values.put(data.COLUMN_HOUR, "15")
                        values.put(data.COLUMN_MIN, "50")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1550)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    20->{
                        values.put(data.COLUMN_HOUR, "16")
                        values.put(data.COLUMN_MIN, "20")
                        values.put(data.COLUMN_FLAG, 6)
                        values.put(data.COLUMN_SEARCH, 1620)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    21->{
                        values.put(data.COLUMN_HOUR, "16")
                        values.put(data.COLUMN_MIN, "35")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1635)
                        uri =   contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    22->{
                        values.put(data.COLUMN_HOUR, "18")
                        values.put(data.COLUMN_MIN, "10")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1810)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    23->{
                        values.put(data.COLUMN_HOUR, "18")
                        values.put(data.COLUMN_MIN, "25")
                        values.put(data.COLUMN_FLAG, 8)
                        values.put(data.COLUMN_SEARCH, 1825)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }
                }

            }

        }catch (e: SQLException){
            e.printStackTrace()
        }finally {
            if (uri == null) {
                throw SQLException("The last insertion has failed")
            }
        }
    }

    private fun initSat_NishiTable(){
        val data = DataContract.SATURDAY_DB_TO_NISHI()
        //dump all the values in here
        val values: ContentValues = ContentValues()
        //Counter to use in loop
        var count:Int = 0
        var uri:Uri? = null

        try{

            contentResolver.delete(data.CONTENT_URI, null, null)
            while(count < 10) {
                when(count) {
                    0 -> {
                        values.put(data.COLUMN_HOUR, "09")
                        values.put(data.COLUMN_MIN, "35")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 935)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    1 -> {
                        values.put(data.COLUMN_HOUR, "10")
                        values.put(data.COLUMN_MIN, "35")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1035)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    2 -> {
                        values.put(data.COLUMN_HOUR, "11")
                        values.put(data.COLUMN_MIN, "05")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1105)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    3 -> {
                        values.put(data.COLUMN_HOUR, "11")
                        values.put(data.COLUMN_MIN, "45")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1145)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    4 -> {
                        values.put(data.COLUMN_HOUR, "12")
                        values.put(data.COLUMN_MIN, "15")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1215)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    5 -> {
                        values.put(data.COLUMN_HOUR, "12")
                        values.put(data.COLUMN_MIN, "45")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1245)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    6 -> {
                        values.put(data.COLUMN_HOUR, "14")
                        values.put(data.COLUMN_MIN, "00")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1400)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    7 -> {
                        values.put(data.COLUMN_HOUR, "14")
                        values.put(data.COLUMN_MIN, "35")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1435)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }
                    8 -> {
                        values.put(data.COLUMN_HOUR, "15")
                        values.put(data.COLUMN_MIN, "20")
                        values.put(data.COLUMN_FLAG, 4)
                        values.put(data.COLUMN_SEARCH, 1520)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    9 -> {
                        values.put(data.COLUMN_HOUR, "16")
                        values.put(data.COLUMN_MIN, "20")
                        values.put(data.COLUMN_FLAG, 5)
                        values.put(data.COLUMN_SEARCH, 1620)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }
                }
            }

        }catch (e: SQLException){
            e.printStackTrace()
        }finally {
            if (uri == null) {
                throw SQLException("The last insertion has failed")
            }
        }
    }
}