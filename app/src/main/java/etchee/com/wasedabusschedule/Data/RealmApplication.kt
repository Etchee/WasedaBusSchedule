package etchee.com.wasedabusschedule.Data

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by rikutoechigoya on 2017/05/20.
 */

class RealmApplication : Application() {

    var databaseObj = DataContract.DatabaseConstants()
    var GlobalConstantsObj = DataContract.GlobalConstants()
    val SCHEMA_VERSION:Long = 0

    override fun onCreate() {
        super.onCreate()
        //create a new Realm database
        Realm.init
        val realmConfiguration = RealmConfiguration.Builder(applicationContext)
                .name(databaseObj.DATABASE_NAME)
                .schemaVersion(SCHEMA_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }
}
