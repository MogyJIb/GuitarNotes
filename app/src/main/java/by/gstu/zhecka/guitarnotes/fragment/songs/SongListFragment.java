package by.gstu.zhecka.guitarnotes.fragment.songs;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.activity.DetailSongActivity;
import by.gstu.zhecka.guitarnotes.database.SongContract;
import by.gstu.zhecka.guitarnotes.interfaces.SeachCursorLoader;
import by.gstu.zhecka.guitarnotes.model.Author;
import by.gstu.zhecka.guitarnotes.utils.FakeDataUtils;
import by.gstu.zhecka.guitarnotes.utils.adapters.SongAdapter;

import static by.gstu.zhecka.guitarnotes.database.SongContract.AuthorEntry.AUTHOR_TAG;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SELECTION;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SELECTION_ARGS;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.MAIN_SONGS_PROJECTION;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.SELECTION_NAME;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.SORT_ODER_BY_NAME;

/**
 * Created by Zhecka on 8/23/2017.
 */

public final class SongListFragment extends Fragment implements  LoaderManager.LoaderCallbacks<Cursor>, SeachCursorLoader{

    /* This ID will be used to identify the Loader responsible for loading our songs. */
    private static final int SONGS_LOADER_ID = 44;
    private Author mAuthor;

    /* RecyclerView to displaying our songs and adapter to it for linking songs with the Views*/
    private RecyclerView mRecyclerView;
    private SongAdapter mAdapter;


    private FloatingActionButton mAddSongActionButton;

    public static SongListFragment newInstance(Author author) {
        SongListFragment fragment = new SongListFragment();
        Bundle args = new Bundle();
        args.putSerializable(AUTHOR_TAG,author);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle!=null) {
            mAuthor = (Author) getArguments().getSerializable(AUTHOR_TAG);
        }

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


        mAddSongActionButton = (FloatingActionButton)view.findViewById(R.id.fb_add_new);
        if(mAuthor==null) {
            mAddSongActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), DetailSongActivity.class);
                    getContext().startActivity(intent);
                }
            });
        }else {
            mAddSongActionButton.setVisibility(View.INVISIBLE);
        }



        /* Ensures a loader is initialized and active. If the loader doesn't already exist, one is
         created and (if the activity/fragment is currently started) starts the loader. Otherwise
         the last created loader is re-used. */
        reload(null);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        reload(null);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int loaderId, Bundle args) {
        String[] searchString = null;
        String selectIt = null;
        if(args!=null) {
            searchString = args.getStringArray(SELECTION_ARGS);
            selectIt  = args.getString(SELECTION);
        }
        else if(mAuthor!=null){
            searchString = new String[]{mAuthor.getName().toString()};
            selectIt = SELECTION_NAME;
        }

        switch (loaderId) {
            case SONGS_LOADER_ID:

                /* URI for all rows of songs data in our songs table */
                Uri sQueryUri = SongContract.SongEntry.CONTENT_URI;

                String selection = selectIt;
                String[] selectionArgs = searchString;

                return new CursorLoader(getContext(),
                        sQueryUri,
                        MAIN_SONGS_PROJECTION,
                        selection,
                        selectionArgs,
                        SORT_ODER_BY_NAME);
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

    public void reload(Bundle args){
        getLoaderManager().restartLoader(SONGS_LOADER_ID, args,this);
    }
}
