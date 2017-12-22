package by.gstu.zhecka.guitarnotes.utils;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.UUID;

import by.gstu.zhecka.guitarnotes.model.Account;
import by.gstu.zhecka.guitarnotes.model.Author;
import by.gstu.zhecka.guitarnotes.model.Song;
import by.gstu.zhecka.guitarnotes.model.SongDetail;

import static by.gstu.zhecka.guitarnotes.database.SongContract.AccountEntry;
import static by.gstu.zhecka.guitarnotes.database.SongContract.AuthorEntry;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry;

/**
 * Created by Zhecka on 8/25/2017.
 */

public class MySongConvertUtility {
    public static Song getSongFromCursor(Cursor cursorSong,Cursor cursorAuthor){
        if(cursorSong == null)
            return null;

        cursorSong.moveToFirst();

        UUID id = UUID.fromString(cursorSong.getString(SongEntry.INDEX_SONG_UUID));
        String name = cursorSong.getString(SongEntry.INDEX_SONG_NAME);
        String authorId = cursorSong.getString(SongEntry.INDEX_SONG_AUTHOR);
        String text = cursorSong.getString(SongEntry.INDEX_SONG_TEXT);
        SongDetail songDetail = SongDetail.deserialize(cursorSong.getBlob(SongEntry.INDEX_SONG_DETAIL));

        Song song = new Song(id,name,authorId,text,songDetail);
        Author author = getAuthorFromCursor(cursorAuthor);
        song.setAuthor(author);
        return song;
    }

    /* Creates a single ContentValues object with random data */
    public static ContentValues getContentValues(Song song) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SongEntry.COLUMN_NAME, song.getName());
        contentValues.put(SongEntry.COLUMN_UUID, song.getId().toString());
        contentValues.put(SongEntry.COLUMN_AUTHOR_ID,song.getAuthorId().toString());
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
    public static ContentValues getContentValues(Account account) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AccountEntry.COLUMN_NAME, account.getName());
        contentValues.put(AccountEntry.COLUMN_UUID, account.getId().toString());
        contentValues.put(AccountEntry.COLUMN_LOGIN,account.getLogin());
        contentValues.put(AccountEntry.COLUMN_PASSWORD,account.getPassword());

        return contentValues;
    }


    public static Author getAuthorFromCursor(Cursor cursor){
        if(cursor == null)
            return null;

        cursor.moveToFirst();
        UUID id = UUID.fromString(cursor.getString(AuthorEntry.INDEX_AUTHOR_UUID));
        String name = cursor.getString(AuthorEntry.INDEX_AUTHOR_NAME);
        String debutdate = cursor.getString(AuthorEntry.INDEX_AUTHOR_DEBUT_DATE);
        String country = cursor.getString(AuthorEntry.INDEX_AUTHOR_COUNTRY);
        String groupMembers = cursor.getString(AuthorEntry.INDEX_AUTHOR_GROUP_MEMBERS);
        return new Author(id,name,debutdate,country,groupMembers);
    }

    /* Creates a single ContentValues object with random data */
    public static ContentValues getContentValues(Author author) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AuthorEntry.COLUMN_NAME, author.getName());
        contentValues.put(AuthorEntry.COLUMN_UUID, author.getId().toString());
        contentValues.put(AuthorEntry.COLUMN_COUNTRY,author.getCountry());
        contentValues.put(AuthorEntry.COLUMN_DEBUT_DATE,author.getDebutdate());
        contentValues.put(AuthorEntry.COLUMN_GROUP_MEMBERS,author.getGroupMembers());

        return contentValues;
    }

}
