package by.gstu.zhecka.guitarnotes.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static by.gstu.zhecka.guitarnotes.database.SongContract.*;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.COLUMN_AUTHOR;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.COLUMN_DETAIL;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.COLUMN_NAME;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.COLUMN_TEXT;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.COLUMN_UUID;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.TABLE_NAME;

/**
 * Created by Zhecka on 8/23/2017.
 */

public final class SongDbHelper extends SQLiteOpenHelper {


    /* Name and version of our database */
    public static final String DATABASE_NAME = "songsDB.db";
    private static final int DATABASE_VERSION = 7;


    /* Create tasks table (careful to follow SQL formatting rules) */
    private static final String SQL_CREATE_SONGS_TABLE =
            "CREATE TABLE " + TABLE_NAME         + " ("                                   +
                    COLUMN_UUID     + " STRING NOT NULL , "                  +
                    COLUMN_NAME   + " STRING NOT NULL, "                   +
                    COLUMN_AUTHOR   + " STRING NOT NULL, "                   +
                    COLUMN_TEXT   + " STRING NOT NULL, "                   +
                    COLUMN_DETAIL  + " BLOB NOT NULL, "                   +
                    " CONSTRAINT " +_ID +" PRIMARY KEY ("+COLUMN_NAME+"," +COLUMN_AUTHOR+")"+
                    " UNIQUE (" +COLUMN_NAME+"," +COLUMN_AUTHOR+ ") ON CONFLICT REPLACE);"              ;

    /* Create string name for onUpgrade method (careful to follow SQL formatting rules) */
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    /* Constructor for our class */
    public SongDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /* Called when the tasks database is created for the first time. */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_SONGS_TABLE);
    }


    /* This method discards the old table of data and calls onCreate to recreate a new one.
    This only occurs when the version number for this database (DATABASE_VERSION)
    is incremented. */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
}
