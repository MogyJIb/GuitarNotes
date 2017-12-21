package by.gstu.zhecka.guitarnotes.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.fragment.authors.DetailAuthorFragment;
import by.gstu.zhecka.guitarnotes.fragment.authors.EditAuthorFragment;
import by.gstu.zhecka.guitarnotes.model.Author;

import static by.gstu.zhecka.guitarnotes.database.SongContract.AuthorEntry.AUTHOR_TAG;

/**
 * Created by Zhecka on 21.12.2017.
 */

public class DetailAuthorActivity extends MainActivity {

    private Author mAuthor;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.main_activity_container);


        if (fragment == null) {
            mAuthor = (Author) getIntent().getSerializableExtra(AUTHOR_TAG);
            if (mAuthor != null) {
                fragment = (DetailAuthorFragment) DetailAuthorFragment.newInstance(mAuthor);
                fm.beginTransaction().add(R.id.main_activity_container, fragment).commit();

                /*Fragment fragment1 = fragment.().findFragmentById(R.id.fragment_song_list);
                Bundle args = new Bundle();
                args.putSerializable(AUTHOR_TAG,mAuthor);
                fragment1.onCreate(args);*/

            }
            else{
                fragment = (EditAuthorFragment) EditAuthorFragment.newInstance(mAuthor);
                fm.beginTransaction().add(R.id.main_activity_container, fragment).commit();

            }


        }

    }
}