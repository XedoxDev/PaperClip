package com.xedox.paperclip.tools;

import android.content.Context;
import android.content.res.Configuration;

public class ThemeManager {
    public static enum Type {
        DAY,
        NIGHT
    }
    
    public static void setTheme(Context c, Type t) {
        if(t == Type.DAY) {
            c.getResources().getConfiguration().uiMode = Configuration.UI_MODE_NIGHT_NO;
            return;
        }
        if(t == Type.DAY) {
            c.getResources().getConfiguration().uiMode = Configuration.UI_MODE_NIGHT_YES;
            return;
        }
    }
}
