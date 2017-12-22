package by.gstu.zhecka.guitarnotes.database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Zhecka on 8/23/2017.
 */

public final class SongContract {

    public static final String URI_TAG = "uri";
    /* The authority, which is how your code knows which Content Provider to access */
    public static final String CONTENT_AUTHORITY = "by.gstu.zhecka.guitarnotes";


    /* The base content URI = "content://" + <authority> */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);


    /* Define the possible paths for accessing data in this contract. This is the path for
    the "songs" directory */
    public static final String PATH_SONGS = "songs";

    public static final String PATH_SONG_LISTS = "song_lists";

    public static final String PATH_PLAYLISTS = "playlists";

    public static final String PATH_ACCOUNTS = "accounts";

    public static final String PATH_AUTHORS = "authors";

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
        public static final String COLUMN_AUTHOR_ID = "author_id";
        public static final String COLUMN_TEXT = "text";
        public static final String COLUMN_DETAIL = "detail";





        /* The columns of data that we are interested in displaying within our MainActivity's list of
    weather data. */
        public static final String[] MAIN_SONGS_PROJECTION = {
                COLUMN_UUID,
                COLUMN_NAME,
                COLUMN_AUTHOR_ID
        };

        public static final String[] DETAIL_SONGS_PROJECTION = {
                COLUMN_UUID,
                COLUMN_NAME,
                COLUMN_AUTHOR_ID,
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

        public static final String SELECTION_NAME = COLUMN_NAME + " LIKE ? ";
        public static final String SELECTION_AUTHOR = COLUMN_AUTHOR_ID + "=?";
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
    public static final class AuthorEntry
            implements BaseColumns {

        /* TaskEntry content URI = base content URI + path */
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_AUTHORS)
                .build();


        /* Task table and column names */
        public static final String TABLE_NAME = "authors";
        public static final String COLUMN_UUID = "uuid";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DEBUT_DATE = "debutdate";
        public static final String COLUMN_COUNTRY = "country";
        public static final String COLUMN_GROUP_MEMBERS = "group_members";


        /* The columns of data that we are interested in displaying within our MainActivity's list of
    weather data. */
        public static final String[] MAIN_AUTHORS_PROJECTION = {
                COLUMN_UUID,
                COLUMN_NAME,
                COLUMN_DEBUT_DATE,
                COLUMN_COUNTRY,
                COLUMN_GROUP_MEMBERS
        };


        /* We store the indices of the values in the array of Strings above to more quickly be able to
        access the data from our query. If the order of the Strings above changes, these indices
        must be adjusted to match the order of the Strings. */
        public static final int INDEX_AUTHOR_UUID = 0;
        public static final int INDEX_AUTHOR_NAME = 1;
        public static final int INDEX_AUTHOR_DEBUT_DATE = 2;
        public static final int INDEX_AUTHOR_COUNTRY = 3;
        public static final int INDEX_AUTHOR_GROUP_MEMBERS = 4;

        public static final String SORT_ODER_BY_NAME = "name";

        public static final String SELECTION_NAME = COLUMN_NAME + " LIKE ? ";
        public static final String SELECTION_UUID = COLUMN_UUID + "=?";

        public static final String UUID_TAG = "uuid";
        public static final String AUTHOR_TAG = "author";
    }

    /* TaskEntry is an inner class that defines the contents of the task table */
    public static final class AccountEntry
            implements BaseColumns {

        /* TaskEntry content URI = base content URI + path */
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_ACCOUNTS)
                .build();


        /* Task table and column names */
        public static final String TABLE_NAME = "accounts";
        public static final String COLUMN_UUID = "uuid";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_LOGIN = "login";
        public static final String COLUMN_PASSWORD = "password";


        /* The columns of data that we are interested in displaying within our MainActivity's list of
    weather data. */
        public static final String[] MAIN_ACCOUNT_PROJECTION = {
                COLUMN_UUID,
                COLUMN_NAME,
                COLUMN_LOGIN,
                COLUMN_PASSWORD
        };

        public static final String[] LOGIN_ACCOUNT_PROJECTION = {
                COLUMN_UUID,
                COLUMN_PASSWORD
        };


        /* We store the indices of the values in the array of Strings above to more quickly be able to
        access the data from our query. If the order of the Strings above changes, these indices
        must be adjusted to match the order of the Strings. */
        public static final int INDEX_ACCOUNT_UUID = 0;
        public static final int INDEX_ACCOUNT_NAME = 1;
        public static final int INDEX_ACCOUNT_LOGIN = 2;
        public static final int INDEX_ACCOUNT_PASSWORD = 3;

        public static final String SORT_ODER_BY_UUID = "uuid";

        public static final String SELECTION_LOGIN = COLUMN_LOGIN + "=?";
        public static final String SELECTION_UUID = COLUMN_UUID + "=?";


        public static final String ACCOUNT_TAG = "account";
        public static final String UUID_TAG = "uuid";

    }

    /* TaskEntry is an inner class that defines the contents of the task table */
    public static final class PlaylistEntry
            implements BaseColumns {

        /* TaskEntry content URI = base content URI + path */
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_PLAYLISTS)
                .build();


        /* Task table and column names */
        public static final String TABLE_NAME = "playlists";
        public static final String COLUMN_UUID = "uuid";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ACCOUNT_ID = "account_id";


        /* The columns of data that we are interested in displaying within our MainActivity's list of
    weather data. */
        public static final String[] MAIN_PLAYLIST_PROJECTION = {
                COLUMN_UUID,
                COLUMN_NAME,
                COLUMN_ACCOUNT_ID
        };

        /* We store the indices of the values in the array of Strings above to more quickly be able to
        access the data from our query. If the order of the Strings above changes, these indices
        must be adjusted to match the order of the Strings. */
        public static final int INDEX_PLAYLIST_UUID = 0;
        public static final int INDEX_PLAYLIST_NAME = 1;
        public static final int INDEX_PLAYLIST_ACCOUNT_ID = 2;

        public static final String SORT_ODER_BY_UUID = "uuid";

        public static final String SELECTION_UUID = COLUMN_UUID + "=?";


        public static final String PLAYLIST_TAG = "playlist";
        public static final String UUID_TAG = "uuid";

    }
    /* TaskEntry is an inner class that defines the contents of the task table */

    public static final class SongListEntry
            implements BaseColumns {

        /* TaskEntry content URI = base content URI + path */
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_SONG_LISTS)
                .build();


        /* Task table and column names */
        public static final String TABLE_NAME = "song_lists";
        public static final String COLUMN_UUID = "uuid";
        public static final String COLUMN_PLAYLIST_ID = "playlist_id";
        public static final String COLUMN_SONG_ID = "song_id";


        /* The columns of data that we are interested in displaying within our MainActivity's list of
    weather data. */
        public static final String[] MAIN_SONG_LIST_PROJECTION = {
                COLUMN_UUID,
                COLUMN_PLAYLIST_ID,
                COLUMN_SONG_ID
        };

        /* We store the indices of the values in the array of Strings above to more quickly be able to
        access the data from our query. If the order of the Strings above changes, these indices
        must be adjusted to match the order of the Strings. */
        public static final int INDEX_SONG_LIST_UUID = 0;
        public static final int INDEX_SONG_LIST_PLAYLIST_ID = 1;
        public static final int INDEX_SONG_LIST_SONG_ID = 2;

        public static final String SORT_ODER_BY_UUID = "uuid";

        public static final String SELECTION_UUID = COLUMN_UUID + "=?";


        public static final String SONG_LIST_TAG = "song_lists";
        public static final String UUID_TAG = "uuid";

    }
}

