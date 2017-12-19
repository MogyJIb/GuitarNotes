package by.gstu.zhecka.guitarnotes.fragment;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.activity.MainActivity;
import by.gstu.zhecka.guitarnotes.model.Account;
import by.gstu.zhecka.guitarnotes.utils.MySongConvertUtility;

import static by.gstu.zhecka.guitarnotes.database.SongContract.AccountEntry;


/**
 * Created by Zhecka on 8/25/2017.
 */

public class RegistrationFragment extends Fragment {

    private FloatingActionButton mSaveAccountActionButton;

    private Account mAccount;

    private EditText mNameEt;
    private EditText mLoginEt;
    private EditText mPasswordEt;
    private EditText mConfirmPasswordEt;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutID(), container, false);

        initializeTheViews(view);

        return view;
    }


    private void initializeTheViews(View parentView){
        mNameEt = (EditText) parentView.findViewById(R.id.et_user_name);
        mLoginEt = (EditText) parentView.findViewById(R.id.et_user_login);
        mPasswordEt = (EditText) parentView.findViewById(R.id.et_user_password);
        mConfirmPasswordEt = (EditText) parentView.findViewById(R.id.et_user_confirm_password);

        mSaveAccountActionButton = (FloatingActionButton) parentView.findViewById(R.id.fb_save_account);
        mSaveAccountActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account account = getAccount();

                if(account!=null) {
                    ContentValues contentValues = MySongConvertUtility.
                            getContentValuesFromAccount(account);

                    String[] selectionArgs = {account.getId().toString()};
                    int accountUpdated = getContext().getContentResolver().update(AccountEntry.CONTENT_URI,contentValues, AccountEntry.SELECTION_UUID,selectionArgs);

                    if(accountUpdated == 0)
                        getContext().getContentResolver().insert(AccountEntry.CONTENT_URI, contentValues);
                    MainActivity.ACCOUNT = account;
                    Toast.makeText(view.getContext(), "The operation was complete successfully!", Toast.LENGTH_LONG).show();
                    getActivity().finish();
                }
            }
        });
    }

    private boolean updateTheAccountInform() {
        if (isTheFieldValid()) {

            if(mAccount == null)
                mAccount = new Account();

            mAccount.setName(mNameEt.getText().toString());
            mAccount.setLogin(mLoginEt.getText().toString());
            mAccount.setPassword(mPasswordEt.getText().toString());
            return true;
        } else {
            Toast.makeText(getActivity(), "Invalid input!\n" +
                    "( Please, check the inputting information and retype. )", Toast.LENGTH_LONG).show();

            return false;
        }
    }

    public Account getAccount() {
        if(updateTheAccountInform())
            return mAccount;
        else return null;
    }

    public boolean isTheFieldValid(){
        if(mNameEt.getText()== null || mNameEt.getText().toString().isEmpty())
            return false;
        if(mLoginEt.getText()== null || mLoginEt.getText().toString().isEmpty())
            return false;
        if(mPasswordEt.getText()== null || mPasswordEt.getText().toString().isEmpty())
            return false;
        if(mConfirmPasswordEt.getText()== null || mConfirmPasswordEt.getText().toString().isEmpty())
            return false;
        if(!mPasswordEt.getText().toString().equals(mConfirmPasswordEt.getText().toString()))
            return false;
        return true;
    }

    protected int getLayoutID(){
        return R.layout.fragment_registration;
    }
}
