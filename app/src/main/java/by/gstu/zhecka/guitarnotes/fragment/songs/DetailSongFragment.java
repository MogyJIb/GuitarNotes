package by.gstu.zhecka.guitarnotes.fragment.songs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.fragment.DeleteDialogFragment;
import by.gstu.zhecka.guitarnotes.model.Song;


/**
 * Created by Zhecka on 8/25/2017.
 */

public class DetailSongFragment extends AbstractDetailSongFragment {
    private FloatingActionButton mDeleteSongActionButton;
    private FloatingActionButton mEditSongActionButton;

    public static DetailSongFragment newInstance(Song song) {
        DetailSongFragment fragment = new DetailSongFragment();
        setFragmentArgs(fragment,song,false);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);

        mDeleteSongActionButton = (FloatingActionButton) view.findViewById(R.id.fb_delete_song);
        mDeleteSongActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mSong!=null) {
                    DialogFragment dialog = DeleteDialogFragment.newInstance(mSong.getId());
                    dialog.show(getFragmentManager(),"DeleteDialogFragment");
                }
            }
        });

        mEditSongActionButton = (FloatingActionButton) view.findViewById(R.id.fb_edit_song);
        mEditSongActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_activity_container, EditSongFragment.newInstance(mSong))
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_detail_song;
    }
}
