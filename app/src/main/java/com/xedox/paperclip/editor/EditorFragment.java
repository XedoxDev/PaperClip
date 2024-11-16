package com.xedox.paperclip.editor;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import com.xedox.paperclip.R;

public class EditorFragment extends Fragment {

    private Editor editor;
    private Context context;
    private String text = "";
    private String pageName;

    public EditorFragment(Context context, String pageName, String text) {
        this.context = context;
        this.pageName = pageName;
        this.text = text;
    }

    public EditorFragment(Context context, String pageName) {
        this.context = context;
        this.pageName = pageName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle bundle) {
        View view = inflater.inflate(R.layout.editor_fragment, parent);
        editor = view.findViewById(R.id.editor);
        editor.setText(text);

        var p = PreferenceManager.getDefaultSharedPreferences(getContext());

        boolean lb = p.getBoolean("lineBreaks", true);
        float ts = Float.parseFloat(p.getString("textSize", "24.0"));
        int ps = Integer.parseInt(p.getString("paddings", "0"));
        editor.setTextSize(ts);
        editor.setLineBreaks(lb);
        editor.setPaddings(ps);
        return view;
    }

    public Editor getEditor() {
        return editor;
    }

    public String getPageName() {
        return this.pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
}
