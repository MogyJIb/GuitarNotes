package by.gstu.zhecka.guitarnotes.fragment;


import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.UUID;

import by.gstu.zhecka.guitarnotes.R;

import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.SELECTION_UUID;
import static by.gstu.zhecka.guitarnotes.database.SongContract.SongEntry.UUID_TAG;
import static by.gstu.zhecka.guitarnotes.database.SongContract.URI_TAG;


public class DeleteDialogFragment extends DialogFragment implements DialogInterface.OnClickListener{

    private UUID mId;
    private Uri contentUri;

    public static DeleteDialogFragment newInstance(UUID Id,Uri contentUri){
        Bundle args = new Bundle();
        args.putSerializable(UUID_TAG,Id);
        args.putParcelable(URI_TAG,contentUri);
        DeleteDialogFragment fragment = new DeleteDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();

        if(bundle!=null) {
            mId = (UUID) getArguments().getSerializable(UUID_TAG);
            contentUri= getArguments().getParcelable(URI_TAG);
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
        if(mId==null)
            return;

        String[] selectionArg = {mId.toString()};
        getActivity().getContentResolver().delete(contentUri, SELECTION_UUID,selectionArg);

        /* This display a toast of number item which selected */
        Toast.makeText(getActivity(), "The item was delete successfully!", Toast.LENGTH_LONG).show();
    }
}
