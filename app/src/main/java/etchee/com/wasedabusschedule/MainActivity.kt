package etchee.com.wasedabusschedule

import android.database.Cursor
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
             R.id.menu_create_database ->{
                //fire the asyncTask
                 AsyncInitTables(applicationContext).execute()
            }

            R.id.menu_delete_database ->{
                val rows = contentResolver.delete(
                        DataContract.DB_TO_WASEDA().CONTENT_URI,
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


}
