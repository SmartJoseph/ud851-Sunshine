package com.example.android.sunshine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

/**
 * Created by Richard on 17/05/2018.
 */
// TODO (4) Create SettingsFragment and extend PreferenceFragmentCompat
// TODO (10) Implement OnSharedPreferenceChangeListener from SettingsFragment
public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        // TODO (5) Override onCreatePreferences and add the preference xml file using addPreferencesFromResource
        addPreferencesFromResource(R.xml.prefs);

        // Do step 9 within onCreatePreference
        // TODO (9) Set the preference summary on each preference that isn't a CheckBoxPreference
        PreferenceScreen screen = getPreferenceScreen();
        SharedPreferences prefs = screen.getSharedPreferences();
        for (int i = 0; i < screen.getPreferenceCount(); i++) {
            Preference pref = screen.getPreference(i);
            if (!(pref instanceof CheckBoxPreference)) {
                String key = pref.getKey();
                String value = prefs.getString(key, null);
                setPreferenceSummary(pref, value);
            }
        }

    }

    // TODO (8) Create a method called setPreferenceSummary that accepts a Preference and an Object and sets the summary of the preference
    private void setPreferenceSummary(@NonNull Preference pref, @Nullable Object value) {
        if (value == null) {
            pref.setSummary(null);
            return;
        }

        if (pref instanceof ListPreference) {
            ListPreference list = (ListPreference) pref;
            int i = list.findIndexOfValue(value.toString());
            if (i < 0 || i >= list.getEntries().length) {
                pref.setSummary(null);
                return;
            }
            CharSequence summary = list.getEntries()[i];
            pref.setSummary(summary);
        } else {
            pref.setSummary(value.toString());
        }

    }

    // TODO (12) Register SettingsFragment (this) as a SharedPreferenceChangedListener in onStart
    @Override
    public void onStart() {
        super.onStart();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    // TODO (13) Unregister SettingsFragment (this) as a SharedPreferenceChangedListener in onStop
    @Override
    public void onStop() {
        super.onStop();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    // TODO (11) Override onSharedPreferenceChanged to update non CheckBoxPreferences when they are changed
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference pref = findPreference(key);
        if (!(pref instanceof CheckBoxPreference)) {
            setPreferenceSummary(pref, sharedPreferences.getString(key, null));
        }
    }
}
