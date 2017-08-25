package by.gstu.zhecka.guitarnotes.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.UUID;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.fragment.DetailSongFragment;
import by.gstu.zhecka.guitarnotes.fragment.SongListFragment;

import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.COLUMN_UUID;

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
            UUID songId = UUID.fromString(uuidInString);
            fragment = DetailSongFragment.newInstance(songId);
            fm.beginTransaction()
                    .add(R.id.detail_song_activity, fragment)
                    .commit();
        }
    }
}
