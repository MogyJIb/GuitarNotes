package by.gstu.zhecka.guitarnotes.utils.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.database.SongContract;
import by.gstu.zhecka.guitarnotes.interfaces.AdatperCursorBinder;


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

    public static class AuthorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,AdatperCursorBinder {
        final private TextView mTitleTextView;

        public AuthorViewHolder( View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.tv_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void bind(Cursor cursor) {
            mTitleTextView.setText(cursor.getString(SongContract.AuthorEntry.INDEX_AUTHOR_NAME));
        }

        @Override
        public void onClick(View view) {

        }
    }
}
