package by.gstu.zhecka.guitarnotes.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.model.Song;

import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.SONG_TAG;


/**
 * Created by Zhecka on 8/25/2017.
 */

public class DetailSongFragment extends Fragment {

    public final static String IS_FOCUSEBLE_TAG = "isFocusable";

    private Song mSong;

    private TextView mSongNameTv;
    private TextView mSongAuthorTv;
    private TextView mSongTextTv;

    private boolean mIsFocusable;

    public static DetailSongFragment newInstance(Song song, boolean isFocusable) {
        Bundle args = new Bundle();
        args.putSerializable(SONG_TAG,song);
        args.putBoolean(IS_FOCUSEBLE_TAG,isFocusable);
        DetailSongFragment fragment = new DetailSongFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle!=null) {
            mSong = (Song) getArguments().getSerializable(SONG_TAG);
            mIsFocusable = getArguments().getBoolean(IS_FOCUSEBLE_TAG);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_song, container, false);

        initializeTheViews(view);

        updateTheFields();

        setFocusable(mIsFocusable);

        return view;
    }


    private void initializeTheViews(View parentView){

        mSongNameTv = (TextView) parentView.findViewById(R.id.tv_song_name);
        mSongAuthorTv = (TextView) parentView.findViewById(R.id.tv_song_author);
        mSongTextTv = (TextView) parentView.findViewById(R.id.tv_song_text);
    }

    private void updateTheFields(){
        if(mSong==null)
            return;

        mSongTextTv.setText(mSong.getText()+"\n"+
                                mSong.getId());
        mSongNameTv.setText(mSong.getName());
        mSongAuthorTv.setText(mSong.getAuthor());
    }

    private boolean updateTheSongInform() {
        if (isTheFieldValid()) {

            if(mSong == null)
                mSong = new Song();

            mSong.setName(mSongNameTv.getText().toString());
            mSong.setAuthor(mSongAuthorTv.getText().toString());
            mSong.setText(mSongTextTv.getText().toString());


            return true;
        } else {
            Toast.makeText(getActivity(), "Invalid input!\n" +
                    "( Please, check the inputting information and retype. )", Toast.LENGTH_LONG).show();

            return false;
        }
    }

    public Song getSong() {
        if(updateTheSongInform())
            return mSong;
        else return null;
    }

    public boolean isTheFieldValid(){
        if(mSongNameTv.getText()== null || mSongNameTv.getText().toString().isEmpty())
            return false;
        if(mSongTextTv.getText()== null || mSongTextTv.getText().toString().isEmpty())
            return false;
        if(mSongAuthorTv.getText()== null || mSongAuthorTv.getText().toString().isEmpty())
            return false;

        return true;
    }

    public void setFocusable(boolean isFocusable){
        if(mSong== null)
            return;

        mSongNameTv.setFocusable(isFocusable);
        mSongTextTv.setFocusable(isFocusable);
        mSongAuthorTv.setFocusable(isFocusable);
    }
}
