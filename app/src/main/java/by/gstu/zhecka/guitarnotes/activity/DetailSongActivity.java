package by.gstu.zhecka.guitarnotes.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.UUID;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.fragment.DetailSongFragment;
import by.gstu.zhecka.guitarnotes.fragment.SongListFragment;
import by.gstu.zhecka.guitarnotes.model.Song;
import by.gstu.zhecka.guitarnotes.utilite.MyConvertUtility;

import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.COLUMN_UUID;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.CONTENT_URI;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.DETAIL_SONGS_PROJECTION;

/**
 * Created by Zhecka on 8/25/2017.
 */

public class DetailSongActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_song);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.detail_song_activity);


        if (fragment == null) {
            String uuidInString = getIntent().getStringExtra(COLUMN_UUID);

            UUID songId = null;
            if(uuidInString!=null){
                songId = UUID.fromString(uuidInString);
            }

            fragment = DetailSongFragment.newInstance(getSongFromDatabase(songId));

            fm.beginTransaction()
                    .add(R.id.detail_song_activity, fragment)
                    .commit();
        }
    }

    private Song getSongFromDatabase(UUID songId) {
        if(songId == null)
            return null;

        String selection = COLUMN_UUID + "=?";
        String[] selectionArgs = {songId.toString()};
        Cursor cursor = getContentResolver()
                .query(CONTENT_URI, DETAIL_SONGS_PROJECTION, selection, selectionArgs, null);


        return MyConvertUtility.getSongFromCursor(cursor);
    }
}
