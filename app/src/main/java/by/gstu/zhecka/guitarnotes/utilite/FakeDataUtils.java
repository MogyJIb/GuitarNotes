package by.gstu.zhecka.guitarnotes.utilite;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import by.gstu.zhecka.guitarnotes.model.Song;

import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.COLUMN_AUTHOR;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.COLUMN_NAME;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.COLUMN_TEXT;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.COLUMN_UUID;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.CONTENT_URI;


public class FakeDataUtils {

    /* Creates a single ContentValues object with random data */
    public static ContentValues createOneItemDataToContentValues(Song song) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, song.getName());
        contentValues.put(COLUMN_UUID, song.getId().toString());
        contentValues.put(COLUMN_AUTHOR,song.getAuthor());
        contentValues.put(COLUMN_TEXT,song.getText());

        return contentValues;
    }


    public static void insertFakeData(Context context){

        /* Creates a list of ContentValues objects with fake data for the provided */
        List<ContentValues> fakeValues = new ArrayList<ContentValues>();
        ArrayList<Song> songs = new ArrayList<>();

        for(int i=0; i<50; i++){
            songs.add(new Song("Name "+i,"Author "+i,"text"));
        }

        for(int i=0; i<songs.size(); i++){
            fakeValues.add(createOneItemDataToContentValues(songs.get(i)));
        }


        /* Bulk Insert our new weather data into our Database */
        context.getContentResolver().bulkInsert(
                CONTENT_URI,
                fakeValues.toArray(new ContentValues[fakeValues.size()]));
    }
}
