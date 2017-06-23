package etchee.com.wasedabusschedule

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

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

        val hint = getDayHintString(Calendar.getInstance().get(Calendar.DAY_OF_WEEK))
        Toast.makeText(this, hint, Toast.LENGTH_SHORT).show()
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

    fun getDayHintString(day:Int):String {
        when (day) {
            1 -> return context.resources.getString(R.string.hint_text_sunday)
            2 -> return context.resources.getString(R.string.hint_text_monday)
            3 -> return context.resources.getString(R.string.hint_text_tuesday)
            4 -> return context.resources.getString(R.string.hint_text_wednesday)
            5 -> return context.resources.getString(R.string.hint_text_thursday)
            6 -> return context.resources.getString(R.string.hint_text_friday)
            7 -> return context.resources.getString(R.string.hint_text_saturday)
            else-> throw IllegalArgumentException(TAG + "Calendar did not recognize day.")
        }
    }
}
