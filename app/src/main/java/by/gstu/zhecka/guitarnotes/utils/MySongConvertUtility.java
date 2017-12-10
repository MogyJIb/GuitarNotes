package by.gstu.zhecka.guitarnotes.utils;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.UUID;

import by.gstu.zhecka.guitarnotes.model.Song;
import by.gstu.zhecka.guitarnotes.model.SongDetail;

import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.COLUMN_AUTHOR;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.COLUMN_DETAIL;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.COLUMN_NAME;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.COLUMN_TEXT;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.COLUMN_UUID;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.INDEX_SONG_AUTHOR;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.INDEX_SONG_DETAIL;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.INDEX_SONG_NAME;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.INDEX_SONG_TEXT;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.INDEX_SONG_UUID;

/**
 * Created by Zhecka on 8/25/2017.
 */

public class MySongConvertUtility {
    public static Song getSongFromCursor(Cursor cursor){
        if(cursor == null)
            return null;

        cursor.moveToFirst();

        UUID id = UUID.fromString(cursor.getString(INDEX_SONG_UUID));
        String name = cursor.getString(INDEX_SONG_NAME);
        String author = cursor.getString(INDEX_SONG_AUTHOR);
        String text = cursor.getString(INDEX_SONG_TEXT);
        SongDetail songDetail = SongDetail.deserialize(cursor.getBlob(INDEX_SONG_DETAIL));
        return new Song(id,name,author,text,songDetail);
    }

    /* Creates a single ContentValues object with random data */
    public static ContentValues createOneItemDataToContentValues(Song song) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, song.getName());
        contentValues.put(COLUMN_UUID, song.getId().toString());
        contentValues.put(COLUMN_AUTHOR,song.getAuthor());
        contentValues.put(COLUMN_TEXT,song.getText());
        contentValues.put(COLUMN_DETAIL,SongDetail.serialize(song.getSongDetail()));
        return contentValues;
    }




}