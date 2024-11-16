package com.xedox.paperclip.activitys;

import android.content.Intent;
import android.net.MailTo;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.appbar.MaterialToolbar;
import com.xedox.paperclip.R;
import com.xedox.paperclip.tools.XDoc;
import com.xedox.paperclip.tools.XDocView;

public class PreviewActivity extends AppCompatActivity {

    private String projectName;
    private MaterialToolbar appbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        appbar = findViewById(R.id.appbar);
        setSupportActionBar(appbar);

        XDocView xdview = findViewById(R.id.preview);
        xdview.loadXDocument(getIntent().getStringExtra("xdoc"));
        xdview.initXDocument();

        projectName = getIntent().getStringExtra("projectName");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            back();
        }
        return super.onOptionsItemSelected(item);
    }

    public void back() {
        Intent i = new Intent(this, EditorActivity.class);
        i.putExtra("projectName", projectName);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        back();
    }
}
