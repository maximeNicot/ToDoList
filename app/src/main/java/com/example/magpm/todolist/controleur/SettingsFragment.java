package com.example.magpm.todolist.controleur;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.magpm.todolist.R;

public class SettingsFragment extends PreferenceFragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
