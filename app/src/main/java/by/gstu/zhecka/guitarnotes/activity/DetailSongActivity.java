package by.gstu.zhecka.guitarnotes.activity;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.fragment.DeleteDialogFragment;

import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.SONG_TAG;

/**
 * Created by Zhecka on 8/25/2017.
 */

public class DetailSongActivity extends AbstractDetailSongActivity {
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
                Intent intent = new Intent(view.getContext(),EditSongActivity.class);
                intent.putExtra(SONG_TAG,mSong);
                view.getContext().startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected int getContainLayoutId() {
        return R.id.detail_song_activity;
    }

    @Override
    protected boolean isFocusable() {
        return false;
    }
}
