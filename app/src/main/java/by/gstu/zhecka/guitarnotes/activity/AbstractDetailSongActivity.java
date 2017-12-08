package by.gstu.zhecka.guitarnotes.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import by.gstu.zhecka.guitarnotes.fragment.DetailSongFragment;
import by.gstu.zhecka.guitarnotes.model.Song;

import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.SONG_TAG;

/**
 * Created by Zhecka on 8/27/2017.
 */

public abstract class AbstractDetailSongActivity extends AppCompatActivity {

    protected DetailSongFragment mDetailSongFragment;
    protected Song mSong;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(getContainLayoutId());


        if (fragment == null) {
            mSong = (Song) getIntent().getSerializableExtra(SONG_TAG);
            fragment = DetailSongFragment.newInstance(mSong, isFocusable());
            mDetailSongFragment = (DetailSongFragment) fragment;
            

            fm.beginTransaction()
                    .add(getContainLayoutId(), fragment)
                    .commit();
        }

    }


    protected abstract int getContainLayoutId();
    protected abstract boolean isFocusable();
}
