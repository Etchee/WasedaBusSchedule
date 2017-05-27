package etchee.com.wasedabusschedule.Data

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.MatrixCursor
import android.database.SQLException
import android.util.Log


/**
 * Database Helper class
 * Created by rikutoechigoya on 2017/05/22.
 */
class DataDbHelper(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?,
                   version: Int): SQLiteOpenHelper(context, name, factory, version) {

    private val TO_WASEDA = DataContract.DB_TO_WASEDA()
    private val TO_NISHI = DataContract.DB_TO_NISHI()
    private val SAT_TO_WASEDA = DataContract.SATURDAY_DB_TO_WASEDA()
    private val SAT_TO_NISHI = DataContract.SATURDAY_DB_TO_NISHI()

    override fun onCreate(db: SQLiteDatabase?) {
        //Create the Calendar table
        val CREATE_TO_WASEDA_TABLE:String = "CREATE TABLE IF NOT EXISTS " +
                TO_WASEDA.TABLE_NAME + " (" +
                TO_WASEDA._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TO_WASEDA.COLUMN_HOUR + " INTEGER, " +
                TO_WASEDA.COLUMN_MIN + " INTEGER, " +
                TO_WASEDA.COLUMN_FLAG + " INTEGER);"

        val CREATE_TO_NISHI_TABLE:String = "CREATE TABLE IF NOT EXISTS " +
                TO_NISHI.TABLE_NAME + " (" +
                TO_NISHI._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TO_NISHI.COLUMN_HOUR + " INTEGER AUTOINCREMENT, " +
                TO_NISHI.COLUMN_MIN + " INTEGER, " +
                TO_NISHI.COLUMN_FLAG + " INTEGER);"

        val CREATE_SAT_TO_WASEDA_TABLE:String = "CREATE TABLE IF NOT EXISTS " +
                SAT_TO_WASEDA.TABLE_NAME + " (" +
                SAT_TO_WASEDA._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SAT_TO_WASEDA.COLUMN_HOUR + " INTEGER AUTOINCREMENT, " +
                SAT_TO_WASEDA.COLUMN_MIN + " INTEGER, " +
                SAT_TO_WASEDA.COLUMN_FLAG + " INTEGER);"

        val CREATE_SAT_TO_NISHI_TABLE:String = "CREATE TABLE IF NOT EXISTS " +
                SAT_TO_NISHI.TABLE_NAME + " (" +
                SAT_TO_NISHI._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SAT_TO_NISHI.COLUMN_HOUR + " INTEGER AUTOINCREMENT, " +
                SAT_TO_NISHI.COLUMN_MIN + " INTEGER, " +
                SAT_TO_NISHI.COLUMN_FLAG + " INTEGER);"

        db!!.execSQL(CREATE_TO_WASEDA_TABLE)
        db.execSQL(CREATE_TO_NISHI_TABLE)
        db.execSQL(CREATE_SAT_TO_WASEDA_TABLE)
        db.execSQL(CREATE_SAT_TO_NISHI_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}