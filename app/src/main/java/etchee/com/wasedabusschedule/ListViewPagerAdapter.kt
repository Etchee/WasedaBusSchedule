package etchee.com.wasedabusschedule

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Adapter for viewPager
 * Created by etchee on 2017/05/24.
 */

class ListViewPagerAdapter(fm: FragmentManager, private val context: Context) : FragmentStatePagerAdapter(fm) {

    private val TAG = javaClass.simpleName

    override fun getItem(position: Int): Fragment? {
        return null
    }

    //return the current number of item
    override fun getCount(): Int {
        return 0
    }

    override fun getPageTitle(position: Int): CharSequence {
        return ""
    }
}
