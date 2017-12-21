package by.gstu.zhecka.guitarnotes.utils.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import by.gstu.zhecka.guitarnotes.fragment.authors.AuthorListFragment;
import by.gstu.zhecka.guitarnotes.fragment.songs.SongListFragment;

/**
 * Created by Zhecka on 20.12.2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    SongListFragment mSongListFragment = new SongListFragment();
    AuthorListFragment mAuthorListFragment = new AuthorListFragment();

    private Context mContext;

    public ViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return mSongListFragment;
        } else {
            return mAuthorListFragment;
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 2;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return "Songs";
            case 1:
                return "Authors";
            default:
                return null;
        }
    }

}