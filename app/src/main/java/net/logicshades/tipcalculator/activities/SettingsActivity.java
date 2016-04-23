package net.logicshades.tipcalculator.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toolbar;

import net.logicshades.tipcalculator.R;
import net.logicshades.tipcalculator.fragments.SettingsFragment;

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
        getActionBar().setDisplayHomeAsUpEnabled(true);

        getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new SettingsFragment())
                .commit();
    }
}