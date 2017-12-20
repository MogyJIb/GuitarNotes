package by.gstu.zhecka.guitarnotes.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.interfaces.SeachCursorLoader;
import by.gstu.zhecka.guitarnotes.utils.adapters.ViewPagerAdapter;

/**
 * Created by Zhecka on 20.12.2017.
 */

public class GuitarFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_song_tabs, container, false);

         mAdapter = new ViewPagerAdapter(getContext(),getFragmentManager());


        mViewPager = view.findViewById(R.id.view_pager);
        mViewPager.setAdapter(mAdapter);

        mTabLayout = view.findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);

        return view;
    }

    public SeachCursorLoader getSelected(){
        return (SeachCursorLoader)mAdapter.getItem(mTabLayout.getSelectedTabPosition());
    }
}
