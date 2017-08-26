package by.gstu.zhecka.guitarnotes.activity;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Toast;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.model.Song;
import by.gstu.zhecka.guitarnotes.utilite.MyConvertUtility;

import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.CONTENT_URI;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.SELECTION_UUID;

/**
 * Created by Zhecka on 8/26/2017.
 */

public class EditSongActivity extends AbstractDetailSongActivity {

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

                    String[] selectionArgs = {song.getId().toString()};
                    int songUpdated = getContentResolver().update(CONTENT_URI,contentValues, SELECTION_UUID,selectionArgs);

                    if(songUpdated == 0)
                        getContentResolver().insert(CONTENT_URI, contentValues);

                    Toast.makeText(view.getContext(), "The operation was complete successfully!", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }

    @Override
    protected int getContainLayoutId() {
        return R.id.edit_song_activity;
    }

    @Override
    protected boolean isFocusable() {
        return true;
    }


}
