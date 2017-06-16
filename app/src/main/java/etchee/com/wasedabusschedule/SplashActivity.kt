package etchee.com.wasedabusschedule

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.content.Intent


/**
 * Created by rikutoechigoya on 2017/06/15.
 */
class SplashActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)

//        AsyncInitTables(this@SplashActivity).execute()
        // Start home activity
        startActivity(Intent(this, MainActivity::class.java))
        // close splash activity
        finish()
    }
}