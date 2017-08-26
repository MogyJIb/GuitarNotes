package by.gstu.zhecka.guitarnotes.activity;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.fragment.DeleteDialogFragment;
import by.gstu.zhecka.guitarnotes.fragment.DetailSongFragment;
import by.gstu.zhecka.guitarnotes.model.Song;

import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.SONG_TAG;

/**
 * Created by Zhecka on 8/25/2017.
 */

public class DetailSongActivity extends FragmentActivity {

    private Song mSong;
    private FloatingActionButton mDeleteSongActionButton;
    private FloatingActionButton mEditSongActionButton;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_song);


        mDeleteSongActionButton = (FloatingActionButton) findViewById(R.id.fb_delete_song);
        mDeleteSongActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mSong!=null) {
                    DialogFragment dialog = DeleteDialogFragment.newInstance(mSong.getId());
                    dialog.show(getFragmentManager(),"DeleteDialogFragment");
                }
            }
        });

        mEditSongActionButton = (FloatingActionButton) findViewById(R.id.fb_edit_song);
        mEditSongActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.detail_song_activity);


        if (fragment == null) {
            mSong = (Song) getIntent().getSerializableExtra(SONG_TAG);
            fragment = DetailSongFragment.newInstance(mSong);

            fm.beginTransaction()
                    .add(R.id.detail_song_activity, fragment)
                    .commit();
        }
    }
}
