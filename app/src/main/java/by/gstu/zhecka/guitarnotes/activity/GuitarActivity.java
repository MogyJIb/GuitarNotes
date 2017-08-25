package by.gstu.zhecka.guitarnotes.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.fragment.SongListFragment;

/**
 * Created by Zhecka on 8/23/2017.
 */

public final class GuitarActivity extends FragmentActivity{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guitar);


        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.guitar_activity);


        if (fragment == null) {
            fragment = new SongListFragment();
            fm.beginTransaction()
                    .add(R.id.guitar_activity, fragment)
                    .commit();
        }
    }
}
