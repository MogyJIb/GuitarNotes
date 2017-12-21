package by.gstu.zhecka.guitarnotes.fragment.authors;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.model.Author;

import static by.gstu.zhecka.guitarnotes.database.SongContract.AuthorEntry.AUTHOR_TAG;

/**
 * Created by Zhecka on 21.12.2017.
 */

public abstract class AbstractDetailAuthorFragment extends Fragment{
    public final static String IS_FOCUSABLE_TAG = "isFocusable";


    private boolean mIsFocusable;

    protected Author mAuthor;

    private EditText mAuthorNameTv;
    private EditText mAuthorCountryTv;
    private EditText mAuthorGroupMembersTv;
    private EditText mAuthorDebutDateTv;


    protected static void setFragmentArgs(Fragment fragment, Author author, boolean isFocusable) {
        Bundle args = new Bundle();
        args.putSerializable(AUTHOR_TAG,author);
        args.putBoolean(IS_FOCUSABLE_TAG,isFocusable);
        fragment.setArguments(args);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle!=null) {
            mAuthor = (Author) getArguments().getSerializable(AUTHOR_TAG);
            mIsFocusable = getArguments().getBoolean(IS_FOCUSABLE_TAG);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutID(), container, false);

        initializeTheViews(view);

        updateTheFields();

        setFocusable(mIsFocusable);

        return view;
    }


    private void initializeTheViews(View parentView){
        mAuthorNameTv = (EditText) parentView.findViewById(R.id.tv_author_name);
        mAuthorCountryTv = (EditText) parentView.findViewById(R.id.tv_country);
        mAuthorGroupMembersTv = (EditText) parentView.findViewById(R.id.tv_group_members);
        mAuthorDebutDateTv = (EditText) parentView.findViewById(R.id.tv_date);
    }

    private void updateTheFields(){
        if(mAuthor==null)
            return;

        mAuthorNameTv.setText(mAuthor.getName());
        mAuthorCountryTv.setText(mAuthor.getCountry());
        mAuthorGroupMembersTv.setText(mAuthor.getGroupMembers());
        mAuthorDebutDateTv.setText(mAuthor.getDebutdate());
    }

    private boolean updateTheAuthorInform() {
        if (isTheFieldValid()) {

            if(mAuthor == null)
                mAuthor = new Author();

            mAuthor.setName(mAuthorNameTv.getText().toString());
            mAuthor.setCountry(mAuthorCountryTv.getText().toString());
            mAuthor.setDebutdate(mAuthorDebutDateTv.getText().toString());
            mAuthor.setGroupMembers(mAuthorGroupMembersTv.getText().toString());


            return true;
        } else {
            Toast.makeText(getActivity(), "Invalid input!\n" +
                    "( Please, check the inputting information and retype. )", Toast.LENGTH_LONG).show();

            return false;
        }
    }

    public Author getAuthor() {
        if(updateTheAuthorInform())
            return mAuthor;
        else return null;
    }

    public boolean isTheFieldValid(){
        if(mAuthorNameTv.getText()== null || mAuthorNameTv.getText().toString().isEmpty())
            return false;
        if(mAuthorCountryTv.getText()== null || mAuthorCountryTv.getText().toString().isEmpty())
            return false;
        if(mAuthorGroupMembersTv.getText()== null || mAuthorGroupMembersTv.getText().toString().isEmpty())
            return false;
        if(mAuthorDebutDateTv.getText()== null || mAuthorDebutDateTv.getText().toString().isEmpty())
            return false;

        return true;
    }

    public void setFocusable(boolean isFocusable){
        if(mAuthor == null)
            return;
        mAuthorNameTv.setFocusable(isFocusable);
        mAuthorCountryTv.setFocusable(isFocusable);
        mAuthorGroupMembersTv.setFocusable(isFocusable);
        mAuthorDebutDateTv.setFocusable(isFocusable);
    }

    public abstract int getLayoutID();
}
