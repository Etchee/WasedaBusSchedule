package etchee.com.wasedabusschedule.Data

import android.content.ContentResolver
import android.net.Uri
import android.provider.BaseColumns

/**
 * Class to contain all the constants
 * Created by rikutoechigoya on 2017/05/19.
 */

class DataContract private constructor() {


    data class GlobalConstants(
            val PASS_STRING:String = "PASS_STRING",

            //universal declarations
            val CONTENT_AUTHORITY: String = "etchee.com.wasedabusschedule",
            val BASE_CONTENT_URI: Uri = Uri.parse("content://" + CONTENT_AUTHORITY),
            val DATABASE_NAME: String = "schedule.db",

            //Specific URI addresses
            val PATH_TO_WASEDA: String = "TO_WASEDA",
            val PATH_TO_NISHI: String = "TO_NISHI",
            val PATH_SAT_TO_WASEDA: String = "SAT_TO_WASEDA",
            val PATH_SAT_TO_NISHI: String = "SAT_TO_NISHI",

            val FLAG_1: String = "西早稲田キャンパス経由",
            val FLAG_2: String = "Twins経由",
            val FLAG_3: String = "学生会館経由と直行便の運行",
            val FLAG_4: String = "早稲田キャンパス経由",
            val FLAG_5: String = "Twins経由",
            val FLAG_6: String = "学生会館経由と直行便の運行",
            val FLAG_7: String = "折り返し後すぐ運行。時間誤差あり",
            val FLAG_8: String = "西早稲田キャンパス経由、 \\n　学生会館経由と直行便の運行 "
    )

    data class DB_TO_WASEDA(

            val CONTENT_URI: Uri = GlobalConstants().BASE_CONTENT_URI.buildUpon()
                    .appendPath(GlobalConstants().PATH_TO_WASEDA).build(),

            val CONTENT_ITEM_TYPE: String = ContentResolver.CURSOR_ITEM_BASE_TYPE +
                    "/" + GlobalConstants().CONTENT_AUTHORITY +
                    "/" + GlobalConstants().PATH_TO_WASEDA,

            val TABLE_NAME: String = "TABLE_TO_WASEDA",
            val _ID: String = BaseColumns._ID,
            val COLUMN_HOUR: String = "COLUMN_HOUR",
            val COLUMN_MIN:String = "COLUMN_MIN",
            val COLUMN_FLAG:String = "COLUMN_FLAG"
    )

    data class DB_TO_NISHI(

            val CONTENT_URI: Uri = GlobalConstants().BASE_CONTENT_URI.buildUpon()
                    .appendPath(GlobalConstants().PATH_TO_NISHI).build(),

            val CONTENT_ITEM_TYPE: String = ContentResolver.CURSOR_ITEM_BASE_TYPE +
                    "/" + GlobalConstants().CONTENT_AUTHORITY +
                    "/" + GlobalConstants().PATH_TO_NISHI,

            val TABLE_NAME: String = "TABLE_TO_NISHI",
            val _ID: String = BaseColumns._ID,
            val COLUMN_HOUR: String = "COLUMN_HOUR",
            val COLUMN_MIN:String = "COLUMN_MIN",
            val COLUMN_FLAG:String = "COLUMN_FLAG"
    )

    data class SATURDAY_DB_TO_WASEDA(
            val CONTENT_URI: Uri = GlobalConstants().BASE_CONTENT_URI.buildUpon()
                    .appendPath(GlobalConstants().PATH_SAT_TO_WASEDA).build(),

            val CONTENT_ITEM_TYPE: String = ContentResolver.CURSOR_ITEM_BASE_TYPE +
                    "/" + GlobalConstants().CONTENT_AUTHORITY +
                    "/" + GlobalConstants().PATH_SAT_TO_WASEDA,

            val TABLE_NAME: String = "TABLE_TO_NISHI",
            val _ID: String = BaseColumns._ID,
            val COLUMN_HOUR: String = "COLUMN_HOUR",
            val COLUMN_MIN:String = "COLUMN_MIN",
            val COLUMN_FLAG:String = "COLUMN_FLAG"
    )

    data class SATURDAY_DB_TO_NISHI(
            val CONTENT_URI: Uri? = GlobalConstants().BASE_CONTENT_URI.buildUpon()
                    .appendPath(GlobalConstants().PATH_SAT_TO_NISHI).build(),

            val CONTENT_ITEM_TYPE: String = ContentResolver.CURSOR_ITEM_BASE_TYPE +
                    "/" + GlobalConstants().CONTENT_AUTHORITY +
                    "/" + GlobalConstants().PATH_SAT_TO_NISHI,

            val TABLE_NAME: String = "TABLE_TO_NISHI",
            val _ID: String = BaseColumns._ID,
            val COLUMN_HOUR: String = "COLUMN_HOUR",
            val COLUMN_MIN:String = "COLUMN_MIN",
            val COLUMN_FLAG:String = "COLUMN_FLAG"
    )
}
