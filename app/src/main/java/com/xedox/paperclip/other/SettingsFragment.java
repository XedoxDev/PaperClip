package com.xedox.paperclip.other;

import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;
import com.xedox.paperclip.R;

public class SettingsFragment extends PreferenceFragmentCompat {

  @Override
  public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    setPreferencesFromResource(R.xml.settings, rootKey);
  }
}