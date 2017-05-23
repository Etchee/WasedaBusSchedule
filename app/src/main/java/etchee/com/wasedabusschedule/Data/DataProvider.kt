package etchee.com.wasedabusschedule.Data

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

/**
 * Content Provider Function
 * Created by rikutoechigoya on 2017/05/23.
 */
class DataProvider : ContentProvider() {

    private val CODE_TO_WASEDA = 100
    private val CODE_TO_NISHI = 101
    private val CODE_SAT_TO_WASEDA = 200
    private val CODE_SAT_TO_NISHI = 201

    private val data:DataContract.GlobalConstants = DataContract.GlobalConstants()
    private val matcher : UriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    private var dbHelper:DataDbHelper = null!!

    private fun UriMatcher() {
        matcher.addURI(data.CONTENT_AUTHORITY, data.PATH_TO_WASEDA, CODE_TO_WASEDA)
        matcher.addURI(data.CONTENT_AUTHORITY, data.PATH_TO_NISHI, CODE_TO_NISHI)
        matcher.addURI(data.CONTENT_AUTHORITY, data.PATH_SAT_TO_WASEDA, CODE_SAT_TO_WASEDA)
        matcher.addURI(data.CONTENT_AUTHORITY, data.PATH_SAT_TO_NISHI, CODE_SAT_TO_NISHI)
    }

    override fun onCreate(): Boolean {
        dbHelper = DataDbHelper(context)

        return true
    }

    override fun query(uri: Uri?, projection: Array<out String>?, selection: String?,
                       selectionArgs: Array<out String>?, sortOrder: String?): Cursor {


        val database = dbHelper.getReadableDatabase()
        val match = matcher.match(uri)
        val cursor:Cursor

        when(match) {

            //query entire calendar table
            CODE_TO_WASEDA -> {
                cursor = database.query(
                        CalendarEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs, null, null, null)
            }

            //query the entire event table
            CODE_TO_NISHI ->{
                cursor = database.query(
                        EventEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs, null, null, null)
            }



            //query entire event_type table
            CODE_SAT_TO_WASEDA -> {
                cursor = database.query(
                        EventTypeEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null, null, null)
            }

            CODE_SAT_TO_NISHI -> {
                cursor = database.query(
                        EventTypeEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null, null, null)
            }

    }

    override fun insert(p0: Uri?, p1: ContentValues?): Uri {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(p0: Uri?, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(p0: Uri?, p1: String?, p2: Array<out String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getType(p0: Uri?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}