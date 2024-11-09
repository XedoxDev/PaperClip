package com.xedox.paperclip.editor;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatEditText;

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
