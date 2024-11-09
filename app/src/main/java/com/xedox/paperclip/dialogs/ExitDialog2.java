package com.xedox.paperclip.dialogs;

import android.content.DialogInterface;
import android.content.Intent;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import androidx.appcompat.app.AlertDialog;
import android.app.Activity;
import com.xedox.paperclip.R;

public class ExitDialog2<T extends Activity> {

    private MaterialAlertDialogBuilder builder;
    private AlertDialog dialog;
    private Activity context;
    private Class<T> twoActivity;

    public ExitDialog2(Activity context, Class<T> twoActivity) {
        this.context = context;
        this.twoActivity = twoActivity;
    }

    public AlertDialog build() {
        builder = new MaterialAlertDialogBuilder(context);
        builder.setTitle(R.string.exitToMainMenu);
        builder.setMessage(R.string.areYouSureYouWantToGetOutInMainMenu);

        builder.setPositiveButton(
                context.getString(R.string.yes),
                (di, i) -> {
                    try {
                        Intent intent = new Intent(context, twoActivity);
                        T activityInstance = twoActivity.newInstance();
                        context.startActivity(intent);
                        context.finish();
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
        builder.setNegativeButton(
                context.getString(R.string.no),
                (di, i) -> di.dismiss());
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
