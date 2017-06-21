package etchee.com.wasedabusschedule

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import etchee.com.wasedabusschedule.Data.DataContract
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val DB_SUCCESS: Int = 0
    val DB_ERROR: Int = 1
    val TAG = this.javaClass.simpleName
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = applicationContext

        //VIEWPAGER ADAPTER
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, applicationContext)
        main_viewPager.adapter = viewPagerAdapter
        list_tab.setupWithViewPager(main_viewPager)

        //TOOLBAR
        setSupportActionBar(main_toolbar)
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
}
