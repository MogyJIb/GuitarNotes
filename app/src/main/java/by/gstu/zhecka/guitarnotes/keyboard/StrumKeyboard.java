package by.gstu.zhecka.guitarnotes.keyboard;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.text.InputType;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import android.support.v7.widget.AppCompatButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import by.gstu.zhecka.guitarnotes.R;

/**
 * Created by Zhecka on 9/6/2017.
 */


public class StrumKeyboard extends LinearLayout implements View.OnClickListener {


    public StrumKeyboard(Context context) {
        this(context, null, 0);
    }

    public StrumKeyboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StrumKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.strum_keyboard, this, true);

        initializeButtons();
    }


    // keyboard keys (buttons)
    private ImageButton mDownstrokeButton;
    private ImageButton mSpaceButton;
    private ImageButton mUpstrokeButton;
    private ImageButton mMuteButton;
    private ImageButton mBackspaceButton;


    // This will map the button resource id to the String value that we want to
    // input when that button is clicked.
    private SparseArray<String> keyValues = new SparseArray<>();

    // Our communication link to the EditText
    private InputConnection mInputConnection;
    private EditText mEditText;



    private void initializeButtons() {
        inflateButton(mDownstrokeButton,R.id.ib_downstroke);
        inflateButton(mSpaceButton,R.id.ib_space);
        inflateButton(mUpstrokeButton,R.id.ib_upstroke);
        inflateButton(mMuteButton,R.id.ib_mute);
        inflateButton(mBackspaceButton,R.id.ib_backspace);
    }

    private void inflateButton(ImageButton button, int buttonId){
        button = (ImageButton) findViewById(buttonId);
        button.setOnClickListener(this);
        keyValues.put(buttonId, (String) button.getTag());
    }

    @Override
    public void onClick(View v) {

        // do nothing if the InputConnection has not been set yet
        if (mInputConnection == null) return;

        // Delete text or input key value
        // All communication goes through the InputConnection
        if (v.getId() == R.id.ib_backspace) {
            CharSequence selectedText = mInputConnection.getSelectedText(0);
            if (TextUtils.isEmpty(selectedText)) {
                // no selection, so delete previous character
                mInputConnection.deleteSurroundingText(1, 0);
            } else {
                // delete the selection
                mInputConnection.commitText("", 1);
            }
        } else {
            String value = keyValues.get(v.getId());
            mInputConnection.commitText(value, 1);
        }
    }

    public void setEditText(final EditText editText, Activity hostActivity) {
        mEditText = editText;

        mEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                EditText editTextPinCode = (EditText)v;
                editTextPinCode.setCursorVisible(true);
                editTextPinCode.setFocusable(true);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Layout layout = ((EditText) v).getLayout();
                        float x = event.getX() + editTextPinCode.getScrollX();
                        int offset = layout.getOffsetForHorizontal(0, x);
                        if(offset>0)
                            if(x>layout.getLineMax(0))
                                editTextPinCode.setSelection(offset);
                            else
                                editTextPinCode.setSelection(offset - 1);

                        break;
                }
                return true;
            }
        });

        // prevent system keyboard from appearing when EditText is tapped
        mEditText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        mEditText.setTextIsSelectable(true);

        // pass the InputConnection from the EditText to the keyboard
        mInputConnection = mEditText.onCreateInputConnection(new EditorInfo());
    }
}

