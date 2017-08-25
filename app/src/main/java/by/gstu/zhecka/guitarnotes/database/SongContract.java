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
}

