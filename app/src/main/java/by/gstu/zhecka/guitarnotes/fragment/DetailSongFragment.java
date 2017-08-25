package by.gstu.zhecka.guitarnotes.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.UUID;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.Utilite.MyConvertUtility;
import by.gstu.zhecka.guitarnotes.database.SongContract;
import by.gstu.zhecka.guitarnotes.model.Song;

import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.COLUMN_UUID;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.CONTENT_URI;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.DETAIL_SONGS_PROJECTION;


/**
 * Created by Zhecka on 8/25/2017.
 */

public class DetailSongFragment extends Fragment {


    private Song mSong;

    private TextView mSongNameTv;
    private TextView mSongAuthorTv;
    private TextView mSongTextTv;


    public static DetailSongFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(COLUMN_UUID,crimeId);
        DetailSongFragment fragment = new DetailSongFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null || !bundle.isEmpty()) {
            UUID songId = (UUID) getArguments().getSerializable(COLUMN_UUID);
            mSong = getSongFromDatabase(songId);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_song, container, false);

        mSongNameTv = (TextView) view.findViewById(R.id.tv_song_name);
        mSongAuthorTv= (TextView) view.findViewById(R.id.tv_song_author);
        mSongTextTv = (TextView) view.findViewById(R.id.tv_song_text);

        if(mSong != null){
            initializeTheFields();
        }




        return view;
    }



    private Song getSongFromDatabase(UUID songId) {
        String selection = COLUMN_UUID + "=?";
        String[] selectionArgs = {songId.toString()};
        Cursor cursor = getActivity().getContentResolver()
                .query(CONTENT_URI, DETAIL_SONGS_PROJECTION, selection, selectionArgs, null);


        return MyConvertUtility.getSongFromCursor(cursor);
    }

    private void initializeTheFields(){
        mSongTextTv.setText(mSong.getText());
        mSongNameTv.setText(mSong.getName());
        mSongAuthorTv.setText(mSong.getAuthor());
    }
}
