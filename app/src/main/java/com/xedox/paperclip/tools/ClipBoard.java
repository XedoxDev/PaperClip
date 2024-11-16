package com.xedox.paperclip.tools;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

public class ClipBoard {

    public static void copy(Context context, String text) {
        ClipboardManager clipboardManager =
                (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("text", text);
        clipboardManager.setPrimaryClip(clipData);
    }

    public static String paste(Context context) {
        ClipboardManager clipboardManager =
                (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboardManager.hasPrimaryClip()
                && clipboardManager.getPrimaryClip().getItemCount() > 0) {
            ClipData.Item item = clipboardManager.getPrimaryClip().getItemAt(0);
            if (item.getText() != null) {
                return item.getText().toString();
            }
        }
        return null;
    }
}
