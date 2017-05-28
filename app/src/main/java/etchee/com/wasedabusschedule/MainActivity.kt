package etchee.com.wasedabusschedule

import android.content.ContentValues
import android.database.Cursor
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Toast
import etchee.com.wasedabusschedule.Data.DataContract

class MainActivity : AppCompatActivity() {

    val DB_SUCCESS: Int = 0
    val DB_ERROR: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //general init
        val context = applicationContext

        //viewpager initialization
        val viewPager = findViewById(R.id.main_viewPager) as ViewPager
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, context)
        viewPager.adapter = viewPagerAdapter

        //tab to show the current item
        val tabLayout = findViewById(R.id.list_tab) as TabLayout
        tabLayout.setupWithViewPager(viewPager)

        val toolbar = findViewById(R.id.main_toolbar) as Toolbar
        setSupportActionBar(toolbar)

        if (checkDB() == 1) {
            if (initDB() < 0) throw IllegalArgumentException("InitDB() failed")
        }
    }

    private fun checkDB(): Int {
        var cursor: Cursor? = null
        var errorFlag = 0
        try {
            //query the Waseda Table
             cursor = contentResolver.query(
                    DataContract.DB_TO_WASEDA().CONTENT_URI,
                    null,
                    null,
                    null,
                    null
            )
            if (cursor == null) {
                errorFlag = 1
            }

            //query the Waseda Table
            cursor = contentResolver.query(
                    DataContract.DB_TO_NISHI().CONTENT_URI,
                    null,
                    null,
                    null,
                    null
            )

            if (cursor == null) {
                errorFlag = 1
            }

        } catch(e: Exception) {
            e.printStackTrace()
        } finally {
            cursor?.close()
            if (errorFlag == 1) {
                Toast.makeText(applicationContext, "時刻表がない！", Toast.LENGTH_SHORT).show()
            }

        }

        return errorFlag
    }


    private fun initDB(): Int {
        var flag = 1
        var numOfRowsInserted:Int

        numOfRowsInserted= contentResolver.bulkInsert(
                DataContract.DB_TO_WASEDA().CONTENT_URI,
                getWasedaContentValuesArray()
        )

        if (numOfRowsInserted < 0) flag = -1

            numOfRowsInserted = contentResolver.bulkInsert(
                DataContract.DB_TO_NISHI().CONTENT_URI,
                getNishiContentValuesArray()
        )
        if (numOfRowsInserted < 0) flag = -1

        return flag
    }

    private fun getWasedaContentValuesArray(): Array<ContentValues>? {
        val data: DataContract.DB_TO_WASEDA = DataContract.DB_TO_WASEDA()
        val values:ContentValues = ContentValues()
        val array:Array<ContentValues>? = null


        /*      From data helper:

                val CREATE_TO_WASEDA_TABLE:String = "CREATE TABLE IF NOT EXISTS " +
                TO_WASEDA.TABLE_NAME + " (" +
                TO_WASEDA._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TO_WASEDA.COLUMN_HOUR + " INTEGER, " +
                TO_WASEDA.COLUMN_MIN + " INTEGER, " +
                TO_WASEDA.COLUMN_FLAG + " INTEGER);"
        * */


        values.put(data.COLUMN_HOUR, 9)
        values.put(data.COLUMN_MIN, 20)
        values.put(data.COLUMN_FLAG, 0)
        array!![0] = values; values.clear()

        values.put(data.COLUMN_HOUR, 9)
        values.put(data.COLUMN_MIN, 35)
        values.put(data.COLUMN_FLAG, 0)
        array[1] = values; values.clear()

        values.put(data.COLUMN_HOUR, 9)
        values.put(data.COLUMN_MIN, 50)
        values.put(data.COLUMN_FLAG, 0)
        array[2] = values; values.clear()

        values.put(data.COLUMN_HOUR, 10)
        values.put(data.COLUMN_MIN, 5)
        values.put(data.COLUMN_FLAG, 0)
        array[3] = values; values.clear()

        values.put(data.COLUMN_HOUR, 10)
        values.put(data.COLUMN_MIN, 35)
        values.put(data.COLUMN_FLAG, 0)
        array[4] = values; values.clear()

        values.put(data.COLUMN_HOUR, 10)
        values.put(data.COLUMN_MIN, 45)
        values.put(data.COLUMN_FLAG, 0)
        array[5] = values; values.clear()

        values.put(data.COLUMN_HOUR,11)
        values.put(data.COLUMN_MIN, 15)
        values.put(data.COLUMN_FLAG, 0)
        array[6] = values; values.clear()

        values.put(data.COLUMN_HOUR,11)
        values.put(data.COLUMN_MIN, 45)
        values.put(data.COLUMN_FLAG, 0)
        array[7] = values; values.clear()

        values.put(data.COLUMN_HOUR, 12)
        values.put(data.COLUMN_MIN, 5)
        values.put(data.COLUMN_FLAG, 1)
        array[8] = values; values.clear()

        values.put(data.COLUMN_HOUR, 12)
        values.put(data.COLUMN_MIN, 15)
        values.put(data.COLUMN_FLAG, 2)
        array[9] = values; values.clear()

        values.put(data.COLUMN_HOUR, 12)
        values.put(data.COLUMN_MIN, 45)
        values.put(data.COLUMN_FLAG, 0)
        array[10] = values; values.clear()

        values.put(data.COLUMN_HOUR, 13)
        values.put(data.COLUMN_MIN, 15)
        values.put(data.COLUMN_FLAG, 0)
        array[11] = values; values.clear()

        values.put(data.COLUMN_HOUR, 13)
        values.put(data.COLUMN_MIN, 45)
        values.put(data.COLUMN_FLAG, 0)
        array[12] = values; values.clear()

        values.put(data.COLUMN_HOUR, 14)
        values.put(data.COLUMN_MIN, 5)
        values.put(data.COLUMN_FLAG, 0)
        array[13] = values; values.clear()

        values.put(data.COLUMN_HOUR, 15)
        values.put(data.COLUMN_MIN, 5)
        values.put(data.COLUMN_FLAG, 0)
        array[14] = values; values.clear()

        values.put(data.COLUMN_HOUR, 15)
        values.put(data.COLUMN_MIN, 20)
        values.put(data.COLUMN_FLAG, 0)
        array[15] = values; values.clear()

        values.put(data.COLUMN_HOUR, 15)
        values.put(data.COLUMN_MIN, 35)
        values.put(data.COLUMN_FLAG, 0)
        array[16] = values; values.clear()

        values.put(data.COLUMN_HOUR, 15)
        values.put(data.COLUMN_MIN, 50)
        values.put(data.COLUMN_FLAG, 0)
        array[17] = values; values.clear()

        values.put(data.COLUMN_HOUR, 16)
        values.put(data.COLUMN_MIN, 20)
        values.put(data.COLUMN_FLAG, 0)
        array[18] = values; values.clear()

        values.put(data.COLUMN_HOUR, 16)
        values.put(data.COLUMN_MIN, 35)
        values.put(data.COLUMN_FLAG, 3)
        array[19] = values; values.clear()

        values.put(data.COLUMN_HOUR, 17)
        values.put(data.COLUMN_MIN, 0)
        values.put(data.COLUMN_FLAG, 0)
        array[20] = values; values.clear()

        values.put(data.COLUMN_HOUR, 17)
        values.put(data.COLUMN_MIN, 20)
        values.put(data.COLUMN_FLAG, 0)
        array[21] = values; values.clear()

        values.put(data.COLUMN_HOUR, 18)
        values.put(data.COLUMN_MIN, 10)
        values.put(data.COLUMN_FLAG, 3)
        array[22] = values; values.clear()

        return array
    }

    private fun getNishiContentValuesArray(): Array<ContentValues>? {
        val data: DataContract.DB_TO_WASEDA = DataContract.DB_TO_WASEDA()
        val values:ContentValues = ContentValues()
        val array:Array<ContentValues>? = null

        values.put(data.COLUMN_HOUR, 9)
        values.put(data.COLUMN_MIN, 20)
        values.put(data.COLUMN_FLAG, 0)
        array?.set(0, values); values.clear()

        values.put(data.COLUMN_HOUR, 9)
        values.put(data.COLUMN_MIN, 35)
        values.put(data.COLUMN_FLAG, 0)
        array?.set(1, values); values.clear()

        values.put(data.COLUMN_HOUR, 9)
        values.put(data.COLUMN_MIN, 50)
        values.put(data.COLUMN_FLAG, 0)
        array?.set(2, values); values.clear()

        values.put(data.COLUMN_HOUR, 10)
        values.put(data.COLUMN_MIN, 5)
        values.put(data.COLUMN_FLAG, 0)
        array?.set(3, values); values.clear()

        values.put(data.COLUMN_HOUR, 10)
        values.put(data.COLUMN_MIN, 35)
        values.put(data.COLUMN_FLAG, 0)
        array?.set(4, values); values.clear()

        values.put(data.COLUMN_HOUR, 10)
        values.put(data.COLUMN_MIN, 45)
        values.put(data.COLUMN_FLAG, 0)
        array?.set(5, values); values.clear()

        values.put(data.COLUMN_HOUR,11)
        values.put(data.COLUMN_MIN, 0)
        values.put(data.COLUMN_FLAG, 0)
        array?.set(6, values); values.clear()

        values.put(data.COLUMN_HOUR,11)
        values.put(data.COLUMN_MIN, 30)
        values.put(data.COLUMN_FLAG, 0)
        array?.set(7, values); values.clear()

        values.put(data.COLUMN_HOUR, 12)
        values.put(data.COLUMN_MIN, 5)
        values.put(data.COLUMN_FLAG, 4)
        array?.set(8, values); values.clear()

        values.put(data.COLUMN_HOUR, 12)
        values.put(data.COLUMN_MIN, 20)
        values.put(data.COLUMN_FLAG, 5)
        array?.set(9, values); values.clear()

        values.put(data.COLUMN_HOUR, 12)
        values.put(data.COLUMN_MIN, 45)
        values.put(data.COLUMN_FLAG, 0)
        array?.set(10, values); values.clear()

        values.put(data.COLUMN_HOUR, 13)
        values.put(data.COLUMN_MIN, 0)
        values.put(data.COLUMN_FLAG, 0)
        array?.set(11, values); values.clear()

        values.put(data.COLUMN_HOUR, 13)
        values.put(data.COLUMN_MIN, 30)
        values.put(data.COLUMN_FLAG, 0)
        array?.set(12, values); values.clear()

        values.put(data.COLUMN_HOUR, 14)
        values.put(data.COLUMN_MIN, 5)
        values.put(data.COLUMN_FLAG, 0)
        array?.set(13, values); values.clear()

        values.put(data.COLUMN_HOUR, 14)
        values.put(data.COLUMN_MIN, 35)
        values.put(data.COLUMN_FLAG, 6)
        array?.set(14, values); values.clear()

        values.put(data.COLUMN_HOUR, 14)
        values.put(data.COLUMN_MIN, 50)
        values.put(data.COLUMN_FLAG, 0)
        array?.set(15, values); values.clear()

        values.put(data.COLUMN_HOUR, 15)
        values.put(data.COLUMN_MIN, 5)
        values.put(data.COLUMN_FLAG, 0)
        array?.set(16, values); values.clear()

        values.put(data.COLUMN_HOUR, 15)
        values.put(data.COLUMN_MIN, 20)
        values.put(data.COLUMN_FLAG, 0)
        array?.set(17, values); values.clear()

        values.put(data.COLUMN_HOUR, 16)
        values.put(data.COLUMN_MIN, 20)
        values.put(data.COLUMN_FLAG, 0)
        array?.set(18, values); values.clear()

        values.put(data.COLUMN_HOUR, 16)
        values.put(data.COLUMN_MIN, 35)
        values.put(data.COLUMN_FLAG, 0)
        array?.set(19, values); values.clear()

        values.put(data.COLUMN_HOUR, 17)
        values.put(data.COLUMN_MIN, 0)
        values.put(data.COLUMN_FLAG, 0)
        array?.set(20, values); values.clear()

        values.put(data.COLUMN_HOUR, 17)
        values.put(data.COLUMN_MIN, 20)
        values.put(data.COLUMN_FLAG, 0)
        array?.set(21, values); values.clear()

        values.put(data.COLUMN_HOUR, 18)
        values.put(data.COLUMN_MIN, 10)
        values.put(data.COLUMN_FLAG, 0)
        array?.set(22, values); values.clear()

        values.put(data.COLUMN_HOUR, 18)
        values.put(data.COLUMN_MIN, 25)
        values.put(data.COLUMN_FLAG, 8)
        array?.set(23, values); values.clear()

        return array
    }
}
