package by.gstu.zhecka.guitarnotes.fragment;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.UUID;

import by.gstu.zhecka.guitarnotes.R;

import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.CONTENT_URI;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.SELECTION_UUID;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.UUID_TAG;


public class DeleteDialogFragment extends DialogFragment implements DialogInterface.OnClickListener{

    private UUID mSongId;

    public static DeleteDialogFragment newInstance(UUID songId){
        Bundle args = new Bundle();
        args.putSerializable(UUID_TAG,songId);
        DeleteDialogFragment fragment = new DeleteDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();

        if(bundle!=null) {
            mSongId = (UUID) getArguments().getSerializable(UUID_TAG);
        }

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_title_delete)
                .setPositiveButton(R.string.dialog_delete, this)
                .setNegativeButton(R.string.dialog_cancel, this);
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int id) {
        switch (id){
            case DialogInterface.BUTTON_POSITIVE:
                onClickDeleteFabButton();
                getActivity().finish();
                break;
            case DialogInterface.BUTTON_NEGATIVE:

                break;
        }
    }

    private void onClickDeleteFabButton() {
        if(mSongId==null)
            return;

        String[] selectionArg = {mSongId.toString()};
        getActivity().getContentResolver().delete(CONTENT_URI, SELECTION_UUID,selectionArg);

        /* This display a toast of number item which selected */
        Toast.makeText(getActivity(), "The item was delete successfully!", Toast.LENGTH_LONG).show();
    }
}
