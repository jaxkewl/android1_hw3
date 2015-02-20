package com.marshong.martinhonghw3;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

/**
 * Created by martin on 2/16/2015.
 */
public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    public SettingsFragment() {
        //required empty constructor
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // When user selects a preference different than what they've already chosen,
        // this listener will run.
        Preference pref = findPreference(key);

        //Toast.makeText(getActivity(), key, Toast.LENGTH_SHORT).show();

        if (pref instanceof ListPreference) {
            ListPreference lp = (ListPreference) pref;
            pref.setSummary(lp.getEntry());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from mysettings.xml
        addPreferencesFromResource(R.xml.mysettings);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }
}
