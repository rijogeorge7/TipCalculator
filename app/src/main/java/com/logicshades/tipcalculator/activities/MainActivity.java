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
        TextView textView_billAmount=(TextView)findViewById(R.id.textView_billAmount);
        textView_billAmount.setTypeface(gothicFont);

        TextView textView_tip=(TextView)findViewById(R.id.textView_tip);
        textView_tip.setTypeface(gothicFont);

        TextView tv_billAmount=(TextView)findViewById(R.id.textView_billAmount);
        tv_billAmount.setTypeface(gothicFont);

        TextView textViewtipAmount=(TextView)findViewById(R.id.textViewtipAmount);
        textViewtipAmount.setTypeface(gothicFont);

        TextView textViewsplitbill=(TextView)findViewById(R.id.textViewsplitbill);
        textViewsplitbill.setTypeface(gothicFont);

        TextView textView_splitNo=(TextView)findViewById(R.id.textView_splitNo);
        textView_splitNo.setTypeface(gothicFont);

        TextView textView_billTotal=(TextView)findViewById(R.id.textView_billTotal);
        textView_billTotal.setTypeface(gothicFont);

        TextView textView_billTotalAmount=(TextView)findViewById(R.id.textView_billTotalAmount);
        textView_billTotalAmount.setTypeface(gothicFont);

    }
}
