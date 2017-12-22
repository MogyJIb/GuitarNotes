package by.gstu.zhecka.guitarnotes.fragment.authors;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.model.Author;
import by.gstu.zhecka.guitarnotes.utils.MySongConvertUtility;

import static by.gstu.zhecka.guitarnotes.database.SongContract.AuthorEntry.CONTENT_URI;
import static by.gstu.zhecka.guitarnotes.database.SongContract.AuthorEntry.SELECTION_UUID;

/**
 * Created by Zhecka on 21.12.2017.
 */

public class EditAuthorFragment extends AbstractDetailAuthorFragment{
    private FloatingActionButton mSaveSongActionButton;

    private TextView mAuthorSongs;
    public static EditAuthorFragment newInstance(Author author) {
        EditAuthorFragment fragment = new EditAuthorFragment();
        setFragmentArgs(fragment,author,true);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);

        mSaveSongActionButton = (FloatingActionButton) view.findViewById(R.id.fb_save_song);
        mSaveSongActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Author author = getAuthor();

                if(author!=null) {
                    ContentValues contentValues = MySongConvertUtility.
                            getContentValues(author);

                    String[] selectionArgs = {author.getId().toString()};
                    int songUpdated = getContext().getContentResolver().update(CONTENT_URI,contentValues, SELECTION_UUID,selectionArgs);

                    if(songUpdated == 0)
                        getContext().getContentResolver().insert(CONTENT_URI, contentValues);

                    Toast.makeText(view.getContext(), "The operation was complete successfully!", Toast.LENGTH_LONG).show();

                    getFragmentManager().popBackStack();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_activity_container, DetailAuthorFragment.newInstance(mAuthor))
                            .commit();
                }
            }
        });
        mAuthorSongs = (TextView) view.findViewById(R.id.tv_author_songs);
        mAuthorSongs.setVisibility(View.INVISIBLE);
        return view;
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_edit_author;
    }
}
