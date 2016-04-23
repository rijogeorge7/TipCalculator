package net.logicshades.tipcalculator.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import net.logicshades.tipcalculator.R;

/**
 * Created by rijogeorge on 4/20/16.
 */
public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.settings_preference);
    }

}
