package etchee.com.wasedabusschedule.Data

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.net.Uri
import android.os.AsyncTask
import android.widget.Toast

/**
 * Table init utility
 * Created by rikutoechigoya on 2017/05/29.
 */
class AsyncInitTables(val context:Context): AsyncTask<Void, Int, Boolean>() {

    val contentResolver: ContentResolver = context.contentResolver

    override fun doInBackground(vararg params: Void?): Boolean {
        //if checking returns false
        if (!checkDB()) {
            //Initialize the database
            initWasedaTable()
            initNishiTable()
            //signal to UI that data is not there
            return false
        } else return true  // otherwise signal to UI that data is there
    }

    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)
        when(result){
            false -> Toast.makeText(context, "時刻表最適化完了！", Toast.LENGTH_SHORT).show()
            true -> Toast.makeText(context, "時刻表あります", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * No problem -> true
     * No table -> false
     */
    private fun checkDB(): Boolean {
        var cursor: Cursor? = null
        var errorFlag = true
        try {
            //query the Waseda Table
            cursor = contentResolver.query(
                    DataContract.DB_TO_WASEDA().CONTENT_URI,
                    null,
                    null,
                    null,
                    null
            )
            if (!cursor.moveToFirst()) {
                errorFlag = false
            }

            //query the Waseda Table
            cursor = contentResolver.query(
                    DataContract.DB_TO_NISHI().CONTENT_URI,
                    null,
                    null,
                    null,
                    null
            )

            if (!cursor.moveToFirst()) {
                errorFlag = false
            }

        } catch(e: Exception) {
            e.printStackTrace()
        } finally {
            cursor?.close()

        }

        return errorFlag
    }


    private fun initWasedaTable(): Boolean {
        //for URI shortcut
        val data: DataContract.DB_TO_WASEDA = DataContract.DB_TO_WASEDA()
        //dump all the values in this
        val values: ContentValues = ContentValues()

        var count:Int = 0   //Counter to use in loop
        var flag:Boolean = true     //If there is an error make this false
        var uri: Uri? = null    //For error checking

        try{
            while(count < 23) {
                when(count) {
                    0 -> {
                        values.put(data.COLUMN_HOUR, 9)
                        values.put(data.COLUMN_MIN, 20)
                        values.put(data.COLUMN_FLAG, 0)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    1 -> {
                        values.put(data.COLUMN_HOUR, 9)
                        values.put(data.COLUMN_MIN, 35)
                        values.put(data.COLUMN_FLAG, 0)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    2 -> {
                        values.put(data.COLUMN_HOUR, 9)
                        values.put(data.COLUMN_MIN, 50)
                        values.put(data.COLUMN_FLAG, 0)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    3 -> {
                        values.put(data.COLUMN_HOUR, 10)
                        values.put(data.COLUMN_MIN, 5)
                        values.put(data.COLUMN_FLAG, 0)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    4 -> {
                        values.put(data.COLUMN_HOUR, 10)
                        values.put(data.COLUMN_MIN, 35)
                        values.put(data.COLUMN_FLAG, 0)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    5 -> {
                        values.put(data.COLUMN_HOUR, 10)
                        values.put(data.COLUMN_MIN, 45)
                        values.put(data.COLUMN_FLAG, 0)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    6 -> {
                        values.put(data.COLUMN_HOUR, 11)
                        values.put(data.COLUMN_MIN, 15)
                        values.put(data.COLUMN_FLAG, 0)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    7 -> {
                        values.put(data.COLUMN_HOUR, 11)
                        values.put(data.COLUMN_MIN, 45)
                        values.put(data.COLUMN_FLAG, 0)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    8 -> {
                        values.put(data.COLUMN_HOUR, 12)
                        values.put(data.COLUMN_MIN, 5)
                        values.put(data.COLUMN_FLAG, 1)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    9 -> {
                        values.put(data.COLUMN_HOUR, 12)
                        values.put(data.COLUMN_MIN, 15)
                        values.put(data.COLUMN_FLAG, 2)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    10 -> {
                        values.put(data.COLUMN_HOUR, 12)
                        values.put(data.COLUMN_MIN, 45)
                        values.put(data.COLUMN_FLAG, 0)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    11 -> {
                        values.put(data.COLUMN_HOUR, 13)
                        values.put(data.COLUMN_MIN, 15)
                        values.put(data.COLUMN_FLAG, 0)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    12 -> {
                        values.put(data.COLUMN_HOUR, 13)
                        values.put(data.COLUMN_MIN, 45)
                        values.put(data.COLUMN_FLAG, 0)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    13 -> {
                        values.put(data.COLUMN_HOUR, 14)
                        values.put(data.COLUMN_MIN, 5)
                        values.put(data.COLUMN_FLAG, 0)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    14 -> {
                        values.put(data.COLUMN_HOUR, 14)
                        values.put(data.COLUMN_MIN, 35)
                        values.put(data.COLUMN_FLAG, 0)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    15 -> {
                        values.put(data.COLUMN_HOUR, 14)
                        values.put(data.COLUMN_MIN, 50)
                        values.put(data.COLUMN_FLAG, 3)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    16 -> {
                        values.put(data.COLUMN_HOUR, 15)
                        values.put(data.COLUMN_MIN, 5)
                        values.put(data.COLUMN_FLAG, 0)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    17 -> {
                        values.put(data.COLUMN_HOUR, 15)
                        values.put(data.COLUMN_MIN, 20)
                        values.put(data.COLUMN_FLAG, 0)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    18 -> {
                        values.put(data.COLUMN_HOUR, 15)
                        values.put(data.COLUMN_MIN, 35)
                        values.put(data.COLUMN_FLAG, 0)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    19 -> {
                        values.put(data.COLUMN_HOUR, 15)
                        values.put(data.COLUMN_MIN, 50)
                        values.put(data.COLUMN_FLAG, 0)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    20 -> {
                        values.put(data.COLUMN_HOUR, 16)
                        values.put(data.COLUMN_MIN, 20)
                        values.put(data.COLUMN_FLAG, 0)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    21 -> {
                        values.put(data.COLUMN_HOUR, 16)
                        values.put(data.COLUMN_MIN, 35)
                        values.put(data.COLUMN_FLAG, 3)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    22 -> {
                        values.put(data.COLUMN_HOUR, 18)
                        values.put(data.COLUMN_MIN, 10)
                        values.put(data.COLUMN_FLAG, 3)
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

    private fun initNishiTable(){
        val data: DataContract.DB_TO_NISHI = DataContract.DB_TO_NISHI()
        //dump all the values in here
        val values: ContentValues = ContentValues()
        //Counter to use in loop
        var count:Int = 0
        var flag:Boolean = true
        var uri:Uri? = null

        try{
            while(count < 24) {
                when(count){
                    0->{
                        values.put(data.COLUMN_HOUR, 9)
                        values.put(data.COLUMN_MIN, 20)
                        values.put(data.COLUMN_FLAG, 0)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    1->{
                        values.put(data.COLUMN_HOUR, 9)
                        values.put(data.COLUMN_MIN, 35)
                        values.put(data.COLUMN_FLAG, 0)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    2->{
                        values.put(data.COLUMN_HOUR, 9)
                        values.put(data.COLUMN_MIN, 50)
                        values.put(data.COLUMN_FLAG, 0)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    3->{
                        values.put(data.COLUMN_HOUR, 10)
                        values.put(data.COLUMN_MIN, 5)
                        values.put(data.COLUMN_FLAG, 0)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    4->{
                        values.put(data.COLUMN_HOUR, 10)
                        values.put(data.COLUMN_MIN, 35)
                        values.put(data.COLUMN_FLAG, 0)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    5->{
                        values.put(data.COLUMN_HOUR, 10)
                        values.put(data.COLUMN_MIN, 45)
                        values.put(data.COLUMN_FLAG, 0)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    6->{
                        values.put(data.COLUMN_HOUR, 11)
                        values.put(data.COLUMN_MIN, 0)
                        values.put(data.COLUMN_FLAG, 0)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    7->{
                        values.put(data.COLUMN_HOUR, 11)
                        values.put(data.COLUMN_MIN, 30)
                        values.put(data.COLUMN_FLAG, 0)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }
                    8->{
                        values.put(data.COLUMN_HOUR, 12)
                        values.put(data.COLUMN_MIN, 5)
                        values.put(data.COLUMN_FLAG, 4)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    9->{
                        values.put(data.COLUMN_HOUR, 12)
                        values.put(data.COLUMN_MIN, 20)
                        values.put(data.COLUMN_FLAG, 5)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    10->{
                        values.put(data.COLUMN_HOUR, 12)
                        values.put(data.COLUMN_MIN, 45)
                        values.put(data.COLUMN_FLAG, 0)
                        uri = contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    11->{
                        values.put(data.COLUMN_HOUR, 13)
                        values.put(data.COLUMN_MIN, 0)
                        values.put(data.COLUMN_FLAG, 0)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    12->{
                        values.put(data.COLUMN_HOUR, 13)
                        values.put(data.COLUMN_MIN, 30)
                        values.put(data.COLUMN_FLAG, 0)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    13->{
                        values.put(data.COLUMN_HOUR, 14)
                        values.put(data.COLUMN_MIN, 5)
                        values.put(data.COLUMN_FLAG, 0)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    14->{
                        values.put(data.COLUMN_HOUR, 14)
                        values.put(data.COLUMN_MIN, 35)
                        values.put(data.COLUMN_FLAG, 6)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    15->{
                        values.put(data.COLUMN_HOUR, 14)
                        values.put(data.COLUMN_MIN, 50)
                        values.put(data.COLUMN_FLAG, 0)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    16->{
                        values.put(data.COLUMN_HOUR, 15)
                        values.put(data.COLUMN_MIN, 5)
                        values.put(data.COLUMN_FLAG, 0)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    17->{
                        values.put(data.COLUMN_HOUR, 15)
                        values.put(data.COLUMN_MIN, 20)
                        values.put(data.COLUMN_FLAG, 0)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    18->{
                        values.put(data.COLUMN_HOUR, 15)
                        values.put(data.COLUMN_MIN, 35)
                        values.put(data.COLUMN_FLAG, 0)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    19->{
                        values.put(data.COLUMN_HOUR, 15)
                        values.put(data.COLUMN_MIN, 50)
                        values.put(data.COLUMN_FLAG, 0)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    20->{
                        values.put(data.COLUMN_HOUR, 16)
                        values.put(data.COLUMN_MIN, 20)
                        values.put(data.COLUMN_FLAG, 6)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    21->{
                        values.put(data.COLUMN_HOUR, 16)
                        values.put(data.COLUMN_MIN, 35)
                        values.put(data.COLUMN_FLAG, 0)
                        uri =   contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    22->{
                        values.put(data.COLUMN_HOUR, 18)
                        values.put(data.COLUMN_MIN, 10)
                        values.put(data.COLUMN_FLAG, 0)
                        uri =  contentResolver.insert(
                                data.CONTENT_URI,
                                values
                        ); values.clear(); count++
                    }

                    23->{
                        values.put(data.COLUMN_HOUR, 18)
                        values.put(data.COLUMN_MIN, 25)
                        values.put(data.COLUMN_FLAG, 8)
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
}