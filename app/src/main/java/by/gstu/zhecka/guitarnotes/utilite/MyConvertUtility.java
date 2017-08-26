package by.gstu.zhecka.guitarnotes.utilite;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.UUID;

import by.gstu.zhecka.guitarnotes.model.Song;

import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.COLUMN_AUTHOR;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.COLUMN_NAME;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.COLUMN_TEXT;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.COLUMN_UUID;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.INDEX_SONG_AUTHOR;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.INDEX_SONG_NAME;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.INDEX_SONG_TEXT;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.INDEX_SONG_UUID;

/**
 * Created by Zhecka on 8/25/2017.
 */

public class MyConvertUtility {
    public static Song getSongFromCursor(Cursor cursor){
        if(cursor == null)
            return null;

        cursor.moveToFirst();

        UUID id = UUID.fromString(cursor.getString(INDEX_SONG_UUID));
        String name = cursor.getString(INDEX_SONG_NAME);
        String author = cursor.getString(INDEX_SONG_AUTHOR);
        String text = cursor.getString(INDEX_SONG_TEXT);
        return new Song(id,name,author,text);
    }

    /* Creates a single ContentValues object with random data */
    public static ContentValues createOneItemDataToContentValues(Song song) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, song.getName());
        contentValues.put(COLUMN_UUID, song.getId().toString());
        contentValues.put(COLUMN_AUTHOR,song.getAuthor());
        contentValues.put(COLUMN_TEXT,song.getText());

        return contentValues;
    }
}
