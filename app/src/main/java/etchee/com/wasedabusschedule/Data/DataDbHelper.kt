package etchee.com.wasedabusschedule.Data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Database Helper class
 * Created by rikutoechigoya on 2017/05/22.
 */
class DataDbHelper(context: Context?,
                   name: String?,
                   factory: SQLiteDatabase.CursorFactory?,
                   version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}