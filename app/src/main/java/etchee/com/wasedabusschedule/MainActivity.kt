package etchee.com.wasedabusschedule

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

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
    }
}