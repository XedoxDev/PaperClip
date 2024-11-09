package com.xedox.paperclip.dialogs;

import android.content.DialogInterface;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import androidx.appcompat.app.AlertDialog;
import android.app.Activity;
import com.xedox.paperclip.R;

public class ExitDialog {

    private MaterialAlertDialogBuilder builder;
    private AlertDialog dialog;
    private Activity context;

    public ExitDialog(Activity context) {
        this.context = context;
    }

    public AlertDialog build() {
        builder = new MaterialAlertDialogBuilder(context);
        builder.setTitle(R.string.exit);
        builder.setMessage(R.string.areYouSureYouWantToGetOut);
        
        builder.setPositiveButton(
                context.getString(R.string.yes),
                new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface di, int i) {
                        context.finish();
                    }
                });
        builder.setNegativeButton(
                context.getString(R.string.no),
                new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface di, int i) {
                        di.dismiss();
                    }
                });
        return builder.create();
    }

    public void show() {
        dialog = build();
        dialog.show();
    }

    public void hide() {
        dialog.dismiss();
    }
}
