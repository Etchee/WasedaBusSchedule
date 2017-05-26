package etchee.com.wasedabusschedule

import android.database.Cursor
import android.net.Uri
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
            initDB()
        }
    }

    private fun checkDB(): Int {
        var cursor: Cursor? = null
        var errorFlag:Int = 0
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
            Toast.makeText(applicationContext, "時刻表がない！", Toast.LENGTH_SHORT).show()
        }

        return errorFlag
    }

    private fun initDB() {
        var uri: Uri? = null
    }
}