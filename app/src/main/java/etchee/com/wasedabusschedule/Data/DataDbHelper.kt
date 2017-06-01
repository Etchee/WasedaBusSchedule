package etchee.com.wasedabusschedule.Data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


/**
 * Database Helper class
 * Created by rikutoechigoya on 2017/05/22.
 */
class DataDbHelper(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?,
                   version: Int): SQLiteOpenHelper(context, name, factory, version) {

    private val to_wasedaDb = DataContract.DB_TO_WASEDA()
    private val to_nishiDb = DataContract.DB_TO_NISHI()
    private val sat_to_wasedaDb = DataContract.SATURDAY_DB_TO_WASEDA()
    private val sat_to_nishiDb = DataContract.SATURDAY_DB_TO_NISHI()



    override fun onCreate(db: SQLiteDatabase) {
        //Create the Calendar table
        val CREATE_TO_WASEDA_TABLE:String = "CREATE TABLE IF NOT EXISTS " +
                to_wasedaDb.TABLE_NAME + " (" +
                to_wasedaDb._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                to_wasedaDb.COLUMN_HOUR + " INTEGER, " +
                to_wasedaDb.COLUMN_MIN + " INTEGER, " +
                to_wasedaDb.COLUMN_SEARCH + " INTEGER, " +
                to_wasedaDb.COLUMN_FLAG + " INTEGER);"

        val CREATE_TO_NISHI_TABLE:String = "CREATE TABLE IF NOT EXISTS " +
                to_nishiDb.TABLE_NAME + " (" +
                to_nishiDb._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                to_nishiDb.COLUMN_HOUR + " INTEGER NOT NULL, " +
                to_nishiDb.COLUMN_MIN + " INTEGER NOT NULL, " +
                to_nishiDb.COLUMN_SEARCH + " INTEGER, " +
                to_nishiDb.COLUMN_FLAG + " INTEGER NOT NULL);"

        val CREATE_SAT_TO_WASEDA_TABLE:String = "CREATE TABLE IF NOT EXISTS " +
                sat_to_wasedaDb.TABLE_NAME + " (" +
                sat_to_wasedaDb._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                sat_to_wasedaDb.COLUMN_HOUR + " INTEGER NOT NULL, " +
                sat_to_wasedaDb.COLUMN_MIN + " INTEGER NOT NULL, " +
                sat_to_wasedaDb.COLUMN_SEARCH + " INTEGER, " +
                sat_to_wasedaDb.COLUMN_FLAG + " INTEGER NOT NULL);"

        val CREATE_SAT_TO_NISHI_TABLE:String = "CREATE TABLE IF NOT EXISTS " +
                sat_to_nishiDb.TABLE_NAME + " (" +
                sat_to_nishiDb._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                sat_to_nishiDb.COLUMN_HOUR + " INTEGER NOT NULL, " +
                sat_to_nishiDb.COLUMN_MIN + " INTEGER NOT NULL, " +
                sat_to_nishiDb.COLUMN_SEARCH + " INTEGER, " +
                sat_to_nishiDb.COLUMN_FLAG + " INTEGER NOT NULL);"

        db.execSQL(CREATE_TO_WASEDA_TABLE)
        db.execSQL(CREATE_TO_NISHI_TABLE)
        db.execSQL(CREATE_SAT_TO_WASEDA_TABLE)
        db.execSQL(CREATE_SAT_TO_NISHI_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}