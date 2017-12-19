package by.gstu.zhecka.guitarnotes.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.fragment.LogInFragment;


/**
 * Created by Zhecka on 09.12.2017.
 */

public class LogInActivity extends MainActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.main_activity_container);


        if (fragment == null) {
            fragment = new LogInFragment();
            fm.beginTransaction()
                    .add(R.id.main_activity_container, fragment)
                    .commit();
        }
    }



}
