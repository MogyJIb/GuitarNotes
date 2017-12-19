package by.gstu.zhecka.guitarnotes.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.activity.MainActivity;
import by.gstu.zhecka.guitarnotes.model.Account;
import by.gstu.zhecka.guitarnotes.utils.MySongConvertUtility;

import static by.gstu.zhecka.guitarnotes.database.SongContract.AccountEntry;

/**
 * Created by Zhecka on 09.12.2017.
 */

public class LogInFragment extends Fragment {


    private Account mAccount;

    private EditText mLoginEditText;
    private EditText mPasswordEditText;
    private TextView mForgotPasswordTextView;
    private TextView mRegistrationTextView;

    private Button mSingInButton;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_authentication, container, false);

        initializeTheViews(view);

        return view;
    }

    private void initializeTheViews(View view){
        mLoginEditText = view.findViewById(R.id.et_user_login);
        mPasswordEditText = view.findViewById(R.id.et_user_password);

        mRegistrationTextView = view.findViewById(R.id.tw_registration);
        mRegistrationTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_activity_container,new RegistrationFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        mForgotPasswordTextView = view.findViewById(R.id.tw_forgotPassword);
        mForgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mSingInButton = view.findViewById(R.id.button_singin);
        mSingInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] selectionArg = {mLoginEditText.getText().toString()};
                Cursor cursor = getActivity().getContentResolver().
                        query(AccountEntry.CONTENT_URI,AccountEntry.MAIN_ACCOUNT_PROJECTION,AccountEntry.SELECTION_LOGIN,selectionArg,null);

                if(cursor.getCount() > 0){
                    cursor.moveToFirst();
                    Account account = MySongConvertUtility.getAccountFromCursor(cursor);

                    if(mPasswordEditText.getText().toString().equals(account.getPassword())) {
                        Toast.makeText(getActivity(), "You are login successfully!", Toast.LENGTH_LONG).show();

                        MainActivity.ACCOUNT = account;
                        getActivity().finish();
                    }
                    else
                        Toast.makeText(getActivity(), "Incorrect password!", Toast.LENGTH_LONG).show();

                }
                else
                    Toast.makeText(getActivity(), "User doesn't exist!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
