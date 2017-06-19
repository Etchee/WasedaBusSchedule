package etchee.com.wasedabusschedule.Data

import android.content.ContentValues
import android.content.Context
import android.database.SQLException
import android.net.Uri
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast

/**
 * data util class to create the DB serially
 * Created by rikutoechigoya on 2017/06/17.
 */
class InitTableSerial(val context: Context): AsyncTask<Void, Void, Void>() {

    val TAG = javaClass.simpleName

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)
        Toast.makeText(context, "DB Finished", Toast.LENGTH_SHORT).show()
    }

    override fun doInBackground(vararg params: Void?): Void? {
        val db = DataDbHelper(context,
                DataContract.GlobalConstants().DATABASE_NAME,
                null,
                1).writableDatabase

        //for URI shortcut
        val data = DataContract.DB_TO_WASEDA()
        val values: ContentValues = ContentValues()
        var count: Int = 0   //Counter to use in loop

        db.beginTransaction()
        try {
            db.delete(data.TABLE_NAME, null, null)

            while (count < 26) {
                when (count) {
                    0 -> {
                        values.put(data.COLUMN_HOUR, "09")
                        values.put(data.COLUMN_MIN, "20")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 920)
                        db.insert(
                                data.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    1 -> {
                        values.put(data.COLUMN_HOUR, "09")
                        values.put(data.COLUMN_MIN, "35")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 935)
                        db.insert(
                                data.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    2 -> {
                        values.put(data.COLUMN_HOUR, "09")
                        values.put(data.COLUMN_MIN, "50")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 950)
                        db.insert(
                                data.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    3 -> {
                        values.put(data.COLUMN_HOUR, "10")
                        values.put(data.COLUMN_MIN, "05")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1005)
                        db.insert(
                                data.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    4 -> {
                        values.put(data.COLUMN_HOUR, "10")
                        values.put(data.COLUMN_MIN, "35")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1035)
                        db.insert(
                                data.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    5 -> {
                        values.put(data.COLUMN_HOUR, "10")
                        values.put(data.COLUMN_MIN, "45")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1045)
                        db.insert(
                                data.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    6 -> {
                        values.put(data.COLUMN_HOUR, "11")
                        values.put(data.COLUMN_MIN, "00")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1100)
                        db.insert(
                                data.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    7 -> {
                        values.put(data.COLUMN_HOUR, "11")
                        values.put(data.COLUMN_MIN, "30")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1130)
                        db.insert(
                                data.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    8 -> {
                        values.put(data.COLUMN_HOUR, "12")
                        values.put(data.COLUMN_MIN, "05")
                        values.put(data.COLUMN_FLAG, 4)
                        values.put(data.COLUMN_SEARCH, 1205)
                        db.insert(
                                data.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    9 -> {
                        values.put(data.COLUMN_HOUR, "12")
                        values.put(data.COLUMN_MIN, "20")
                        values.put(data.COLUMN_FLAG, 5)
                        values.put(data.COLUMN_SEARCH, 1215)
                        db.insert(
                                data.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    10 -> {
                        values.put(data.COLUMN_HOUR, "12")
                        values.put(data.COLUMN_MIN, "45")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1245)
                        db.insert(
                                data.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    11 -> {
                        values.put(data.COLUMN_HOUR, "13")
                        values.put(data.COLUMN_MIN, "00")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1300)
                        db.insert(
                                data.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    12 -> {
                        values.put(data.COLUMN_HOUR, "13")
                        values.put(data.COLUMN_MIN, "30")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1330)
                        db.insert(
                                data.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    13 -> {
                        values.put(data.COLUMN_HOUR, "14")
                        values.put(data.COLUMN_MIN, "05")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1405)
                        db.insert(
                                data.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    14 -> {
                        values.put(data.COLUMN_HOUR, "14")
                        values.put(data.COLUMN_MIN, "35")
                        values.put(data.COLUMN_FLAG, 6)
                        values.put(data.COLUMN_SEARCH, 1435)
                        db.insert(
                                data.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    15 -> {
                        values.put(data.COLUMN_HOUR, "14")
                        values.put(data.COLUMN_MIN, "50")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1450)
                        db.insert(
                                data.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    16 -> {
                        values.put(data.COLUMN_HOUR, "15")
                        values.put(data.COLUMN_MIN, "05")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1505)
                        db.insert(
                                data.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    17 -> {
                        values.put(data.COLUMN_HOUR, "15")
                        values.put(data.COLUMN_MIN, "20")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1520)
                        db.insert(
                                data.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    18 -> {
                        values.put(data.COLUMN_HOUR, "15")
                        values.put(data.COLUMN_MIN, "35")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1535)
                        db.insert(
                                data.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    19 -> {
                        values.put(data.COLUMN_HOUR, "15")
                        values.put(data.COLUMN_MIN, "50")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1550)
                        db.insert(
                                data.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    20 -> {
                        values.put(data.COLUMN_HOUR, "16")
                        values.put(data.COLUMN_MIN, "20")
                        values.put(data.COLUMN_FLAG, 6)
                        values.put(data.COLUMN_SEARCH, 1620)
                        db.insert(
                                data.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    21 -> {
                        values.put(data.COLUMN_HOUR, "16")
                        values.put(data.COLUMN_MIN, "35")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1635)
                        db.insert(
                                data.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    22 -> {
                        values.put(data.COLUMN_HOUR, "17")
                        values.put(data.COLUMN_MIN, "00")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1700)
                        db.insert(
                                data.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    23 -> {
                        values.put(data.COLUMN_HOUR, "17")
                        values.put(data.COLUMN_MIN, "20")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1720)
                        db.insert(
                                data.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    24 -> {
                        values.put(data.COLUMN_HOUR, "18")
                        values.put(data.COLUMN_MIN, "10")
                        values.put(data.COLUMN_FLAG, 0)
                        values.put(data.COLUMN_SEARCH, 1810)
                        db.insert(
                                data.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    25 -> {
                        values.put(data.COLUMN_HOUR, "18")
                        values.put(data.COLUMN_MIN, "25")
                        values.put(data.COLUMN_FLAG, 8)
                        values.put(data.COLUMN_SEARCH, 1825)
                        db.insert(
                                data.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }
                }
            }

            val data2 = DataContract.SATURDAY_DB_TO_WASEDA()
            db.delete(data.TABLE_NAME, null, null)
            count = 0
            while (count < 10) {
                when (count) {
                    0 -> {
                        values.put(data2.COLUMN_HOUR, "09")
                        values.put(data2.COLUMN_MIN, "35")
                        values.put(data2.COLUMN_FLAG, 0)
                        values.put(data2.COLUMN_SEARCH, 935)
                        db.insert(
                                data2.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    1 -> {
                        values.put(data2.COLUMN_HOUR, "10")
                        values.put(data2.COLUMN_MIN, "35")
                        values.put(data2.COLUMN_FLAG, 0)
                        values.put(data2.COLUMN_SEARCH, 1035)
                        db.insert(
                                data2.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    2 -> {
                        values.put(data2.COLUMN_HOUR, "11")
                        values.put(data2.COLUMN_MIN, "05")
                        values.put(data2.COLUMN_FLAG, 0)
                        values.put(data2.COLUMN_SEARCH, 1105)
                        db.insert(
                                data2.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    3 -> {
                        values.put(data2.COLUMN_HOUR, "11")
                        values.put(data2.COLUMN_MIN, "45")
                        values.put(data2.COLUMN_FLAG, 0)
                        values.put(data2.COLUMN_SEARCH, 1145)
                        db.insert(
                                data2.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    4 -> {
                        values.put(data2.COLUMN_HOUR, "12")
                        values.put(data2.COLUMN_MIN, "15")
                        values.put(data2.COLUMN_FLAG, 0)
                        values.put(data2.COLUMN_SEARCH, 1215)
                        db.insert(
                                data2.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    5 -> {
                        values.put(data2.COLUMN_HOUR, "12")
                        values.put(data2.COLUMN_MIN, "45")
                        values.put(data2.COLUMN_FLAG, 0)
                        values.put(data2.COLUMN_SEARCH, 1245)
                        db.insert(
                                data2.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    6 -> {
                        values.put(data2.COLUMN_HOUR, "14")
                        values.put(data2.COLUMN_MIN, "00")
                        values.put(data2.COLUMN_FLAG, 0)
                        values.put(data2.COLUMN_SEARCH, 1400)
                        db.insert(
                                data2.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    7 -> {
                        values.put(data2.COLUMN_HOUR, "14")
                        values.put(data2.COLUMN_MIN, "35")
                        values.put(data2.COLUMN_FLAG, 0)
                        values.put(data2.COLUMN_SEARCH, 1435)
                        db.insert(
                                data2.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }
                    8 -> {
                        values.put(data2.COLUMN_HOUR, "15")
                        values.put(data2.COLUMN_MIN, "20")
                        values.put(data2.COLUMN_FLAG, 0)
                        values.put(data2.COLUMN_SEARCH, 1520)
                        db.insert(
                                data2.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    9 -> {
                        values.put(data2.COLUMN_HOUR, "16")
                        values.put(data2.COLUMN_MIN, "20")
                        values.put(data2.COLUMN_FLAG, 0)
                        values.put(data2.COLUMN_SEARCH, 1620)
                        db.insert(
                                data2.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }
                }
            }

            count = 0
            val data3 = DataContract.DB_TO_NISHI()
            db.delete(data3.TABLE_NAME, null, null)

            while (count < 25) {
                when (count) {
                    0 -> {
                        values.put(data3.COLUMN_HOUR, "09")
                        values.put(data3.COLUMN_MIN, "20")
                        values.put(data3.COLUMN_FLAG, 0)
                        values.put(data3.COLUMN_SEARCH, 920)
                        db.insert(
                                data3.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    1 -> {
                        values.put(data3.COLUMN_HOUR, "09")
                        values.put(data3.COLUMN_MIN, "35")
                        values.put(data3.COLUMN_FLAG, 0)
                        values.put(data3.COLUMN_SEARCH, 935)
                        db.insert(
                                data3.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    2 -> {
                        values.put(data3.COLUMN_HOUR, "9")
                        values.put(data3.COLUMN_MIN, "50")
                        values.put(data3.COLUMN_FLAG, 0)
                        values.put(data3.COLUMN_SEARCH, 950)
                        db.insert(
                                data3.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    3 -> {
                        values.put(data3.COLUMN_HOUR, "10")
                        values.put(data3.COLUMN_MIN, "05")
                        values.put(data3.COLUMN_FLAG, 0)
                        values.put(data3.COLUMN_SEARCH, 1005)
                        db.insert(
                                data3.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    4 -> {
                        values.put(data3.COLUMN_HOUR, "10")
                        values.put(data3.COLUMN_MIN, "35")
                        values.put(data3.COLUMN_FLAG, 0)
                        values.put(data3.COLUMN_SEARCH, 1035)
                        db.insert(
                                data3.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    5 -> {
                        values.put(data3.COLUMN_HOUR, "10")
                        values.put(data3.COLUMN_MIN, "45")
                        values.put(data3.COLUMN_FLAG, 0)
                        values.put(data3.COLUMN_SEARCH, 1045)
                        db.insert(
                                data3.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    6 -> {
                        values.put(data3.COLUMN_HOUR, "11")
                        values.put(data3.COLUMN_MIN, "15")
                        values.put(data3.COLUMN_FLAG, 0)
                        values.put(data3.COLUMN_SEARCH, 1115)
                        db.insert(
                                data3.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    7 -> {
                        values.put(data3.COLUMN_HOUR, "11")
                        values.put(data3.COLUMN_MIN, "45")
                        values.put(data3.COLUMN_FLAG, 0)
                        values.put(data3.COLUMN_SEARCH, 1145)
                        db.insert(
                                data3.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }
                    8 -> {
                        values.put(data3.COLUMN_HOUR, "12")
                        values.put(data3.COLUMN_MIN, "05")
                        values.put(data3.COLUMN_FLAG, 1)
                        values.put(data3.COLUMN_SEARCH, 1205)
                        db.insert(
                                data3.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    9 -> {
                        values.put(data3.COLUMN_HOUR, "12")
                        values.put(data3.COLUMN_MIN, "15")
                        values.put(data3.COLUMN_FLAG, 2)
                        values.put(data3.COLUMN_SEARCH, 1215)
                        db.insert(
                                data3.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    10 -> {
                        values.put(data3.COLUMN_HOUR, "12")
                        values.put(data3.COLUMN_MIN, "45")
                        values.put(data3.COLUMN_FLAG, 0)
                        values.put(data3.COLUMN_SEARCH, 1245)
                        db.insert(
                                data3.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    11 -> {
                        values.put(data3.COLUMN_HOUR, "13")
                        values.put(data3.COLUMN_MIN, "15")
                        values.put(data3.COLUMN_FLAG, 0)
                        values.put(data3.COLUMN_SEARCH, 1315)
                        val id = db.insert(
                                data3.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                        Log.v(TAG, "THE ID IS: $id")
                    }

                    12 -> {
                        values.put(data3.COLUMN_HOUR, "13")
                        values.put(data3.COLUMN_MIN, "45")
                        values.put(data3.COLUMN_FLAG, 0)
                        values.put(data3.COLUMN_SEARCH, 1345)
                        val id = db.insert(
                                data3.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                        Log.v(TAG, "THE ID IS: $id")
                    }

                    13 -> {
                        values.put(data3.COLUMN_HOUR, "14")
                        values.put(data3.COLUMN_MIN, "05")
                        values.put(data3.COLUMN_FLAG, 0)
                        values.put(data3.COLUMN_SEARCH, 1405)
                        val id = db.insert(
                                data3.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                        Log.v(TAG, "THE ID IS: $id")
                    }

                    14 -> {
                        values.put(data3.COLUMN_HOUR, "14")
                        values.put(data3.COLUMN_MIN, "35")
                        values.put(data3.COLUMN_FLAG, 6)
                        values.put(data3.COLUMN_SEARCH, 1435)
                        val id = db.insert(
                                data3.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                        Log.v(TAG, "THE ID IS: $id")
                    }

                    15 -> {
                        values.put(data3.COLUMN_HOUR, "14")
                        values.put(data3.COLUMN_MIN, "50")
                        values.put(data3.COLUMN_FLAG, 3)
                        values.put(data3.COLUMN_SEARCH, 1450)
                        val id = db.insert(
                                data3.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                        Log.v(TAG, "THE ID IS: $id")
                    }

                    16 -> {
                        values.put(data3.COLUMN_HOUR, "15")
                        values.put(data3.COLUMN_MIN, "05")
                        values.put(data3.COLUMN_FLAG, 0)
                        values.put(data3.COLUMN_SEARCH, 1505)
                        db.insert(
                                data3.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    17 -> {
                        values.put(data3.COLUMN_HOUR, "15")
                        values.put(data3.COLUMN_MIN, "20")
                        values.put(data3.COLUMN_FLAG, 0)
                        values.put(data3.COLUMN_SEARCH, 1520)
                        db.insert(
                                data3.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    18 -> {
                        values.put(data3.COLUMN_HOUR, "15")
                        values.put(data3.COLUMN_MIN, "35")
                        values.put(data3.COLUMN_FLAG, 0)
                        values.put(data3.COLUMN_SEARCH, 1535)
                        db.insert(
                                data3.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    19 -> {
                        values.put(data3.COLUMN_HOUR, "15")
                        values.put(data3.COLUMN_MIN, "50")
                        values.put(data3.COLUMN_FLAG, 0)
                        values.put(data3.COLUMN_SEARCH, 1550)
                        db.insert(
                                data3.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    20 -> {
                        values.put(data3.COLUMN_HOUR, "16")
                        values.put(data3.COLUMN_MIN, "20")
                        values.put(data3.COLUMN_FLAG, 6)
                        values.put(data3.COLUMN_SEARCH, 1620)
                        db.insert(
                                data3.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    21 -> {
                        values.put(data3.COLUMN_HOUR, "16")
                        values.put(data3.COLUMN_MIN, "35")
                        values.put(data3.COLUMN_FLAG, 3)
                        values.put(data3.COLUMN_SEARCH, 1635)
                        db.insert(
                                data3.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    22 -> {
                        values.put(data3.COLUMN_HOUR, "17")
                        values.put(data3.COLUMN_MIN, "00")
                        values.put(data3.COLUMN_FLAG, 0)
                        values.put(data3.COLUMN_SEARCH, 1700)
                        db.insert(
                                data3.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    23 -> {
                        values.put(data3.COLUMN_HOUR, "17")
                        values.put(data3.COLUMN_MIN, "20")
                        values.put(data3.COLUMN_FLAG, 0)
                        values.put(data3.COLUMN_SEARCH, 1720)
                        db.insert(
                                data3.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    24 -> {
                        values.put(data3.COLUMN_HOUR, "18")
                        values.put(data3.COLUMN_MIN, "10")
                        values.put(data3.COLUMN_FLAG, 3)
                        values.put(data3.COLUMN_SEARCH, 1810)
                        db.insert(
                                data3.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }
                }
            }

            count = 0
            val data4 = DataContract.SATURDAY_DB_TO_NISHI()
            db.delete(data.TABLE_NAME, null, null)
            while (count < 11) {
                when (count) {
                    0 -> {
                        values.put(data4.COLUMN_HOUR, "09")
                        values.put(data4.COLUMN_MIN, "20")
                        values.put(data4.COLUMN_FLAG, 0)
                        values.put(data4.COLUMN_SEARCH, 920)
                        db.insert(
                                data4.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    1 -> {
                        values.put(data4.COLUMN_HOUR, "10")
                        values.put(data4.COLUMN_MIN, "20")
                        values.put(data4.COLUMN_FLAG, 0)
                        values.put(data4.COLUMN_SEARCH, 1020)
                        db.insert(
                                data4.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    2 -> {
                        values.put(data4.COLUMN_HOUR, "10")
                        values.put(data4.COLUMN_MIN, "50")
                        values.put(data4.COLUMN_FLAG, 0)
                        values.put(data4.COLUMN_SEARCH, 1050)
                        db.insert(
                                data4.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    3 -> {
                        values.put(data4.COLUMN_HOUR, "11")
                        values.put(data4.COLUMN_MIN, "30")
                        values.put(data4.COLUMN_FLAG, 0)
                        values.put(data4.COLUMN_SEARCH, 1130)
                        db.insert(
                                data4.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    4 -> {
                        values.put(data4.COLUMN_HOUR, "12")
                        values.put(data4.COLUMN_MIN, "00")
                        values.put(data4.COLUMN_FLAG, 0)
                        values.put(data4.COLUMN_SEARCH, 1200)
                        db.insert(
                                data4.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    5 -> {
                        values.put(data4.COLUMN_HOUR, "12")
                        values.put(data4.COLUMN_MIN, "30")
                        values.put(data4.COLUMN_FLAG, 0)
                        values.put(data4.COLUMN_SEARCH, 1230)
                        db.insert(
                                data4.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    6 -> {
                        values.put(data4.COLUMN_HOUR, "13")
                        values.put(data4.COLUMN_MIN, "45")
                        values.put(data4.COLUMN_FLAG, 0)
                        values.put(data4.COLUMN_SEARCH, 1345)
                        db.insert(
                                data4.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    7 -> {
                        values.put(data4.COLUMN_HOUR, "14")
                        values.put(data4.COLUMN_MIN, "15")
                        values.put(data4.COLUMN_FLAG, 0)
                        values.put(data4.COLUMN_SEARCH, 1415)
                        db.insert(
                                data4.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }
                    8 -> {
                        values.put(data4.COLUMN_HOUR, "14")
                        values.put(data4.COLUMN_MIN, "50")
                        values.put(data4.COLUMN_FLAG, 0)
                        values.put(data4.COLUMN_SEARCH, 1450)
                        db.insert(
                                data4.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    9 -> {
                        values.put(data4.COLUMN_HOUR, "15")
                        values.put(data4.COLUMN_MIN, "50")
                        values.put(data4.COLUMN_FLAG, 0)
                        values.put(data4.COLUMN_SEARCH, 1550)
                        db.insert(
                                data4.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }

                    10 -> {
                        values.put(data4.COLUMN_HOUR, "16")
                        values.put(data4.COLUMN_MIN, "35")
                        values.put(data4.COLUMN_FLAG, 0)
                        values.put(data4.COLUMN_SEARCH, 1635)
                        db.insert(
                                data4.TABLE_NAME,
                                null,
                                values
                        ); values.clear(); count++
                    }
                }
            }
            db.setTransactionSuccessful()

        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            db.endTransaction()
        }
        return null
    }
}