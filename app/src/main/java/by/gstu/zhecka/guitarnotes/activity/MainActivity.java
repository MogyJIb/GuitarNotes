package by.gstu.zhecka.guitarnotes.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.model.Account;
import by.gstu.zhecka.guitarnotes.utils.DrawerNavigationUtils;

/**
 * Created by Zhecka on 8/23/2017.
 */


public class MainActivity extends AppCompatActivity {
    public static Account ACCOUNT =null;


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
        login();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        login();
    }

    @Override
    protected void onResume() {
        super.onResume();
        login();
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

    private void login(){
        if(ACCOUNT!=null && headerResult.getProfiles().size()==0) {
            DrawerNavigationUtils.setAccount(this, headerResult, drawerResult, ACCOUNT);
        }
    }
    public void logout() {
        if (ACCOUNT != null){
            ACCOUNT = null;
            DrawerNavigationUtils.takeAccount( headerResult,drawerResult, ACCOUNT);
        }
    }

}
