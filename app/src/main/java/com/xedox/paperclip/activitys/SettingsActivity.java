package com.xedox.paperclip.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.appbar.MaterialToolbar;
import com.xedox.paperclip.R;
import com.xedox.paperclip.other.SettingsFragment;

public class SettingsActivity extends AppCompatActivity {

    private MaterialToolbar appbar;
    private FrameLayout settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        appbar = findViewById(R.id.appbar);
        setSupportActionBar(appbar);

        getSupportActionBar().setTitle(getString(R.string.settings));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.settings, new SettingsFragment());
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(this, StartActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
