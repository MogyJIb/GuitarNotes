package by.gstu.zhecka.guitarnotes.utils.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.activity.DetailAuthorActivity;
import by.gstu.zhecka.guitarnotes.interfaces.AdatperCursorBinder;
import by.gstu.zhecka.guitarnotes.model.Author;
import by.gstu.zhecka.guitarnotes.utils.MySongConvertUtility;

import static by.gstu.zhecka.guitarnotes.database.SongContract.AuthorEntry.AUTHOR_TAG;
import static by.gstu.zhecka.guitarnotes.database.SongContract.AuthorEntry.CONTENT_URI;
import static by.gstu.zhecka.guitarnotes.database.SongContract.AuthorEntry.INDEX_AUTHOR_NAME;
import static by.gstu.zhecka.guitarnotes.database.SongContract.AuthorEntry.INDEX_AUTHOR_UUID;
import static by.gstu.zhecka.guitarnotes.database.SongContract.AuthorEntry.MAIN_AUTHORS_PROJECTION;
import static by.gstu.zhecka.guitarnotes.database.SongContract.AuthorEntry.SELECTION_UUID;


/**
 * Created by Zhecka on 19.12.2017.
 */

public class AuthorAdapter extends  AbstractAdapter<AuthorAdapter.AuthorViewHolder>{

    public AuthorAdapter(@NonNull Context context, PlaylistAdapterOnClickHandler clickHandler) {
        super(context, clickHandler);
    }

    @Override
    protected AuthorViewHolder getViewHolder(View view) {
        return new AuthorViewHolder(view);
    }

    public class AuthorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,AdatperCursorBinder {
        final private TextView mTitleTextView;

        public AuthorViewHolder( View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.tv_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void bind(Cursor cursor) {
            mTitleTextView.setText(cursor.getString(INDEX_AUTHOR_NAME));
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mCursor.moveToPosition(clickedPosition);
            Toast.makeText(mContext, mCursor.getString(INDEX_AUTHOR_UUID) +
                    " clicked!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(mContext, DetailAuthorActivity.class);

            UUID uuid = UUID.fromString(mCursor.getString(INDEX_AUTHOR_UUID));
            Author author = getAuthorFromDatabase(uuid);

            intent.putExtra(AUTHOR_TAG,author);
            mContext.startActivity(intent);
        }
        private Author getAuthorFromDatabase(UUID songId) {
            if(songId == null)
                return null;

            String selection = SELECTION_UUID;
            String[] selectionArgs = {songId.toString()};
            Cursor cursor = mContext.getContentResolver()
                    .query(CONTENT_URI, MAIN_AUTHORS_PROJECTION, selection, selectionArgs, null);
            return MySongConvertUtility.getAuthorFromCursor(cursor);
        }
    }
}
