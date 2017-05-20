package etchee.com.wasedabusschedule.Data

import android.app.Application
import android.provider.Settings
import etchee.com.wasedabusschedule.Data.DataContract.DatabaseConstants
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by rikutoechigoya on 2017/05/20.
 */

class RealmApplication : Application() {

    val databaseObj = DataContract.DatabaseConstants()
    val GlobalConstantsObj = DataContract.GlobalConstants()
    val SCHEMA_VERSION:Long = 0

    override fun onCreate() {
        super.onCreate()

        //create a new Realm database
        val realmConfiguration = RealmConfiguration.Builder(this)
                .name(databaseObj.DATABASE_NAME)
                .schemaVersion(SCHEMA_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }
}
