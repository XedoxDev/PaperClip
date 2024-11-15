package com.xedox.paperclip.editor;

public interface Editor {
    public String getTextString();
    public void setTextString(String newText);
    
    public void undo();
    public void redo();
}
