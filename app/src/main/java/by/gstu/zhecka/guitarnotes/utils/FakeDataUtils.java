package by.gstu.zhecka.guitarnotes.utils;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import by.gstu.zhecka.guitarnotes.database.SongContract;
import by.gstu.zhecka.guitarnotes.model.Account;
import by.gstu.zhecka.guitarnotes.model.Author;
import by.gstu.zhecka.guitarnotes.model.Song;

import static by.gstu.zhecka.guitarnotes.database.SongContract.AccountEntry;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry;


public class FakeDataUtils {




    public static void insertFakeData(Context context){

        ArrayList<UUID> authorsId = insertAuthors(context);


        insertSongs(context,authorsId);
        insertAccounts(context);
    }

    private static void insertSongs(Context context,ArrayList<UUID> authorsId){
        /* Creates a list of ContentValues objects with fake data for the provided */
        List<ContentValues> fakeValues = new ArrayList<ContentValues>();
        ArrayList<Song> songs = new ArrayList<>();
        Random random = new Random();
        for(int i=0; i<100; i++){
            songs.add(new Song("Name "+i,authorsId.get(random.nextInt(authorsId.size())),"text"));
        }

        for(int i=0; i<songs.size(); i++){
            fakeValues.add(MySongConvertUtility.getContentValues(songs.get(i)));
        }


        /* Bulk Insert our new weather data into our Database */
        context.getContentResolver().bulkInsert(
                SongEntry.CONTENT_URI,
                fakeValues.toArray(new ContentValues[fakeValues.size()]));
    }

    private static ArrayList<UUID> insertAuthors(Context context){
        ArrayList<UUID> authorsId = new ArrayList<>();

        /* Creates a list of ContentValues objects with fake data for the provided */
        List<ContentValues> fakeValues = new ArrayList<ContentValues>();
        ArrayList<Author> authors = new ArrayList<>();

        for(int i=0; i<30; i++){
            authors.add(new Author("AuthorName "+i,"Author "+i,"some country","one"));
            authorsId.add(authors.get(i).getId());
        }

        for(int i=0; i<authors.size(); i++){
            fakeValues.add(MySongConvertUtility.getContentValues(authors.get(i)));
        }


        /* Bulk Insert our new weather data into our Database */
        context.getContentResolver().bulkInsert(
                SongContract.AuthorEntry.CONTENT_URI,
                fakeValues.toArray(new ContentValues[fakeValues.size()]));

        return authorsId;
    }

    private static void insertAccounts(Context context){
        /* Creates a list of ContentValues objects with fake data for the provided */
        List<ContentValues> fakeValues = new ArrayList<ContentValues>();
        ArrayList<Account> accounts = new ArrayList<>();


        accounts.add(new Account("VItalya","vitpolt@mail.ru","doN0Tsay"));
        accounts.add(new Account("Merli","ocarSll@tut.by","PsPs"));
        accounts.add(new Account("Unknown","user@us.er","password"));


        for(int i=0; i<accounts.size(); i++){
            fakeValues.add(MySongConvertUtility.getContentValues(accounts.get(i)));
        }


        /* Bulk Insert our new weather data into our Database */
        context.getContentResolver().bulkInsert(
                AccountEntry.CONTENT_URI,
                fakeValues.toArray(new ContentValues[fakeValues.size()]));
    }
}
