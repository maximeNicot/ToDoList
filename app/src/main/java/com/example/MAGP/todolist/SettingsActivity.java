package com.example.MAGP.todolist;

import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.os.Bundle;
import android.widget.EditText;

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();


    }
}
