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
    private var dbHelper:DataDbHelper? = null

    private val DATABASE_VERSION:Int = 1

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


        val database = dbHelper?.getReadableDatabase()
        val match = matcher.match(uri)
        val cursor: Cursor

        when (match) {

            CODE_TO_WASEDA -> {
                cursor = database?.query(
                        DataContract.DB_TO_WASEDA().TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs, null, null, null) as Cursor
            }

            CODE_TO_NISHI -> {
                cursor = database?.query(
                        DataContract.DB_TO_NISHI().TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs, null, null, null) as Cursor
            }

            CODE_SAT_TO_WASEDA -> {
                cursor = database?.query(
                        DataContract.SATURDAY_DB_TO_WASEDA().TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null, null, null) as Cursor
            }

            CODE_SAT_TO_NISHI -> {
                cursor = database?.query(
                        DataContract.SATURDAY_DB_TO_NISHI().TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null, null, null) as Cursor
            }

            else -> {
                throw IllegalArgumentException("Query method cannot handle " +
                        "unsupported URI: " + uri)
            }
        }
        cursor.setNotificationUri(context!!.contentResolver, uri)
        return cursor
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri {
        val match = matcher.match(uri)
        val uri_new:Uri

        when (match) {

            CODE_TO_WASEDA -> {
                uri_new = insertInWasedaTable(uri, values!!)
            }

            CODE_TO_NISHI -> {
                uri_new = insertInNishiTable(uri, values!!)
            }

            CODE_SAT_TO_WASEDA -> {
                uri_new = insertInSATWasedaTable(uri, values!!)
            }

            CODE_SAT_TO_NISHI -> {
                uri_new = insertInSATNishiTable(uri, values!!)
            }

            else -> throw IllegalArgumentException("Insert method cannot handle " +
                    "unsupported URI: " + uri)
        }
        return uri_new
    }

    fun insertInWasedaTable(uri: Uri, values: ContentValues):Uri {
        val database = dbHelper?.writableDatabase

        //id is the ID of the newly inserted row. Returns -1 in case of an error with insertion.
        val id = database?.insert(DataContract.DB_TO_WASEDA().TABLE_NAME, null, values)

        values.getAsString(DataContract.DB_TO_WASEDA().COLUMN_HOUR) ?:
                throw IllegalArgumentException("Content provider's insert method of " +
                "the calendar table has received null for the date value. " +
                        "Check what is passed into the insert method.")

        if (id != null) {
            if (id < 0) {
                throw IllegalArgumentException("Content provider's insertion has failed.")
            }
        }

        return ContentUris.withAppendedId(uri, id as Long)
    }

    fun insertInNishiTable(uri: Uri, values: ContentValues):Uri {
        val database = dbHelper?.writableDatabase

        //error prevention measure #1
        val id = database?.insert(DataContract.DB_TO_NISHI().TABLE_NAME, null, values)
        if (id != null) {
            if (id < 0) {
                throw IllegalArgumentException("Content provider's insertion has failed.")
            }
        }

        //error prevention measure #2
        values.getAsString(DataContract.DB_TO_NISHI().COLUMN_HOUR) ?:
                throw IllegalArgumentException("Content provider's insert method of " +
                        "the calendar table has received null for the date value. " +
                        "Check what is passed into the insert method.")

        return ContentUris.withAppendedId(uri, id as Long)
    }

    fun insertInSATWasedaTable(uri: Uri, values: ContentValues):Uri {
        val database = dbHelper?.writableDatabase

        //error prevention measure #1
        val id = database?.insert(DataContract.SATURDAY_DB_TO_WASEDA().TABLE_NAME, null, values)
        if (id != null) {
            if (id < 0) {
                throw IllegalArgumentException("Content provider's insertion has failed.")
            }
        }

        //error prevention measure #2
        values.getAsString(DataContract.SATURDAY_DB_TO_WASEDA().COLUMN_HOUR) ?:
                throw IllegalArgumentException("Content provider's insert method of " +
                        "the calendar table has received null for the date value. " +
                        "Check what is passed into the insert method.")

        return ContentUris.withAppendedId(uri, id as Long)
    }

    fun insertInSATNishiTable(uri: Uri, values: ContentValues):Uri {
        val database = dbHelper?.writableDatabase

        //error prevention measure #1
        val id = database?.insert(DataContract.SATURDAY_DB_TO_NISHI().TABLE_NAME, null, values)
        if (id != null) {
            if (id < 0) {
                throw IllegalArgumentException("Content provider's insertion has failed.")
            }
        }

        //error prevention measure #2
        values.getAsString(DataContract.SATURDAY_DB_TO_NISHI().COLUMN_HOUR) ?:
                throw IllegalArgumentException("Content provider's insert method of " +
                        "the calendar table has received null for the date value. " +
                        "Check what is passed into the insert method.")

        return ContentUris.withAppendedId(uri, id as Long)
    }

    override fun update(p0: Uri?, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        //not needed right now

        return 0
    }

    override fun delete(uri: Uri?, selection: String?, selectionArgs: Array<out String>?): Int {
        val numOfRowsDeleted: Int
        val database = dbHelper?.writableDatabase
        val match = matcher.match(uri)

        when (match) {
            CODE_TO_WASEDA -> {
                numOfRowsDeleted = database?.delete(DataContract.DB_TO_WASEDA().TABLE_NAME,
                        selection, selectionArgs) as Int
            }

            CODE_TO_NISHI -> {
                numOfRowsDeleted = database?.delete(DataContract.DB_TO_NISHI().TABLE_NAME,
                        selection, selectionArgs) as Int
            }
            CODE_SAT_TO_WASEDA -> {
                numOfRowsDeleted = database?.delete(DataContract.DB_TO_WASEDA().TABLE_NAME,
                        selection, selectionArgs) as Int
            }

            CODE_SAT_TO_NISHI -> {
                numOfRowsDeleted = database?.delete(DataContract.SATURDAY_DB_TO_NISHI().TABLE_NAME, selection, selectionArgs) as Int
            }

            else -> throw IllegalArgumentException("Delete method cannot handle " +
                    "unsupported URI: " + uri)
        }

        if (numOfRowsDeleted < 0)
            throw IllegalArgumentException("Content Provider (delete method) gave an error. " +
                    "Number of deleted row was 0 or less.")

        context!!.contentResolver.notifyChange(uri, null)
        return numOfRowsDeleted
    }



    override fun getType(p0: Uri?): String {
        //not needed as of now
        return "Not Implemented in the provider"
    }
}