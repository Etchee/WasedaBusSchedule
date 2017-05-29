package etchee.com.wasedabusschedule

import android.content.ContentValues
import android.database.Cursor
import android.database.SQLException
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Toast
import etchee.com.wasedabusschedule.Data.AsyncInitTables
import etchee.com.wasedabusschedule.Data.DataContract

class MainActivity : AppCompatActivity() {

    val DB_SUCCESS: Int = 0
    val DB_ERROR: Int = 1
    val TAG = this.javaClass.simpleName

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

        AsyncInitTables(context).execute()


    }
}
