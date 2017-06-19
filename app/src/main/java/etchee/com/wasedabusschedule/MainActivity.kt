package etchee.com.wasedabusschedule

import android.app.Activity
import android.app.FragmentManager
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import etchee.com.wasedabusschedule.Data.DataContract
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    val DB_SUCCESS: Int = 0
    val DB_ERROR: Int = 1
    val TAG = this.javaClass.simpleName
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = applicationContext

        //database check
        if (!checkDB()) {
            Log.v(TAG, "DATABASE INIT STARTED")
            //Initialize the database in order
//            AsyncInitTables(applicationContext).execute()
        }

        //viewpager initialization
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, applicationContext)
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
             R.id.option_item_about->{
                 val intent = Intent(this, AboutActivity::class.java)
                 startActivity(intent)
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
        val contentResolver = context.contentResolver
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
        } catch(e: Exception) {
            e.printStackTrace()
        } finally {
            cursor?.close()

        }
        return flag
    }
}
