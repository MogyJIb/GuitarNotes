package by.gstu.zhecka.guitarnotes.database;

/**
 * Created by Zhecka on 8/23/2017.
 */

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;


public final class SongProvider
        extends ContentProvider {

    /* These constant will be used to match URIs with the data they are looking for. We will take
    advantage of the UriMatcher class to make that matching MUCH easier than doing something
    ourselves, such as using regular expressions. */
    public static final int CODE_SONGS = 100;
    public static final int CODE_ACCOUNTS = 101;
    public static final int CODE_AUTHORS = 102;


    /* The URI Matcher used by this content provider. */
    private static final UriMatcher sUriMatcher = buildUriMatcher();


    /* Member variable for a SongReaderDbHelper that's initialized in the onCreate() method */
    private SongDbHelper mDbHelper;


    /* Define a static buildUriMatcher method that associates URI's with their int match */
    public static UriMatcher buildUriMatcher() {

        /* All paths added to the UriMatcher have a corresponding code to return when a match is
         found. The code passed into the constructor of UriMatcher here represents the code to
         return for the root URI. It's common to use NO_MATCH as the code for this case. */
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = SongContract.CONTENT_AUTHORITY;


        /* For each type of URI you want to add, create a corresponding code. Preferably, these are
         constant fields in your class so that you can use them throughout the class and you no
         they aren't going to change. Hear, we use CODE_SONGS or CODE_SONGS_WITH_DATE. */

        /* This URI is content://by.application.android.zhecka.guitarmysongs/songs/ */
        matcher.addURI(authority, SongContract.PATH_SONGS, CODE_SONGS);


        /* This URI is content://by.application.android.zhecka.guitarmysongs/accounts/ */
        matcher.addURI(authority, SongContract.PATH_ACCOUNTS, CODE_ACCOUNTS);

        /* This URI is content://by.application.android.zhecka.guitarmysongs/authors/ */
        matcher.addURI(authority, SongContract.PATH_AUTHORS, CODE_AUTHORS);


        /* This URI would look something like content://by.application.android.zhecka.guitarmysongs/songs/1472214172
         The "/#" signifies to the UriMatcher that if PATH_SONGS is followed by ANY number,
         that it should return the CODE_SONGS_WITH_DATE code */
       // matcher.addURI(authority, SongContract.PATH_SONGS + "/#", CODE_SONGS_WITH_DATE);

        return matcher;
    }


    /* In onCreate, we initialize our content provider on startup. This method is called for all
    registered content providers on the application main thread at application launch time. */
    @Override
    public boolean onCreate() {

        /* As noted in the comment above, onCreate is run on the main thread, so performing any
         lengthy operations will cause lag in your app. Since SongsReaderDbHelper's constructor is
         very lightweight, we are safe to perform that initialization here. */
        mDbHelper = new SongDbHelper(getContext());
        return true;
    }


    /* Handles requests to insert a set of new rows. */
    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        String tableName ="";
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)) {
            case CODE_SONGS:
                tableName = SongContract.SongEntry.TABLE_NAME;
               break;
            case CODE_ACCOUNTS:
                tableName = SongContract.AccountEntry.TABLE_NAME;
                break;
            case CODE_AUTHORS:
                tableName = SongContract.AuthorEntry.TABLE_NAME;
                break;
            default:
                return super.bulkInsert(uri, values);
        }



        db.beginTransaction();
        int rowsInserted = 0;
        try {
            for (ContentValues value : values) {
                long _id = db.insert(tableName, null, value);
                if (_id != -1) {
                    rowsInserted++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        if (rowsInserted > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsInserted;

    }


    /* Implement insert to handle requests to insert a single new row of data */
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {

        String tableName ="";

        /* Get access to the task database (to write new data to) */
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Uri returnUri;


        /* Write URI matching code to identify the match for the tasks directory */
        switch (sUriMatcher.match(uri)) {
            case CODE_SONGS:
                tableName = SongContract.SongEntry.TABLE_NAME;
                break;
            case CODE_ACCOUNTS:
                tableName = SongContract.AccountEntry.TABLE_NAME;
                break;
            case CODE_AUTHORS:
                tableName = SongContract.AuthorEntry.TABLE_NAME;
                break;
                /* Default case throws an UnsupportedOperationException */
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        db.beginTransaction();


                /* Inserting values into tasks table */
        long id = 0;
        try {
            id = db.insert(tableName, null, values);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        if (id > 0) {
            returnUri = ContentUris.withAppendedId(SongContract.SongEntry.CONTENT_URI, id);


                    /* Notify the resolver if the uri has been changed, and return the newly inserted URI */
            getContext().getContentResolver().notifyChange(uri, null);
        } else {
            throw new android.database.SQLException("Failed to insert row into " + uri);
        }

        /* Return constructed uri (this points to the newly inserted row of data) */
        return returnUri;
    }


    /* Handles query requests from clients. */
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        String tableName ="";


        /* Get access to underlying database (read-only for query) */
        final SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor retCursor;


        /* Query for the tasks directory and write a default case */
        switch (sUriMatcher.match(uri)){
            case CODE_SONGS:
                tableName = SongContract.SongEntry.TABLE_NAME;
                break;
            case CODE_ACCOUNTS:
                tableName = SongContract.AccountEntry.TABLE_NAME;
                break;
            case CODE_AUTHORS:
                tableName = SongContract.AuthorEntry.TABLE_NAME;
            break;

            /* Default exception */
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        retCursor = db.query(tableName,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        /* Set a notification URI on the Cursor and return that Cursor */
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }


    /* This method handles requests for the MIME type of the data at the
    given URI. For example, if your app provided images at a particular URI, then you would
    return an image URI from this method. */
    @Override
    public String getType(@NonNull Uri uri) {
        throw new RuntimeException("Not implementing getType!");
    }


    /* Deletes data at a given URI with optional arguments for more fine tuned deletions. */
    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {

        String tableName ="";


        /* Get access to the database and write URI matching code to recognize a single item */
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();


        /* Keep track of the number of deleted tasks */
        int songsDeleted;


        /* [Hint] Use selections to delete an item by its row ID */
        switch (sUriMatcher.match(uri)) {

            /* Handle the single item case, recognized by the ID included in the URI path */
            case CODE_SONGS:
                tableName = SongContract.SongEntry.TABLE_NAME;
                break;
            case CODE_ACCOUNTS:
                tableName = SongContract.AccountEntry.TABLE_NAME;
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

                /* Use selections/selectionArgs to filter for this ID */
        songsDeleted = db.delete(tableName, selection, selectionArgs);

        if (songsDeleted != 0) {


            /* A song was deleted, set notification */
            getContext().getContentResolver().notifyChange(uri, null);
        }


        /* Return the number of songs deleted */
        return songsDeleted;
    }


    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        String tableName ="";

        final SQLiteDatabase db = mDbHelper.getWritableDatabase();


        /* Keep track of the number of deleted tasks */
        int songsUpdated;


        /* [Hint] Use selections to delete an item by its row ID */
        switch (sUriMatcher.match(uri)) {

            /* Handle the single item case, recognized by the ID included in the URI path */
            case CODE_SONGS:
                tableName = SongContract.SongEntry.TABLE_NAME;
                break;
            case CODE_ACCOUNTS:
                tableName = SongContract.AccountEntry.TABLE_NAME;
                break;
            case CODE_AUTHORS:
                tableName = SongContract.AuthorEntry.TABLE_NAME;
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

         /* Use selections/selectionArgs to filter for this ID */
        songsUpdated = db.update(tableName,values,selection,selectionArgs);
        if (songsUpdated != 0) {


            /* A song was deleted, set notification */
            getContext().getContentResolver().notifyChange(uri, null);
        }


        /* Return the number of songs deleted */
        return songsUpdated;
    }


    /* This is a method specifically to assist the testing framework in running smoothly. */
    @Override
    public void shutdown() {
        mDbHelper.close();
        super.shutdown();
    }
}
