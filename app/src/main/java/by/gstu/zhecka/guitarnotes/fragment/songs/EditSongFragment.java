package by.gstu.zhecka.guitarnotes.fragment.songs;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.model.Song;
import by.gstu.zhecka.guitarnotes.utils.MySongConvertUtility;

import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.CONTENT_URI;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.SELECTION_UUID;

/**
 * Created by Zhecka on 09.12.2017.
 */

public class EditSongFragment extends AbstractDetailSongFragment {
    private FloatingActionButton mSaveSongActionButton;

    public static EditSongFragment newInstance(Song song) {
        EditSongFragment fragment = new EditSongFragment();
        setFragmentArgs(fragment,song,true);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);

        mSaveSongActionButton = (FloatingActionButton) view.findViewById(R.id.fb_save_song);
        mSaveSongActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Song song = getSong();

                if(song!=null) {
                    ContentValues contentValues = MySongConvertUtility.
                            getContentValues(song);

                    String[] selectionArgs = {song.getId().toString()};
                    int songUpdated = getContext().getContentResolver().update(CONTENT_URI,contentValues, SELECTION_UUID,selectionArgs);

                    if(songUpdated == 0)
                        getContext().getContentResolver().insert(CONTENT_URI, contentValues);

                    Toast.makeText(view.getContext(), "The operation was complete successfully!", Toast.LENGTH_LONG).show();

                    getFragmentManager().popBackStack();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_activity_container, DetailSongFragment.newInstance(mSong))
                            .commit();
                }
            }
        });


        return view;
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_edit_song;
    }
}
