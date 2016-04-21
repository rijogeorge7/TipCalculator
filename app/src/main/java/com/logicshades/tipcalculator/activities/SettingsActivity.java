package com.logicshades.tipcalculator.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toolbar;

import com.logicshades.tipcalculator.R;
import com.logicshades.tipcalculator.fragments.SettingsFragment;

/**
 * Created by rijogeorge on 4/20/16.
 */
public class SettingsActivity extends Activity {
    public static String key_currency="pref_currencyList";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pref_with_actionbar);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolBar);
        setActionBar(toolbar);

        getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new SettingsFragment())
                .commit();
    }
}