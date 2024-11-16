package com.xedox.paperclip.editor;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatEditText;

import com.xedox.paperclip.R;
import com.xedox.paperclip.tools.ClipBoard;

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
    public void undo() {}

    @Override
    public void redo() {}

    @Override
    public String getTextString() {
        return getText().toString();
    }

    @Override
    public void goCursor(int relative) {
        if (getSelectionStart() == getSelectionEnd()) {
            int start = getSelectionEnd();
            if (start + relative >= 0 && start + relative < length() + 1)
                setSelection(start + relative);
            return;
        }

        if (getSelectionStart() != getSelectionEnd()) {
            int start = getSelectionStart();
            int end = getSelectionEnd();
            if (end + 1 < length() && end + 1 >= 0) setSelection(start, end + relative);
            if (start == getSelectionEnd()
                    && getSelectionEnd() + relative >= 0
                    && getSelectionEnd() + relative <= length())
                setSelection(start, getSelectionEnd() + relative);
        }
    }

    @Override
    public int[] getSelect() {
        return new int[] {getSelectionStart(), getSelectionEnd()};
    }

    @Override
    public String copy() {
        return getTextString().substring(getSelectionStart(), getSelectionEnd());
    }

    @Override
    public void paste() {
        getText().insert(getSelectionStart(), ClipBoard.paste(getContext()));
    }

    @Override
    public void startSelect() {
        setSelection(getSelectionStart(), getSelectionStart() + 1);
    }

    @Override
    public void setLineBreaks(boolean b) {
        setHorizontalScrollBarEnabled(b);
    }

    @Override
    public void setText(String newText) {
        super.setText(newText);
    }

    @Override
    public void setPaddings(int paddings) {
        setPadding(paddings, paddings, paddings, paddings);
    }

    @Override
    public void jumpCursor(int relative) {
        setCursorLine(getSelect()[0] + relative);
    }

    @Override
    public int getCursorLine() {
        return getLayout().getLineForOffset(getSelect()[0]);
    }

    @Override
    public String[] getLines() {
        return getTextString().split("\n");
    }

    @Override
    public void setCursorLine(int lineNumber) {
        if (lineNumber < 0) return;
        Layout layout = getLayout();
        if (layout == null) return;
        int lineCount = layout.getLineCount();
        if (lineNumber >= lineCount) lineNumber = lineCount - 1;
        int offset = layout.getLineForOffset(lineNumber);
        setSelection(offset);
    }
}
