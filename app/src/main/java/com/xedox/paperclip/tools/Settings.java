package com.xedox.paperclip.tools;

import android.content.Context;
import android.content.SharedPreferences;
import com.xedox.paperclip.App;
import java.io.Serializable;
import static android.content.Context.*;

public class Settings {
    
    public static final float TEXT_SIZE = App.toDP(36);
    public static final boolean LINE_BREAKS = false;
    
    public static float getTextSize() {
        SharedPreferences sp = App.context.getSharedPreferences("SETTINGS", MODE_PRIVATE);
        return sp.getFloat("TEXT<>SIZE", TEXT_SIZE);
    }
    
    public static boolean getIsLineBreaks() {
        SharedPreferences sp = App.context.getSharedPreferences("SETTINGS", MODE_PRIVATE);
        return sp.getBoolean("LINE_BREAKS", LINE_BREAKS);
    }
    
    public static boolean setTextSize(float newTextSize) {
        SharedPreferences.Editor sp = App.context.getSharedPreferences("SETTINGS", MODE_PRIVATE).edit();
        sp.putFloat("TEXT_SIZE", newTextSize);
        return sp.commit();
    }
    
    public static boolean setLineBreaks(boolean newLineBreaks) {
        SharedPreferences.Editor sp = App.context.getSharedPreferences("SETTINGS", MODE_PRIVATE).edit();
        sp.putBoolean("LINE_BREAKS", newLineBreaks);
        return sp.commit();
    }
}
