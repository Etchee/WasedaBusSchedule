package etchee.com.wasedabusschedule

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.Log
import etchee.com.wasedabusschedule.Fragments.ToNishiFragment
import etchee.com.wasedabusschedule.Fragments.ToWasedaFragment

/**
 * Adapter for viewPager
 * Created by etchee on 2017/05/24.
 */

class ListViewPagerAdapter(fm: FragmentManager, private val context: Context) : FragmentStatePagerAdapter(fm) {

    private val TAG = javaClass.simpleName

    //return appropriate fragment
    override fun getItem(position: Int): Fragment? {

        when(position){

            //toWasedaFragment
            0 -> {
                return ToWasedaFragment()
            }

            //toNishiFragment
            1 -> {
                return ToNishiFragment()
            }

            //impossible viewPager index
            else -> {
                Log.e(TAG, "ViewPagerAdapter did not recognize the item number")
                return null
            }
        }
    }

    //return the current number of item
    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        when(position){

        //toWasedaFragment
            0 -> {
                return "早稲田行き"
            }

        //toNishiFragment
            1 -> {
                return "西早稲田行き"
            }

        //impossible viewPager index
            else -> {
                Log.e(TAG, "ViewPagerAdapter did not recognize the item number")
                return ""
            }
        }
    }
}
