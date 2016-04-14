package com.logicshades.tipcalculator.activities;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.logicshades.tipcalculator.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface gothicFont = Typeface.createFromAsset(getAssets(), "Kozuka-Gothic-Pro-M_26793.ttf");
        TextView tv_billAmount=(TextView)findViewById(R.id.textView_billAmount);
        tv_billAmount.setTypeface(gothicFont);
    }
}
