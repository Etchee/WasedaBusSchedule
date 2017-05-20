package etchee.com.wasedabusschedule.Data

import io.realm.RealmObject

/**
 * Class to contain all the constants
 * Created by rikutoechigoya on 2017/05/19.
 */

class DataContract {
    data class GlobalConstants(
            val PASS_STRING:String = "PASS_STRING"
    )


    data class DatabaseConstants(
            val DATABASE_NAME:String = "SCHEDULE_DB"
    )
}
