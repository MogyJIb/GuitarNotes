package by.gstu.zhecka.guitarnotes.utils;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.UUID;

import by.gstu.zhecka.guitarnotes.model.Account;
import by.gstu.zhecka.guitarnotes.model.Song;
import by.gstu.zhecka.guitarnotes.model.SongDetail;

import static by.gstu.zhecka.guitarnotes.database.SongContract.AccountEntry;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry;

/**
 * Created by Zhecka on 8/25/2017.
 */

public class MySongConvertUtility {
    public static Song getSongFromCursor(Cursor cursor){
        if(cursor == null)
            return null;

        cursor.moveToFirst();

        UUID id = UUID.fromString(cursor.getString(SongEntry.INDEX_SONG_UUID));
        String name = cursor.getString(SongEntry.INDEX_SONG_NAME);
        String author = cursor.getString(SongEntry.INDEX_SONG_AUTHOR);
        String text = cursor.getString(SongEntry.INDEX_SONG_TEXT);
        SongDetail songDetail = SongDetail.deserialize(cursor.getBlob(SongEntry.INDEX_SONG_DETAIL));
        return new Song(id,name,author,text,songDetail);
    }

    /* Creates a single ContentValues object with random data */
    public static ContentValues getContentValuesFromSong(Song song) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SongEntry.COLUMN_NAME, song.getName());
        contentValues.put(SongEntry.COLUMN_UUID, song.getId().toString());
        contentValues.put(SongEntry.COLUMN_AUTHOR,song.getAuthor());
        contentValues.put(SongEntry.COLUMN_TEXT,song.getText());
        contentValues.put(SongEntry.COLUMN_DETAIL,SongDetail.serialize(song.getSongDetail()));
        return contentValues;
    }

    public static Account getAccountFromCursor(Cursor cursor){
        if(cursor == null)
            return null;

        cursor.moveToFirst();

        UUID id = UUID.fromString(cursor.getString(AccountEntry.INDEX_ACCOUNT_UUID));
        String name = cursor.getString(AccountEntry.INDEX_ACCOUNT_NAME);
        String login = cursor.getString(AccountEntry.INDEX_ACCOUNT_LOGIN);
        String password = cursor.getString(AccountEntry.INDEX_ACCOUNT_PASSWORD);
        return new Account(id,name,login,password);
    }

    /* Creates a single ContentValues object with random data */
    public static ContentValues getContentValuesFromAccount(Account account) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AccountEntry.COLUMN_NAME, account.getName());
        contentValues.put(AccountEntry.COLUMN_UUID, account.getId().toString());
        contentValues.put(AccountEntry.COLUMN_LOGIN,account.getLogin());
        contentValues.put(AccountEntry.COLUMN_PASSWORD,account.getPassword());
        return contentValues;
    }




}
