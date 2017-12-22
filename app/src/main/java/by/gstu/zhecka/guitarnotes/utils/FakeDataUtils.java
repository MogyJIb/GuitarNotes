package by.gstu.zhecka.guitarnotes.utils;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import by.gstu.zhecka.guitarnotes.database.SongContract;
import by.gstu.zhecka.guitarnotes.model.Account;
import by.gstu.zhecka.guitarnotes.model.Author;
import by.gstu.zhecka.guitarnotes.model.Song;

import static by.gstu.zhecka.guitarnotes.database.SongContract.AccountEntry;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry;


public class FakeDataUtils {




    public static void insertFakeData(Context context){

        ArrayList<String> authorsId = insertAuthors(context);


        insertSongs(context,authorsId);
        insertAccounts(context);
    }

    private static void insertSongs(Context context,ArrayList<String> authorsId){
        /* Creates a list of ContentValues objects with fake data for the provided */
        List<ContentValues> fakeValues = new ArrayList<ContentValues>();
        ArrayList<Song> songs = new ArrayList<>();

        songs.add(new Song("Numb",authorsId.get(0),"I’m tired of being what you want me to be\n" +
                "Feeling so faithless, lost under the surface\n" +
                "I don’t know what you’re expecting of me Put under the pressure of walking in your shoes\n" +
                "Caught in the undertow, just caught in the undertow\n" +
                "Every step that I take is another mistake to you\n" +
                "Caught in the undertow, just caught in the undertow\n" +
                "I’ve become so numb, I can’t feel you there\n" +
                "Become so tired, so much more aware\n" +
                "By becoming this all I want to do Is be more like me and be less like you\n" +
                "Can’t you see that you’re smothering me?\n" +
                "Holding too tightly, afraid to lose control\n" +
                "'Cause everything that you thought I would be Has fallen apart right in front of you\n" +
                "Caught in the undertow, just caught in the undertow\n" +
                "Every step that I take is another mistake to you\n" +
                "Caught in the undertow, just caught in the undertow\n" +
                "And every second I waste is more than I can take!\n" +
                "I’ve become so numb, I can’t feel you there\n" +
                "Become so tired, so much more aware\n" +
                "By becoming this all I want to do Is be more like me and be less like you\n" +
                "And I know I may end up failing too\n" +
                "But I know you were just like me with someone disappointed in you\n" +
                "I’ve become so numb, I can’t feel you there\n" +
                "Become so tired, so much more aware\n" +
                "By becoming this all I want to do Is be more like me and be less like you\n" +
                "I’ve become so numb, I can’t feel you there\n" +
                "I’m tired of being what you want me to be\n" +
                "I’ve become so numb, I can’t feel you there\n" +
                "I’m tired of being what you want me to be"));

        songs.add(new Song("Meds",authorsId.get(1),"I was alone, Falling free,\n" +
                "Trying my best not to forget\n" +
                "What happened to us,\n" +
                "What happened to me,\n" +
                "What happened as I let it slip.\n" +
                "\n" +
                "I was confused by the powers that be,\n" +
                "Forgetting names and faces.\n" +
                "Passersby were looking at me\n" +
                "As if they could erase it\n" +
                "\n" +
                "Baby did you forget to take your meds?\n" +
                "Baby did you forget to take your meds?\n" +
                "\n" +
                "I was alone,\n" +
                "Staring over the ledge,\n" +
                "Trying my best not to forget\n" +
                "All manner of joy\n" +
                "All manner of glee\n" +
                "And our one heroic pledge\n" +
                "\n" +
                "How it mattered to us,\n" +
                "How it mattered to me,\n" +
                "And the consequences\n" +
                "\n" +
                "I was confused,\n" +
                "By the birds and the bees 1\n" +
                "Forgetting if I meant it\n" +
                "\n" +
                "Baby did you forget to take your meds?\n" +
                "Baby did you forget to take your meds?\n" +
                "Baby did you forget to take your meds?\n" +
                "Baby did you forget to take your meds?\n" +
                "\n" +
                "And the Sex and the drugs and the complications\n" +
                "And the Sex and the drugs and the complications\n" +
                "And the Sex and the drugs and the complications\n" +
                "And the Sex and the drugs and the complications\n" +
                "\n" +
                "Baby did you forget to take your meds?\n" +
                "Baby did you forget to take your meds?\n" +
                "Baby did you forget to take your meds?\n" +
                "Baby did you forget to take your meds?\n" +
                "\n" +
                "I was alone,\n" +
                "Falling free,\n" +
                "Trying my best not to forget\n" +
                "\n" +
                "Оригинал: https://en.lyrsense.com/placebo/meds\n" +
                "Copyright: https://lyrsense.com ©"));


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

    private static ArrayList<String> insertAuthors(Context context){
        ArrayList<String> authorsId = new ArrayList<>();

        /* Creates a list of ContentValues objects with fake data for the provided */
        List<ContentValues> fakeValues = new ArrayList<ContentValues>();
        ArrayList<Author> authors = new ArrayList<>();

        int i = 0;
        authors.add(new Author("Linkin Park","2000",
                "USA",
                "Mike Shinoda, Brad Delson, David Farrell, Rob Bourdon, Joe Khan, Chester Bennington"));
        authorsId.add(authors.get(i).getName());
        i++;
        authors.add(new Author("Placebo","1994",
                "Britain",
                "Brian Molko , Stefan Olsdal, Bill Lloyd, Nick Gavrilovich, Matt Lunn, Angela Chan, Steve Hewitt "));
        authorsId.add(authors.get(i).getName());
        i++;


        for(; i<30; i++){
            authors.add(new Author("AuthorName "+i,"Author "+i,"some country","one"));
            authorsId.add(authors.get(i).getName());
        }

        for( i=0; i<authors.size(); i++){
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
