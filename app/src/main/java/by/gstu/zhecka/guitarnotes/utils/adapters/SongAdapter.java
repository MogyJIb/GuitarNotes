package by.gstu.zhecka.guitarnotes.utils.adapters;

/**
 * Created by Zhecka on 8/24/2017.
 */

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
import by.gstu.zhecka.guitarnotes.activity.DetailSongActivity;
import by.gstu.zhecka.guitarnotes.database.SongContract;
import by.gstu.zhecka.guitarnotes.interfaces.AdatperCursorBinder;
import by.gstu.zhecka.guitarnotes.model.Song;
import by.gstu.zhecka.guitarnotes.utils.MySongConvertUtility;

import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.CONTENT_URI;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.DETAIL_SONGS_PROJECTION;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.INDEX_SONG_AUTHOR;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.INDEX_SONG_NAME;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.INDEX_SONG_UUID;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.SELECTION_NAME;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.SELECTION_UUID;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.SONG_TAG;


public class SongAdapter
        extends AbstractAdapter<SongAdapter.SongViewHolder> {


    public SongAdapter(@NonNull Context context, PlaylistAdapterOnClickHandler clickHandler) {
        super(context, clickHandler);
    }

    @Override
    protected SongViewHolder getViewHolder(View view) {
        return new SongViewHolder(view);
    }


    public class SongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,AdatperCursorBinder {
        final private TextView mTitleTextView;

        public SongViewHolder( View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.tv_title);
            itemView.setOnClickListener(this);
        }


        @Override
        public void bind(Cursor cursor) {
            String uuid = cursor.getString(INDEX_SONG_AUTHOR);
            String selection = SELECTION_NAME;
            String[] selectionArgs =new String[]{uuid};
            Cursor cursorAuthor = mContext.getContentResolver()
                    .query(SongContract.AuthorEntry.CONTENT_URI, SongContract.AuthorEntry.MAIN_AUTHORS_PROJECTION, selection, selectionArgs, null);
            cursorAuthor.moveToFirst();
            mTitleTextView.setText(cursor.getString(INDEX_SONG_NAME) );

        }

        /* This gets called by the child views during a click. We fetch the ID of song that has been
         selected, and then call the onClick handler registered with this adapter, passing that
         ID. */
        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mCursor.moveToPosition(clickedPosition);
            Toast.makeText(mContext, mCursor.getString(INDEX_SONG_UUID) +
                    " clicked!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(mContext, DetailSongActivity.class);

            UUID uuid = UUID.fromString(mCursor.getString(INDEX_SONG_UUID));
            Song song = getSongFromDatabase(uuid);

            intent.putExtra(SONG_TAG,song);
            mContext.startActivity(intent);
        }

        private Song getSongFromDatabase(UUID songId) {
            if(songId == null)
                return null;

            String selection = SELECTION_UUID;
            String[] selectionArgs = {songId.toString()};
            Cursor cursorSong = mContext.getContentResolver()
                    .query(CONTENT_URI, DETAIL_SONGS_PROJECTION, selection, selectionArgs, null);
            cursorSong.moveToFirst();

            selection = SELECTION_NAME;
            String uuid = cursorSong.getString(INDEX_SONG_AUTHOR);
            selectionArgs =new String[]{uuid};
            Cursor cursorAuthor = mContext.getContentResolver()
                    .query(SongContract.AuthorEntry.CONTENT_URI, SongContract.AuthorEntry.MAIN_AUTHORS_PROJECTION, selection, selectionArgs, null);


            return MySongConvertUtility.getSongFromCursor(cursorSong,cursorAuthor);
        }
    }
}

