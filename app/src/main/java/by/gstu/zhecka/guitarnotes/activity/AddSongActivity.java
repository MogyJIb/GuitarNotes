package by.gstu.zhecka.guitarnotes.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.fragment.DetailSongFragment;

/**
 * Created by Zhecka on 8/26/2017.
 */
public class AddSongActivity extends FragmentActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.add_song_activity);


        if (fragment == null) {
            fragment = new DetailSongFragment();
            fm.beginTransaction()
                    .add(R.id.add_song_activity, fragment)
                    .commit();
        }
    }


}
