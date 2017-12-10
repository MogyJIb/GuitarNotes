package by.gstu.zhecka.guitarnotes.database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Zhecka on 8/23/2017.
 */

public final class SongContract {

    /* The authority, which is how your code knows which Content Provider to access */
    public static final String CONTENT_AUTHORITY = "by.gstu.zhecka.guitarnotes";


    /* The base content URI = "content://" + <authority> */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);


    /* Define the possible paths for accessing data in this contract. This is the path for
    the "songs" directory */
    public static final String PATH_SONGS = "songs";

    public static final String PATH_ACCOUNTS = "accounts";

    public static final String SELECTION_ARGS = "selectionArgs";
    public static final String SELECTION = "selection";


    /* TaskEntry is an inner class that defines the contents of the task table */
    public static final class SongEntry
            implements BaseColumns {

        /* TaskEntry content URI = base content URI + path */
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_SONGS)
                .build();


        /* Task table and column names */
        public static final String TABLE_NAME = "songs";
        public static final String COLUMN_UUID = "uuid";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AUTHOR = "author";
        public static final String COLUMN_TEXT = "text";
        public static final String COLUMN_DETAIL = "detail";





        /* The columns of data that we are interested in displaying within our MainActivity's list of
    weather data. */
        public static final String[] MAIN_SONGS_PROJECTION = {
                COLUMN_UUID,
                COLUMN_NAME,
                COLUMN_AUTHOR
        };

        public static final String[] DETAIL_SONGS_PROJECTION = {
                COLUMN_UUID,
                COLUMN_NAME,
                COLUMN_AUTHOR,
                COLUMN_TEXT,
                COLUMN_DETAIL
        };

        /* We store the indices of the values in the array of Strings above to more quickly be able to
        access the data from our query. If the order of the Strings above changes, these indices
        must be adjusted to match the order of the Strings. */
        public static final int INDEX_SONG_UUID = 0;
        public static final int INDEX_SONG_NAME = 1;
        public static final int INDEX_SONG_AUTHOR = 2;
        public static final int INDEX_SONG_TEXT = 3;
        public static final int INDEX_SONG_DETAIL = 4;

        public static final String SORT_ODER_BY_NAME = "name";
        public static final String SORT_ODER_BY_AUTHOR = "author";

        public static final String SELECTION_NAME_AND_AUTHOR = COLUMN_NAME + " LIKE ? OR "+COLUMN_AUTHOR+" LIKE ? ";
        public static final String SELECTION_UUID = COLUMN_UUID + "=?";

        public static final String SONG_TAG = "song";
        public static final String UUID_TAG = "uuid";

        /*

        The above table structure looks something like the sample table below.
        With the name of the table and columns on top, and potential contents in rows

        Note: Because this implements BaseColumns, the _id column is generated automatically

        tasks
         - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        | _id  |         name         |         author         |  songs_id  |            text           |
         - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        |  1   |        "Meds"        |       "Placebo"        |     1      | "I was alone, falling...  |
         - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        |  2   |    "Linkin Park"     |         "Numb"         |     2      |  "I'm tired of being...   |
         - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
             ...                                                 ...
             ...                                                 ...
             ...                                                 ...
         - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        |  45  |   "System of Down"   |       "Lonely day"     |     45     |   "Such a lonely day...   |
         - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

         */
    }

    /* TaskEntry is an inner class that defines the contents of the task table */
    public static final class AccountEntry
            implements BaseColumns {

        /* TaskEntry content URI = base content URI + path */
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_ACCOUNTS)
                .build();


        /* Task table and column names */
        public static final String TABLE_NAME = "account";
        public static final String COLUMN_UUID = "uuid";
        public static final String COLUMN_LOGIN = "login";
        public static final String COLUMN_PASSWORD = "password";


        /* The columns of data that we are interested in displaying within our MainActivity's list of
    weather data. */
        public static final String[] MAIN_ACCOUNT_PROJECTION = {
                COLUMN_UUID,
                COLUMN_LOGIN,
                COLUMN_PASSWORD
        };


        /* We store the indices of the values in the array of Strings above to more quickly be able to
        access the data from our query. If the order of the Strings above changes, these indices
        must be adjusted to match the order of the Strings. */
        public static final int INDEX_ACCOUNT_UUID = 0;
        public static final int INDEX_ACCOUNT_LOGIN = 1;
        public static final int INDEX_ACCOUNT_PASSWORD = 2;

        public static final String SORT_ODER_BY_UUID = "uuid";

        public static final String SELECTION_UUID = COLUMN_UUID + "=?";


        public static final String ACCOUNT_TAG = "account";
        public static final String UUID_TAG = "uuid";

    }
}

