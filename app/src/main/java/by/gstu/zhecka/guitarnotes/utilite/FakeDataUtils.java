package by.gstu.zhecka.guitarnotes.utilite;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import by.gstu.zhecka.guitarnotes.model.Song;

import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.CONTENT_URI;


public class FakeDataUtils {




    public static void insertFakeData(Context context){

        /* Creates a list of ContentValues objects with fake data for the provided */
        List<ContentValues> fakeValues = new ArrayList<ContentValues>();
        ArrayList<Song> songs = new ArrayList<>();

        for(int i=0; i<100; i++){
            songs.add(new Song("Name "+i,"Author "+i,"text"));
        }

        for(int i=0; i<songs.size(); i++){
            fakeValues.add(MySongConvertUtility.createOneItemDataToContentValues(songs.get(i)));
        }


        /* Bulk Insert our new weather data into our Database */
        context.getContentResolver().bulkInsert(
                CONTENT_URI,
                fakeValues.toArray(new ContentValues[fakeValues.size()]));
    }
}
