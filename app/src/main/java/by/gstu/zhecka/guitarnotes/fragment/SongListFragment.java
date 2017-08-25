package by.gstu.zhecka.guitarnotes.fragment;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.Utilite.FakeDataUtils;
import by.gstu.zhecka.guitarnotes.Utilite.SongAdapter;
import by.gstu.zhecka.guitarnotes.database.SongContract;

import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.COLUMN_AUTHOR;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.COLUMN_NAME;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.COLUMN_TEXT;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.COLUMN_UUID;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry._ID;

/**
 * Created by Zhecka on 8/23/2017.
 */

public final class SongListFragment extends Fragment implements  LoaderManager.LoaderCallbacks<Cursor>{

    /* This ID will be used to identify the Loader responsible for loading our songs. */
    private static final int SONGS_LOADER_ID = 44;


    /* RecyclerView to displaying our songs and adapter to it for linking songs with the Views*/
    private RecyclerView mRecyclerView;
    private SongAdapter mAdapter;


    /* The columns of data that we are interested in displaying within our MainActivity's list of
    weather data. */
    public static final String[] MAIN_SONGS_PROJECTION = {
            COLUMN_UUID,
            COLUMN_NAME,
            COLUMN_AUTHOR
    };


    /* We store the indices of the values in the array of Strings above to more quickly be able to
    access the data from our query. If the order of the Strings above changes, these indices
    must be adjusted to match the order of the Strings. */
    public static final int INDEX_SONG_UUID = 0;
    public static final int INDEX_SONG_NAME = 1;
    public static final int INDEX_SONG_AUTHOR = 2;

    public static final String SORT_ODER = "name";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_song_list, container, false);


        FakeDataUtils.insertFakeData(getContext());

        mRecyclerView = (RecyclerView) view.findViewById(R.id.song_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        /* Create new SongsAdapter and setting the adapter attaches it to the RecyclerView in our
        layout. */
        mAdapter = new SongAdapter(getContext(),null);
        mRecyclerView.setAdapter(mAdapter);


        /* Ensures a loader is initialized and active. If the loader doesn't already exist, one is
         created and (if the activity/fragment is currently started) starts the loader. Otherwise
         the last created loader is re-used. */
        getLoaderManager().initLoader(SONGS_LOADER_ID, null, this);

        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int loaderId, Bundle args) {
        switch (loaderId) {
            case SONGS_LOADER_ID:

                /* URI for all rows of songs data in our songs table */
                Uri sQueryUri = SongContract.SongEntry.CONTENT_URI;

                return new CursorLoader(getContext(),
                        sQueryUri,
                        MAIN_SONGS_PROJECTION,
                        null,
                        null,
                        SORT_ODER);
            default:
                throw new RuntimeException("Loader Not Implemented: " + loaderId);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
/* Update the data that the adapter uses to create ViewHolders */
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
/* Since this Loader's data is now invalid, we need to clear the Adapter that is
         displaying the data. */
        mAdapter.swapCursor(null);
    }
}
