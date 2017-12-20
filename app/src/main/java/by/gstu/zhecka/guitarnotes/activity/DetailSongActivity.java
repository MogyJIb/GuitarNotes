package by.gstu.zhecka.guitarnotes.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.fragment.songs.DetailSongFragment;
import by.gstu.zhecka.guitarnotes.fragment.songs.EditSongFragment;
import by.gstu.zhecka.guitarnotes.model.Song;

import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.SONG_TAG;

/**
 * Created by Zhecka on 8/27/2017.
 */

public class DetailSongActivity extends MainActivity {

    private Song mSong;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.main_activity_container);


        if (fragment == null) {
            mSong = (Song) getIntent().getSerializableExtra(SONG_TAG);
            if(mSong != null)
                fragment = (DetailSongFragment) DetailSongFragment.newInstance(mSong);
            else fragment = (EditSongFragment) EditSongFragment.newInstance(mSong);
            fm.beginTransaction().add(R.id.main_activity_container, fragment).commit();
        }

    }

}
