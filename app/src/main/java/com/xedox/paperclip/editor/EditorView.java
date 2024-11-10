package com.xedox.paperclip.editor;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import androidx.appcompat.widget.AppCompatEditText;
import com.xedox.paperclip.R;

public class EditorView extends AppCompatEditText implements Editor {
    
    public EditorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    
    public EditorView(Context context) {
        super(context);
        init();
    }
    
    private void init() {
        setGravity(Gravity.START);
        setBackgroundColor(getContext().getColor(R.color.background));
        setTextColor(getContext().getColor(R.color.text));
    }

    @Override
    public String getTextString() {
        return getText().toString();
    }

    @Override
    public void setTextString(String newText) {
        setText(newText);
    }
}
