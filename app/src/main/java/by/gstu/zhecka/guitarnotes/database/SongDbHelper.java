package by.gstu.zhecka.guitarnotes.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static by.gstu.zhecka.guitarnotes.database.SongContract.AccountEntry;
import static by.gstu.zhecka.guitarnotes.database.SongContract.AuthorEntry;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry;

/**
 * Created by Zhecka on 8/23/2017.
 */

public final class SongDbHelper extends SQLiteOpenHelper {


    /* Name and version of our database */
    public static final String DATABASE_NAME = "songsDB.db";
    private static final int DATABASE_VERSION = 13;


    /* Create tasks table (careful to follow SQL formatting rules) */
    private static final String SQL_CREATE_SONGS_TABLE =
            "CREATE TABLE " + SongEntry.TABLE_NAME         + " ("                                   +
                    SongEntry.COLUMN_UUID     + " STRING NOT NULL , "                  +
                    SongEntry.COLUMN_NAME   + " STRING NOT NULL, "                   +
                    SongEntry.COLUMN_AUTHOR_ID   + " STRING NOT NULL, "                   +
                    SongEntry.COLUMN_TEXT   + " STRING NOT NULL, "                   +
                    SongEntry.COLUMN_DETAIL  + " BLOB NOT NULL, "                   +
                    " CONSTRAINT " +_ID +" PRIMARY KEY ("+SongEntry.COLUMN_NAME+")"+
                    " UNIQUE (" +SongEntry.COLUMN_NAME+ ") ON CONFLICT REPLACE);"              ;

    private static final String SQL_CREATE_ACCOUNTS_TABLE =
            "CREATE TABLE " + AccountEntry.TABLE_NAME         + " ("                                   +
                    AccountEntry.COLUMN_UUID     + " STRING NOT NULL , "                  +
                    AccountEntry.COLUMN_NAME   + " STRING NOT NULL, "                   +
                    AccountEntry.COLUMN_LOGIN   + " STRING NOT NULL, "                   +
                    AccountEntry.COLUMN_PASSWORD   + " STRING NOT NULL, "                   +
                    " CONSTRAINT " +_ID +" PRIMARY KEY ("+AccountEntry.COLUMN_LOGIN+")"+
                    " UNIQUE (" +AccountEntry.COLUMN_LOGIN+ ") ON CONFLICT REPLACE);"              ;

    private static final String SQL_CREATE_AUTHORS_TABLE =
            "CREATE TABLE " + AuthorEntry.TABLE_NAME         + " ("                                   +
                    AuthorEntry.COLUMN_UUID     + " STRING NOT NULL , "                  +
                    AuthorEntry.COLUMN_NAME   + " STRING NOT NULL, "                   +
                    AuthorEntry.COLUMN_COUNTRY   + " STRING NOT NULL, "                   +
                    AuthorEntry.COLUMN_DEBUT_DATE   + " STRING NOT NULL, "                   +
                    AuthorEntry.COLUMN_GROUP_MEMBERS   + " STRING NOT NULL, "                   +
                    " CONSTRAINT " +_ID +" PRIMARY KEY ("+AuthorEntry.COLUMN_NAME+")"+
                    " UNIQUE (" +AuthorEntry.COLUMN_NAME+ ") ON CONFLICT REPLACE);"              ;


    /* Create string name for onUpgrade method (careful to follow SQL formatting rules) */
    private static final String SQL_DELETE_SONG_ENTRIES =
            "DROP TABLE IF EXISTS " + SongEntry.TABLE_NAME;
    private static final String SQL_DELETE_ACCOUNT_ENTRIES =
            "DROP TABLE IF EXISTS " + AccountEntry.TABLE_NAME;
    private static final String SQL_DELETE_AUTHOR_ENTRIES =
            "DROP TABLE IF EXISTS " + AuthorEntry.TABLE_NAME;

    /* Constructor for our class */
    public SongDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /* Called when the tasks database is created for the first time. */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_SONGS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_ACCOUNTS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_AUTHORS_TABLE);
    }


    /* This method discards the old table of data and calls onCreate to recreate a new one.
    This only occurs when the version number for this database (DATABASE_VERSION)
    is incremented. */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(SQL_DELETE_ACCOUNT_ENTRIES);
        sqLiteDatabase.execSQL(SQL_DELETE_AUTHOR_ENTRIES);
        sqLiteDatabase.execSQL(SQL_DELETE_SONG_ENTRIES);
        onCreate(sqLiteDatabase);
    }
}
