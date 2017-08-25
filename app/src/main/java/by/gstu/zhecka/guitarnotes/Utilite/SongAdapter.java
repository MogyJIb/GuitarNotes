package by.gstu.zhecka.guitarnotes.Utilite;

/**
 * Created by Zhecka on 8/24/2017.
 */

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.fragment.SongListFragment;


public class SongAdapter
        extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    /* Temp variable to understand which item is selected */
    private static int viewHolderCount;


    /* Simple name of our class and number of items which will be displayed */
    private static final String TAG = SongAdapter.class.getSimpleName();


    /* The context we use to utility methods, app resources and layout inflaters */
    private final Context mContext;
    private Cursor mCursor;


    /* Defined an interface to handle clicks on items within this Adapter. In the
    constructor of our ForecastAdapter, receive an instance of a class that has implemented
    said interface. Store that instance in this variable to call the onClick method whenever
    an item is clicked in the list. */
    private final PlaylistAdapterOnClickHandler onClickHandler;


    /* The interface that receives onClick messages. */
    public interface PlaylistAdapterOnClickHandler {
        void onClick(long id);
        
    }


    /* Constructor for SongsAdapter */
    public SongAdapter(@NonNull Context context, PlaylistAdapterOnClickHandler clickHandler) {
        mContext = context;
        onClickHandler = clickHandler;
        this.viewHolderCount = 0;
    }

    /* This gets called when each new ViewHolder is created. This happens when the RecyclerView
     is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling. */
    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        /* ID of layout used to recycler view */
        int layoutIdForListItem = R.layout.song_recycler_view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        view.setFocusable(true);
        SongViewHolder viewHolder = new SongViewHolder(view);


        /* Temp variable to understand which item is selected */
        viewHolderCount++;
        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: " + viewHolderCount);

        return viewHolder;
    }

    /* OnBindViewHolder is called by the RecyclerView to display the data at the specified
      position. In this method, we update the contents of the ViewHolder to displaying */
    @Override
    public void onBindViewHolder(SongViewHolder songViewHolder, int position) {
        mCursor.moveToPosition(position);

        /* Temp logs to understand which item is selected */
        Log.d(TAG, "#" + position);


        /* Display information in view */
        songViewHolder.bindSong(mCursor);
    }

    /* The number of items available */
    @Override
    public int getItemCount() {
        if (null == mCursor) return 0;
        return mCursor.getCount();
    }

    /* Swaps the cursor used by the ForecastAdapter for its weather data. This method is called by
    MainActivity after a load has finished, as well as when the Loader responsible for loading
    the weather data is reset. When this method is called, we assume we have a completely new
    set of data, so we call notifyDataSetChanged to tell the RecyclerView to update. */
    public void swapCursor(Cursor newCursor) {
        mCursor = newCursor;
        notifyDataSetChanged();
    }


    /* A ViewHolder is a required part of the pattern for RecyclerViews. It mostly behaves as
    a cache of the child views for a forecast item. It's also a convenient place to set an
    OnClickListener, since it has access to the adapter and the views. */
    public class SongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* View to displaying information */
        final TextView mTitleTextView;

        public SongViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.tv_title);
            itemView.setOnClickListener(this);
        }

        public void bindSong(Cursor cursor) {
            mTitleTextView.setText(cursor.getString(SongListFragment.INDEX_SONG_NAME) +
                                cursor.getString(SongListFragment.INDEX_SONG_AUTHOR));
        }

        /* This gets called by the child views during a click. We fetch the ID of song that has been
         selected, and then call the onClick handler registered with this adapter, passing that
         ID. */
        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mCursor.moveToPosition(clickedPosition);

        }
    }
}

