package by.gstu.zhecka.guitarnotes.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.model.Account;

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

        mLoginEditText = view.findViewById(R.id.et_login);
        mPasswordEditText = view.findViewById(R.id.et_password);

        mRegistrationTextView = view.findViewById(R.id.tw_registration);
        mRegistrationTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                getActivity().finish();
            }
        });

        return view;
    }


}
