package by.gstu.zhecka.guitarnotes.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.activity.GuitarActivity;
import by.gstu.zhecka.guitarnotes.activity.LogInActivity;
import by.gstu.zhecka.guitarnotes.activity.MainActivity;
import by.gstu.zhecka.guitarnotes.model.Account;

/**
 * Created by Zhecka on 09.12.2017.
 */

public class DrawerNavigationUtils {
    public static final int ACCOUNTS_LOGOUT_ID = 0;
    public static final int ACCOUNTS_LOGIN_ID = 1;
    public static final int HOME_ID = 2;


    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            //
        }

    }


    public static Drawer.OnDrawerItemClickListener handlerOnClick(final Drawer.Result drawerResult, final AppCompatActivity activity) {
        return new Drawer.OnDrawerItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                //check if the drawerItem is set.
                //there are different reasons for the drawerItem to be null
                //--> click on the header
                //--> click on the footer
                //those items don't contain a drawerItem

                if (drawerItem != null) {

                    if (drawerItem.getIdentifier() == ACCOUNTS_LOGIN_ID) {
                        Intent intent = new Intent(view.getContext(), LogInActivity.class);
                        activity.startActivity(intent);

                    }
                    else if (drawerItem.getIdentifier() == HOME_ID) {
                        Intent intent = new Intent(view.getContext(), GuitarActivity.class);
                        activity.startActivity(intent);
                    }
                    /* else if (drawerItem.getIdentifier() == 70) {
                        // Rate App
                        try {
                            Intent int_rate = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + activity.getApplicationContext().getPackageName()));
                            int_rate.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            activity.getApplicationContext().startActivity(int_rate);
                        } catch (Exception e) {
                            //
                        }
                    }*/

                }
            }
        };
    }


    public static AccountHeader.Result getAccountHeader(final AppCompatActivity activity, final Bundle savedInstanceState) {

        // Create a few sample profile
        // NOTE you have to define the loader logic too. See the CustomApplication for more details

        /*final IProfile profile2 = new ProfileDrawerItem().withName("Bernat Borras").withEmail("alorma@github.com").withIcon(Uri.parse("https://avatars3.githubusercontent.com/u/887462?v=3&s=460"));
    */

        AccountHeader header = new AccountHeader()
                .withActivity(activity)
                .withHeaderBackground(R.drawable.header)
                .withProfileImagesClickable(false)
                .withProfileImagesVisible(false);


        header.withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
            @Override
            public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                if (profile instanceof IDrawerItem && ((IDrawerItem) profile).getIdentifier() == ACCOUNTS_LOGOUT_ID) {
                    ((MainActivity)activity).logout();
                }
                //false if you have not consumed the event and it should close the drawer
                return false;
            }
        })
        .withSavedInstance(savedInstanceState);

        // Create the AccountHeader
        return header.build();
    }

    public static Drawer.Result createCommonDrawer(final AppCompatActivity activity, Toolbar toolbar, AccountHeader.Result headerResult) {

        Drawer drawer =new Drawer()
                .withActivity(activity)
                .withToolbar(toolbar)
                .withHeader(R.layout.drawer_header)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                       new PrimaryDrawerItem().withName("Login").withIdentifier(ACCOUNTS_LOGIN_ID),
                        new PrimaryDrawerItem().withName("Home").withIcon(GoogleMaterial.Icon.gmd_home).withIdentifier(HOME_ID),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName("Settings").withIcon(GoogleMaterial.Icon.gmd_settings).withIdentifier(50)


                        // Вывод для Google Play
                        //new PrimaryDrawerItem().withName(R.string.drawer_item_out).withIcon(GoogleMaterial.Icon.gmd_attach_money).withIdentifier(4),
                        //new DividerDrawerItem(),
                        //new SecondaryDrawerItem().withName(R.string.drawer_item_settings).withIcon(GoogleMaterial.Icon.gmd_settings).withIdentifier(50),
                        //new SecondaryDrawerItem().withName(R.string.drawer_item_help).withIcon(GoogleMaterial.Icon.gmd_help).withIdentifier(60),
                        //new DividerDrawerItem(),
                        //new SecondaryDrawerItem().withName(R.string.drawer_item_rate).withIdentifier(70)
                        //new SecondaryDrawerItem().withName(R.string.drawer_item_donate).withIdentifier(80)
                )
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public boolean equals(Object o) {
                        return super.equals(o);
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        //Toast.makeText(MainActivity.this, "onDrawerOpened", Toast.LENGTH_SHORT).show();
                        hideSoftKeyboard(activity);
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        //Toast.makeText(MainActivity.this, "onDrawerClosed", Toast.LENGTH_SHORT).show();
                    }
                });

        Drawer.Result drawerResult = drawer.build();
        drawerResult.setOnDrawerItemClickListener(handlerOnClick(drawerResult, activity));

        return drawerResult;
    }

    public static void setAccount(final AppCompatActivity activity, AccountHeader.Result headerResult,Drawer.Result drawerResult, Account account){
        if(account==null)
            return;

        drawerResult.removeItem(0);
        drawerResult.addItem(
                new PrimaryDrawerItem().withName("Favorite").withIdentifier(1001),0);
        drawerResult.addItem(
                new PrimaryDrawerItem().withName("Playlist").withIdentifier(1000),1);
        final IProfile profile = new ProfileDrawerItem().withName(account.getName()).withEmail(account.getLogin());
        headerResult.addProfiles(
                profile,
                      /*  profile2,
                       */
                //don't ask but google uses 14dp for the add ACCOUNT icon in gmail but 20dp for the normal icons (like manage ACCOUNT)
                //new ProfileSettingDrawerItem().withName("Add Account").withDescription("Add new GitHub Account").withIcon(new IconicsDrawable(activity, GoogleMaterial.Icon.gmd_add).actionBarSize().paddingDp(5).colorRes(R.color.material_drawer_primary_text)).withIdentifier(111),
                new ProfileSettingDrawerItem().withName("Logout").withIcon(new IconicsDrawable(activity, GoogleMaterial.Icon.gmd_exit_to_app).actionBarSize().paddingDp(5).colorRes(R.color.material_drawer_primary_text)).withIdentifier(ACCOUNTS_LOGOUT_ID),
                new ProfileSettingDrawerItem().withName("Manage Account").withIcon(GoogleMaterial.Icon.gmd_settings)
        );
    }
    public static void takeAccount(AccountHeader.Result headerResult,Drawer.Result drawerResult, Account account){
        if(account!=null)
            return;
        drawerResult.resetDrawerContent();
        headerResult.clear();
        drawerResult.removeItem(0);
        drawerResult.removeItem(1);
        drawerResult.addItem( new PrimaryDrawerItem().withName("Login").withIdentifier(ACCOUNTS_LOGIN_ID),0);
    }


}
