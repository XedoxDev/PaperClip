package com.xedox.paperclip.dialogs;

import android.content.DialogInterface;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import androidx.appcompat.app.AlertDialog;
import com.xedox.paperclip.activitys.EditorActivtiy;
import com.xedox.paperclip.activitys.StartActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.xedox.paperclip.R;

public class CreatePageDialog {

    private MaterialAlertDialogBuilder builder;
    private AlertDialog dialog;
    private EditorActivtiy context;

    public CreatePageDialog(EditorActivtiy context) {
        this.context = context;
    }

    public AlertDialog build() {
        builder = new MaterialAlertDialogBuilder(context);
        builder.setTitle(R.string.createPage);

        TextInputLayout layout = new TextInputLayout(context);
        TextInputEditText editText = new TextInputEditText(context);
        editText.setHint(context.getString(R.string.namePage));
        layout.addView(editText);

        builder.setView(layout);

        builder.setPositiveButton(
                context.getString(R.string.create),
                (di, i) -> {
                    String name = editText.getText().toString();
                    if (!name.isBlank()) {
                        di.dismiss();
                        context.addEditor(name, "Its new Page!");
                    }
                });
        builder.setNegativeButton(
                context.getString(R.string.cancel),
                (di, i) -> {
                    di.dismiss();
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
