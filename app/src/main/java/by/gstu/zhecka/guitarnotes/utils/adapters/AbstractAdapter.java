package by.gstu.zhecka.guitarnotes.utils.adapters;


import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.gstu.zhecka.guitarnotes.R;
import by.gstu.zhecka.guitarnotes.interfaces.AdatperCursorBinder;

public abstract class AbstractAdapter<T extends  RecyclerView.ViewHolder & AdatperCursorBinder>
        extends RecyclerView.Adapter<T> {

    /* Temp variable to understand which item is selected */
    private static int viewHolderCount;


    /* Simple name of our class and number of items which will be displayed */
    private static final String TAG = SongAdapter.class.getSimpleName();


    /* The context we use to utility methods, app resources and layout inflaters */
    protected final Context mContext;
    protected Cursor mCursor;


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
    public AbstractAdapter(@NonNull Context context, PlaylistAdapterOnClickHandler clickHandler) {
         mContext = context;
        onClickHandler = clickHandler;
        this.viewHolderCount = 0;
    }

    /* This gets called when each new ViewHolder is created. This happens when the RecyclerView
     is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling. */
    @Override
    public T onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        /* ID of layout used to recycler view */
        int layoutIdForListItem = R.layout.song_recycler_view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        view.setFocusable(true);
        T viewHolder = getViewHolder(view);


        /* Temp variable to understand which item is selected */
        viewHolderCount++;
        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: " + viewHolderCount);

        return viewHolder;
    }

    /* OnBindViewHolder is called by the RecyclerView to display the data at the specified
      position. In this method, we update the contents of the ViewHolder to displaying */
    @Override
    public void onBindViewHolder(T viewHolder, int position) {
        mCursor.moveToPosition(position);

        /* Temp logs to understand which item is selected */
        Log.d(TAG, "#" + position);


        /* Display information in view */
        viewHolder.bind(mCursor);
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

    protected abstract T getViewHolder(View view);

}

