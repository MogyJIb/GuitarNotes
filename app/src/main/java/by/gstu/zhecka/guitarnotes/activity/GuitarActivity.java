package by.gstu.zhecka.guitarnotes.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.database.SongContract;
import by.gstu.zhecka.guitarnotes.fragment.GuitarFragment;

import static by.gstu.zhecka.guitarnotes.database.SongContract.SELECTION;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SELECTION_ARGS;

/**
 * Created by Zhecka on 8/23/2017.
 */


public final class GuitarActivity extends MainActivity {
    private GuitarFragment mGuitarFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.main_activity_container);


        if (fragment == null) {
            mGuitarFragment = new GuitarFragment();
            fm.beginTransaction()
                    .add(R.id.main_activity_container, mGuitarFragment)
                    .commit();
        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.search_menu,menu);

        configureSeachView(menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return false;
    }

    private void configureSeachView(Menu menu){
        // Associate searchable configuration with the SearchView
        final SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();

        //set up the query listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Bundle bundle = new Bundle();
                bundle.putStringArray(SELECTION_ARGS,new String[]{"%"+query+"%"});
                bundle.putString(SELECTION, SongContract.SongEntry.SELECTION_NAME);

                mGuitarFragment.getSelected().reload(bundle);

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
                mGuitarFragment.getSelected().reload(null);
                return false;
            }
        });
    }

}
