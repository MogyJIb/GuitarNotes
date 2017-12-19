package by.gstu.zhecka.guitarnotes.utils;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import by.gstu.zhecka.guitarnotes.model.Account;
import by.gstu.zhecka.guitarnotes.model.Song;

import static by.gstu.zhecka.guitarnotes.database.SongContract.AccountEntry;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry;


public class FakeDataUtils {




    public static void insertFakeData(Context context){
        insertSongs(context);
        insertAccounts(context);
    }

    private static void insertSongs(Context context){
        /* Creates a list of ContentValues objects with fake data for the provided */
        List<ContentValues> fakeValues = new ArrayList<ContentValues>();
        ArrayList<Song> songs = new ArrayList<>();

        for(int i=0; i<100; i++){
            songs.add(new Song("Name "+i,"Author "+i,"text"));
        }

        for(int i=0; i<songs.size(); i++){
            fakeValues.add(MySongConvertUtility.getContentValuesFromSong(songs.get(i)));
        }


        /* Bulk Insert our new weather data into our Database */
        context.getContentResolver().bulkInsert(
                SongEntry.CONTENT_URI,
                fakeValues.toArray(new ContentValues[fakeValues.size()]));
    }

    private static void insertAccounts(Context context){
        /* Creates a list of ContentValues objects with fake data for the provided */
        List<ContentValues> fakeValues = new ArrayList<ContentValues>();
        ArrayList<Account> accounts = new ArrayList<>();


        accounts.add(new Account("VItalya","vitpolt@mail.ru","doN0Tsay"));
        accounts.add(new Account("Merli","ocarSll@tut.by","PsPs"));
        accounts.add(new Account("Unknown","user@us.er","password"));


        for(int i=0; i<accounts.size(); i++){
            fakeValues.add(MySongConvertUtility.getContentValuesFromAccount(accounts.get(i)));
        }


        /* Bulk Insert our new weather data into our Database */
        context.getContentResolver().bulkInsert(
                AccountEntry.CONTENT_URI,
                fakeValues.toArray(new ContentValues[fakeValues.size()]));
    }
}
