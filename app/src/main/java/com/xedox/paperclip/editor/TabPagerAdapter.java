package com.xedox.paperclip.editor;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabPagerAdapter extends FragmentStateAdapter {

    private List<EditorFragment> editors;
    private Context context;

    public TabPagerAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.context = fragmentActivity;
        editors = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return editors.size();
    }

    @Override
    public Fragment createFragment(int position) {
        return editors.get(position);
    }

    public EditorFragment getEditor(int pos) {
        return editors.get(pos);
    }

    public void addEditor(String pageName, String text) {
        EditorFragment editor = new EditorFragment(context, pageName, text);
        editors.add(editor);
        notifyItemInserted(editors.size());
    }
    
    public void addEditor(String pageName) {
        EditorFragment editor = new EditorFragment(context, pageName);
        editors.add(editor);
        notifyItemInserted(editors.size());
    }

    public List<EditorFragment> getEditors() {
        return editors;
    }

    public void setEditors(List<EditorFragment> editors) {
        this.editors = new ArrayList<>(editors);
        notifyDataSetChanged();
    }
    
    public void updateEditor(int position, String text) {
        editors.get(position).getEditor().setTextString(text);
        notifyItemChanged(position);
    }
    
    public void removeEditor(int pos) {
        editors.remove(pos);
        notifyItemRemoved(pos);
    }
}
