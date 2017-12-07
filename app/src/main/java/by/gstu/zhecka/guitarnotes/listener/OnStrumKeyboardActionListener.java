package by.gstu.zhecka.guitarnotes.listener;

import android.app.Activity;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Zhecka on 9/6/2017.
 */

public class OnStrumKeyboardActionListener implements KeyboardView.OnKeyboardActionListener {
    public final static int CodeDelete   = -5; // Keyboard.KEYCODE_DELETE


    private Activity mHostActivity;
    private EditText mEditText;

    public OnStrumKeyboardActionListener(Activity activity,EditText editText){
        mHostActivity = activity;
        mEditText = editText;
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        // NOTE We can say '<Key android:codes="49,50" ... >' in the xml file; all codes come in keyCodes, the first in this list in primaryCode
        // Get the EditText and its Editable
        View focusCurrent = mEditText;
        if( focusCurrent==null || focusCurrent.getClass()!=EditText.class ) return;
        EditText edittext = (EditText) focusCurrent;
        Editable editable = edittext.getText();
        int start = edittext.getSelectionStart();
        // Apply the key to the edittext
        if( primaryCode==CodeDelete ) {
            if( editable!=null && start>0 ) editable.delete(start - 1, start);
        }  else { // insert character
            editable.insert(start, Character.toString((char) primaryCode));
        }
    }

    @Override public void onPress(int arg0) {
    }

    @Override public void onRelease(int primaryCode) {
    }

    @Override public void onText(CharSequence text) {
    }

    @Override public void swipeDown() {
    }

    @Override public void swipeLeft() {
    }

    @Override public void swipeRight() {
    }

    @Override public void swipeUp() {
    }
}
