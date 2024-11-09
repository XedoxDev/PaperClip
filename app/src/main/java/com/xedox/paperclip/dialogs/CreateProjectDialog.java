package com.xedox.paperclip.dialogs;

import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.xedox.paperclip.App;
import com.xedox.paperclip.activitys.StartActivity;
import com.xedox.paperclip.R;
import com.xedox.paperclip.projects.Project;

public class CreateProjectDialog {

    private MaterialAlertDialogBuilder builder;
    private AlertDialog dialog;
    private StartActivity context;

    public CreateProjectDialog(StartActivity context) {
        this.context = context;
    }

    public AlertDialog build() {
        builder = new MaterialAlertDialogBuilder(context);
        builder.setTitle(R.string.createProject);
        
        TextInputLayout layout = new TextInputLayout(context);
        TextInputEditText editText = new TextInputEditText(context);
        editText.setHint(context.getString(R.string.projectName));
        layout.setTop(20);
        layout.addView(editText);

        builder.setView(layout);

        builder.setPositiveButton(
                context.getString(R.string.create),
                new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface di, int i) {
                        String name = editText.getText().toString();
                        if (!name.isBlank()) {
                            di.dismiss();
                            Project project = new Project(name);
                            project.create();
                            context.getAdapter().addItem(project);
                        }
                    }
                });
        builder.setNegativeButton(
                context.getString(R.string.cancel),
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
