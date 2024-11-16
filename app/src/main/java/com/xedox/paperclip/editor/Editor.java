package com.xedox.paperclip.editor;

public interface Editor {
    public String getTextString();
    public void setText(String newText);
    public void setTextSize(float value);
    public void undo();
    public void redo();
    public void goCursor(int relative);
    public int[] getSelect();
    public String copy();
    public void paste();
    public void startSelect();
    public void selectAll();
    public void setLineBreaks(boolean b);
    public void setPaddings(int paddings);
    public void jumpCursor(int relative);
    public int getCursorLine();
    public String[] getLines();
    public void setCursorLine(int newLine);
}
