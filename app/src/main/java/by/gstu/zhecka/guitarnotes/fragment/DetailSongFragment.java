package by.gstu.zhecka.guitarnotes.fragment;

import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.keyboard.StrumKeyboard;
import by.gstu.zhecka.guitarnotes.model.Song;

import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.SONG_TAG;


/**
 * Created by Zhecka on 8/25/2017.
 */

public class DetailSongFragment extends Fragment {

    public final static String IS_FOCUSABLE_TAG = "isFocusable";
    private boolean mIsFocusable;

    private Song mSong;

    private EditText mSongNameTv;
    private EditText mSongAuthorTv;
    private EditText mSongTextTv;

    private EditText mSongChordTv;
    private EditText mSongStrumTv;

    private StrumKeyboard mStrumKeyboard;




    public static DetailSongFragment newInstance(Song song, boolean isFocusable) {
        Bundle args = new Bundle();
        args.putSerializable(SONG_TAG,song);
        args.putBoolean(IS_FOCUSABLE_TAG,isFocusable);
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
            mIsFocusable = getArguments().getBoolean(IS_FOCUSABLE_TAG);
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
        mSongNameTv = (EditText) parentView.findViewById(R.id.tv_song_name);
        mSongAuthorTv = (EditText) parentView.findViewById(R.id.tv_song_author);
        mSongTextTv = (EditText) parentView.findViewById(R.id.tv_song_text);
        mSongChordTv = (EditText) parentView.findViewById(R.id.tv_song_chord);
        mSongStrumTv = (EditText) parentView.findViewById(R.id.tv_song_strum);

        mStrumKeyboard = (StrumKeyboard)parentView.findViewById(R.id.strum_keyboard);
        mStrumKeyboard.setEditText(mSongStrumTv,getActivity());

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
