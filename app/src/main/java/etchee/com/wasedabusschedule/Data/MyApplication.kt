package etchee.com.wasedabusschedule.Data

import android.app.Application
import io.realm.Realm

/**
 * Created by rikutoechigoya on 2017/05/21.
 */

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}
