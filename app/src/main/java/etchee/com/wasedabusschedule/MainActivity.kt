package etchee.com.wasedabusschedule

import android.app.ProgressDialog
import android.content.ContentValues
import android.database.Cursor
import android.database.SQLException
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import etchee.com.wasedabusschedule.Data.AsyncInitTables
import etchee.com.wasedabusschedule.Data.DataContract
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val DB_SUCCESS: Int = 0
    val DB_ERROR: Int = 1
    val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //general init
        val context = this@MainActivity

        //database check
        val dialog: ProgressDialog = ProgressDialog(context, R.style.DialogTheme)
        if (!checkDB()) {
            dialog.setMessage("Loading...")
            dialog.isIndeterminate = true
            dialog.setCancelable(false)
            dialog.show()
            //Initialize the database
            initWasedaTable()
            initNishiTable()
            initSat_NishiTable()
            initSat_WasedaTable()
        }

        if (dialog.isShowing) dialog.cancel()

        //viewpager initialization
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, context)
        main_viewPager.adapter = viewPagerAdapter

        //tab to show the current item
        list_tab.setupWithViewPager(main_viewPager)
        setSupportActionBar(main_toolbar)
//            AsyncInitTables(context).execute()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
             R.id.menu_create_database ->{
                //fire the asyncTask
//                 AsyncInitTables(applicationContext).execute()
                 checkDB()
            }

            R.id.menu_delete_database ->{
                val rows = contentResolver.delete(
                        DataContract.DB_TO_WASEDA().CONTENT_URI,
                        null,
                        null
                ) + contentResolver.delete(
                        DataContract.DB_TO_NISHI().CONTENT_URI,
                        null,
                        null
                )
                Toast.makeText(this, rows.toString() + " rows deleted", Toast.LENGTH_SHORT).show()
            }

            R.id.menu_get_count ->{
                var cursor:Cursor? = contentResolver.query(
                        DataContract.DB_TO_WASEDA().CONTENT_URI,
                        null, null, null, null
                )

                Toast.makeText(this, "Waseda Table has: " + cursor?.count.toString() + " rows.",
                        Toast.LENGTH_SHORT).show()

                cursor = contentResolver.query(
                        DataContract.DB_TO_NISHI().CONTENT_URI,
                        null, null, null, null
                )

                Toast.makeText(this, "Nishi Table has: " + cursor?.count.toString() + " rows.",
                        Toast.LENGTH_SHORT).show()

                cursor?.close()
            }
        }

        return super.onOptionsItemSelected(item)
    }


    /**
     * No problem -> true
     * No table -> false
     */
    private fun checkDB(): Boolean {
        var cursor: Cursor? = null
        var flag = true
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
                flag = false
            }

            cursor = null

            //query the NISHI table
            cursor = contentResolver.query(
                    DataContract.DB_TO_NISHI().CONTENT_URI,
                    null,
                    null,
                    null,
                    null
            )

            if (!cursor.moveToFirst()) {
                flag = false
            }

            //query the sat Waseda table
            cursor = contentResolver.query(
                    DataContract.SATURDAY_DB_TO_WASEDA().CONTENT_URI,
                    null,
                    null,
                    null,
                    null
            )

            if (!cursor.moveToFirst()) {
                flag = false
            }

            //query the sat NISHI table
            cursor = contentResolver.query(
                    DataContract.SATURDAY_DB_TO_NISHI().CONTENT_URI,
                    null,
                    null,
                    null,
                    null
            )

            if (!cursor.moveToFirst()) {
                flag = false
            }

        } catch(e: Exception) {
            e.printStackTrace()
        } finally {
            cursor?.close()

        }

        return flag
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

            while(count < 23) {
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
        var uri: Uri? = null

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
        var uri: Uri? = null

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
        var uri: Uri? = null

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
