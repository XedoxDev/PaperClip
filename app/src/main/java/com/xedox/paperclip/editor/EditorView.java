package com.xedox.paperclip.editor;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatEditText;

import com.xedox.paperclip.R;
import java.util.ArrayList;
import java.util.List;

public class EditorView extends AppCompatEditText implements Editor {

    private List<String> changes;
    private int maxChanges = 100;
    private int currentChangeIndex = -1;

    private TextWatcher watcher =
            new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}

                @Override
                public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}

                @Override
                public void afterTextChanged(Editable e) {
                    changes.add(e.toString());
                    currentChangeIndex = changes.size();
                    if (changes.size() > maxChanges) changes.remove(0);
                }
            };

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

        changes = new ArrayList<>();
        addTextChangedListener(watcher);
    }

    @Override
    public void undo() {
        if (currentChangeIndex > 0) {
            currentChangeIndex--;
            setText(changes.get(currentChangeIndex));
        }
    }

    @Override
    public void redo() {
        if (currentChangeIndex < changes.size()) {
            currentChangeIndex++;
            setTextString(changes.get(currentChangeIndex));
        }
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
