package by.gstu.zhecka.guitarnotes.fragment.authors;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.database.SongContract;
import by.gstu.zhecka.guitarnotes.fragment.DeleteDialogFragment;
import by.gstu.zhecka.guitarnotes.model.Author;


public class DetailAuthorFragment extends AbstractDetailAuthorFragment {
    private FloatingActionButton mDeleteSongActionButton;
    private FloatingActionButton mEditSongActionButton;

    public static DetailAuthorFragment newInstance(Author author) {
        DetailAuthorFragment fragment = new DetailAuthorFragment();
        setFragmentArgs(fragment,author,false);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);

        mDeleteSongActionButton = (FloatingActionButton) view.findViewById(R.id.fb_delete_song);
        mDeleteSongActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAuthor!=null) {
                    DialogFragment dialog = DeleteDialogFragment.newInstance(mAuthor.getId(), SongContract.AuthorEntry.CONTENT_URI);
                    dialog.show(getFragmentManager(),"DeleteDialogFragment");
                }
            }
        });

        mEditSongActionButton = (FloatingActionButton) view.findViewById(R.id.fb_edit_song);
        mEditSongActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_activity_container, EditAuthorFragment.newInstance(mAuthor))
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_detail_author;
    }
}
