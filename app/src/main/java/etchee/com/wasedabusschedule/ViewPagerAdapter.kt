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

class ViewPagerAdapter(fm: FragmentManager, private val context: Context) : FragmentStatePagerAdapter(fm) {

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
    /**
     *  This method is being called twice every second due to UI update from the time count.
     *  DO NOT do heavy process on here
     */
    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        when(position){

        //toWasedaFragment
            0 -> {
                return context.resources.getString(R.string.tab_text_to_waseda)
            }

        //toNishiFragment
            1 -> {
                return context.resources.getString(R.string.tab_hint_text_to_nishi)
            }

        //impossible viewPager index
            else -> {
                Log.e(TAG, "ViewPagerAdapter did not recognize the item number")
                return ""
            }
        }
    }
}
