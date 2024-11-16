package com.xedox.paperclip.activitys;

import android.content.ComponentName;
import android.graphics.drawable.Drawable;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.SubMenu;
import android.view.MenuItem;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.xedox.paperclip.App;
import com.xedox.paperclip.R;
import com.xedox.paperclip.projects.ProjectsAdapter;
import com.xedox.paperclip.dialogs.CreateProjectDialog;
import com.xedox.paperclip.dialogs.ExitDialog;
import com.xedox.paperclip.tools.FastTask;

public class StartActivity extends AppCompatActivity {

    private MaterialToolbar toolbar;
    private RecyclerView projects;
    private ProjectsAdapter adapter;
    private int selectedProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        toolbar = findViewById(R.id.appbar);
        projects = findViewById(R.id.projects);

        adapter =
                new ProjectsAdapter(
                        this,
                        App.getProjects(),
                        (project, pos) -> {
                            Intent i = new Intent(this, EditorActivity.class);
                            i.putExtra("projectName", project.getName());
                            startActivity(i);
                            finish();
                        });
        projects.setLayoutManager(new LinearLayoutManager(this));
        projects.setAdapter(adapter);

        DefaultItemAnimator anim = new DefaultItemAnimator();
        projects.setItemAnimator(anim);

        updateProjects();
        setSupportActionBar(toolbar);
    }

    public void updateProjects() {
        FastTask.execute(
                (thread) -> {
                    synchronized (adapter) {
                        adapter.updateData(App.getProjects());
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.createProject) {
            CreateProjectDialog dialog = new CreateProjectDialog(this);
            dialog.show();
        }
        if (item.getItemId() == R.id.developer) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/xedox_creator"));
            startActivity(i);
        }
        if (item.getItemId() == R.id.settings) {
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    public ProjectsAdapter getAdapter() {
        return this.adapter;
    }

    @Override
    public void onBackPressed() {
        ExitDialog ed = new ExitDialog(this);
        ed.show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo info) {
        super.onCreateContextMenu(menu, view, info);
        getMenuInflater().inflate(R.menu.project, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.remove) {
            adapter.removeItem(selectedProject);
        }
        return super.onContextItemSelected(item);
    }

    public void setSelectedProject(int pos) {
        selectedProject = pos;
    }

    
}
