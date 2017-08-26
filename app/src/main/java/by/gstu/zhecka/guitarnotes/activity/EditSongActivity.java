package by.gstu.zhecka.guitarnotes.activity;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Toast;

import java.util.UUID;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.database.SongContract;
import by.gstu.zhecka.guitarnotes.fragment.DetailSongFragment;
import by.gstu.zhecka.guitarnotes.model.Song;
import by.gstu.zhecka.guitarnotes.utilite.MyConvertUtility;

import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.COLUMN_UUID;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.SONG_TAG;

/**
 * Created by Zhecka on 8/26/2017.
 */

public class EditSongActivity extends FragmentActivity {

    private Song mSong;
    private DetailSongFragment mDetailSongFragment;

    private FloatingActionButton mSaveSongActionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_song);

        mSaveSongActionButton = (FloatingActionButton) findViewById(R.id.fb_save_song);
        mSaveSongActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Song song = mDetailSongFragment.getSong();

                if(song!=null) {
                    ContentValues contentValues = MyConvertUtility.
                            createOneItemDataToContentValues(song);

                    getContentResolver().insert(SongContract.SongEntry.CONTENT_URI, contentValues);

                    Toast.makeText(view.getContext(), "The operation was complete successfully!", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.edit_song_activity);


        if (fragment == null) {

            mSong = (Song) getIntent().getSerializableExtra(SONG_TAG);
            fragment = DetailSongFragment.newInstance(mSong);
            mDetailSongFragment = (DetailSongFragment) fragment;

            fm.beginTransaction()
                    .add(R.id.edit_song_activity, fragment)
                    .commit();
        }
    }


}
