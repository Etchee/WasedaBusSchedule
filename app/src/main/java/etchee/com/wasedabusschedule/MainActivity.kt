package etchee.com.wasedabusschedule

import android.content.Context
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var viewPager:ViewPager = null!!
    private var viewPagerAdapter:ListViewPagerAdapter
    private var context:Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //general init
        context = applicationContext

        //viewpager initialization
        viewPager = findViewById(R.id.main_viewPager) as ViewPager
        viewPagerAdapter = ListViewPagerAdapter(supportFragmentManager, context)
        viewPager.adapter = viewPagerAdapter


    }
}