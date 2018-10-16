package com.example.maxime.todolist2;

import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
/*<PreferenceCategory
            android:key="pref_key_general_settings"
            android:title="@string/pref_sync_google_facebook_title">
            <EditTextPreference
                android:dependency="pref_sync"
                android:defaultValue="true"
                android:key="pref_sync"
                android:summary="@string/pref_sync_sum_google"
                android:title="@string/pref_sync_google" />
            <EditTextPreference
                android:dependency="pref_sync"
                android:defaultValue="true"
                android:key="pref_sync"
                android:summary="@string/pref_sync_sum_facebook"
                android:title="@string/pref_sync_facebook" />

        </PreferenceCategory>*/
}
