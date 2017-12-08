package by.gstu.zhecka.guitarnotes.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.database.SongContract;
import by.gstu.zhecka.guitarnotes.fragment.SongListFragment;

import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.SELECTION;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.SELECTION_ARGS;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.SELECTION_NAME_AND_AUTHOR;

/**
 * Created by Zhecka on 8/23/2017.
 */


public final class GuitarActivity extends AppCompatActivity {
    private SongListFragment mSongListFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guitar);


        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.guitar_activity);


        if (fragment == null) {
            mSongListFragment = new SongListFragment();
            fm.beginTransaction()
                    .add(R.id.guitar_activity, mSongListFragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.guitar_activity_menu,menu);

        configureSeachView(menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return false;
    }

    private void configureSeachView(Menu menu){
        // Associate searchable configuration with the SearchView
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();

        //set up the query listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Bundle bundle = new Bundle();
                bundle.putStringArray(SELECTION_ARGS,new String[]{"%"+query+"%"});
                mSongListFragment.reload(bundle);

                Toast.makeText(getApplicationContext(), "Seach for:  "+query, Toast.LENGTH_LONG).show();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String query) {
                //do nothing in our case
                return true;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                mSongListFragment.reload(null);
                return false;
            }
        });
    }

}
