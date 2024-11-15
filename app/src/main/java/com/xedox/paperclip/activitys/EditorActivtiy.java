package com.xedox.paperclip.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import com.xedox.paperclip.R;
import com.xedox.paperclip.dialogs.CreatePageDialog;
import com.xedox.paperclip.editor.EditorFragment;
import com.xedox.paperclip.projects.Project;
import com.xedox.paperclip.dialogs.ExitDialog2;
import com.xedox.paperclip.editor.TabPagerAdapter;
import com.xedox.paperclip.tools.XDoc;

import java.util.HashMap;
import java.util.Map;

public class EditorActivtiy extends AppCompatActivity {

    private MaterialToolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private Project project;
    private TabPagerAdapter adapter;
    
    private ImageButton undo, redo;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        toolbar = findViewById(R.id.appbar);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.pager);
        
        undo = findViewById(R.id.undoButton);
        redo = findViewById(R.id.redoButton);
        
        undo.setOnClickListener((v)->{
            adapter.getEditor(tabLayout.getSelectedTabPosition()).undo();
        });
        redo.setOnClickListener((v)->{
            adapter.getEditor(tabLayout.getSelectedTabPosition()).redo();
        });
        
        String projectName = getIntent().getStringExtra("projectName");
        project = new Project(projectName);
        
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(projectName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new TabPagerAdapter(this);

        viewPager.setAdapter(adapter);

        new TabLayoutMediator(
                        tabLayout,
                        viewPager,
                        (tab, pos) -> {
                            tab.setText(adapter.getEditorFragment(pos).getPageName());
                            registerForContextMenu(tab.view);
                            tab.view.setOnLongClickListener(
                                    (v) -> {
                                        selectedTab = tab.getPosition();
                                        return false;
                                    });
                        })
                .attach();

        String projectFileContent = project.getMain().read();

        Map<String, String> pages = XDoc.parse(projectFileContent);
        for (String key : pages.keySet()) {
            adapter.addEditor(key, pages.get(key));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.editor, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            ExitDialog2<StartActivity> ed = new ExitDialog2<>(this, StartActivity.class);
            ed.show();
            return true;
        }
        if (item.getItemId() == R.id.createPage) {
            CreatePageDialog cpd = new CreatePageDialog(this);
            cpd.show();
            return true;
        }
        if (item.getItemId() == R.id.view) {
            save();
            Intent i = new Intent(this, PreviewActivity.class);
            i.putExtra("xdoc", project.getMain().read());
            i.putExtra("projectName", project.getName());
            startActivity(i);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.save) {
            save();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        ExitDialog2<StartActivity> ed = new ExitDialog2<>(this, StartActivity.class);
        ed.show();
    }

    public void addEditor(String pageName, String pageText) {
        adapter.addEditor(pageName, pageText);
    }

    public void removeEditor(String name) {
        var editors = adapter.getEditors();
        for (int i = 0; i < editors.size(); i++) {
            if (editors.get(i).getPageName() == name) adapter.removeEditor(i);
        }
    }

    private int selectedTab;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo info) {
        super.onCreateContextMenu(menu, view, info);
        getMenuInflater().inflate(R.menu.tab, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.remove) {
            adapter.removeEditor(selectedTab);
        }

        return super.onContextItemSelected(item);
    }

    public void save() {
        try {
            Map<String, String> pages = new HashMap<>();
            for (EditorFragment ef : adapter.getEditors()) {
                pages.put(ef.getPageName(), ef.getEditor().getTextString());
            }
            String text = XDoc.connect(pages);
            project.getMain().write(text);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "error: " + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
