package by.gstu.zhecka.guitarnotes.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.utils.DrawerNavigationUtils;

import static by.gstu.zhecka.guitarnotes.database.SongContract.SELECTION_ARGS;

/**
 * Created by Zhecka on 8/23/2017.
 */


public class MainActivity extends AppCompatActivity {

    private Drawer.Result drawerResult = null;
    private AccountHeader.Result headerResult = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // init Drawer & Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        headerResult = DrawerNavigationUtils.getAccountHeader(this, savedInstanceState);
        drawerResult = DrawerNavigationUtils.createCommonDrawer(this, toolbar, headerResult);
        drawerResult.setSelectionByIdentifier(1, false); // Set proper selection

    }

    @Override
    public void onBackPressed() {
        if (drawerResult.isDrawerOpen()) {
            // Закрываем меню, если оно показано и при этом нажата системная кнопка "Назад"
            drawerResult.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return false;
    }


}
