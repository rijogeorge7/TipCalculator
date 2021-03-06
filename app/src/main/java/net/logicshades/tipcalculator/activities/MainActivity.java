package net.logicshades.tipcalculator.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import net.logicshades.tipcalculator.R;
import net.logicshades.tipcalculator.domain.SplitBill;
import net.logicshades.tipcalculator.util.CalcSplitBill;

public class MainActivity extends AppCompatActivity {

    private EditText billAmount_et,editTextCurrency;
    private SeekBar seekBar_tip,seekBar_splitNo;
    private CalcSplitBill calcSplitBill;
    private TextView textView_billTotalAmount, textView_splitAmount,textViewtipAmount;
    private String currency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(net.logicshades.tipcalculator.R.layout.activity_main);
        //currency="$";
        Toolbar toolbar=(Toolbar)findViewById(net.logicshades.tipcalculator.R.id.toolBar);
        setActionBar(toolbar);
        calcSplitBill=new CalcSplitBill();
        editTextCurrency=(EditText)findViewById(net.logicshades.tipcalculator.R.id.editTextCurrency);
        Typeface gothicFont = Typeface.createFromAsset(getAssets(), "Kozuka-Gothic-Pro-M_26793.ttf");

        TextView textView_billAmount=(TextView)findViewById(net.logicshades.tipcalculator.R.id.textView_billAmount);
        textView_billAmount.setTypeface(gothicFont);

        TextView textView_tip=(TextView)findViewById(net.logicshades.tipcalculator.R.id.textView_tip);
        textView_tip.setTypeface(gothicFont);

        textViewtipAmount=(TextView)findViewById(net.logicshades.tipcalculator.R.id.textViewtipAmount);
        textViewtipAmount.setTypeface(gothicFont);

        TextView textViewsplitbill=(TextView)findViewById(net.logicshades.tipcalculator.R.id.textViewsplitbill);
        textViewsplitbill.setTypeface(gothicFont);

        final TextView textView_splitNo=(TextView)findViewById(net.logicshades.tipcalculator.R.id.textView_splitNo);
        textView_splitNo.setText("1 "+getString(R.string.person));
        textView_splitNo.setTypeface(gothicFont);

        textView_billTotalAmount=(TextView)findViewById(net.logicshades.tipcalculator.R.id.textView_billTotalAmount);
        textView_billTotalAmount.setTypeface(gothicFont);

        textView_splitAmount=(TextView)findViewById(net.logicshades.tipcalculator.R.id.textView_splitAmount);
        textView_splitAmount.setTypeface(gothicFont);

        seekBar_tip=(SeekBar)findViewById(net.logicshades.tipcalculator.R.id.seekBar_tip);
        seekBar_tip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                calculateSplitBill();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar_splitNo=(SeekBar)findViewById(net.logicshades.tipcalculator.R.id.seekBar_splitNo);
        seekBar_splitNo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                textView_splitNo.setText(String.valueOf(seekBar_splitNo.getProgress()+1)+" "+getString(R.string.person));
                calculateSplitBill();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        billAmount_et=(EditText)findViewById(net.logicshades.tipcalculator.R.id.editTextBillAmount);
        billAmount_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                calculateSplitBill();

            }
        });
        //for admob
        AdView mAdView = (AdView) findViewById(net.logicshades.tipcalculator.R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("190BD804C3B2C39CA5C5202ADC32BAA2")
                .build();

        mAdView.loadAd(adRequest);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        initTipCalc();
    }

    private void clearData() {
        initTipCalc();
        billAmount_et.setText("");


    }

    private void initTipCalc() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        currency=sharedPref.getString(SettingsActivity.key_currency,"$");
        editTextCurrency.setText(currency);
        textView_billTotalAmount.setText(currency+"0");
        textView_splitAmount.setText(currency+"0/Person");
    }

    private void calculateSplitBill() {

        if(billAmount_et.getText().toString().equals(""))
            return;
        double billAmount = Double.parseDouble(billAmount_et.getText().toString());
        int tipPercentage=seekBar_tip.getProgress();
        int splitno=seekBar_splitNo.getProgress();
        SplitBill splitBill=calcSplitBill.getSplitAmount(billAmount, tipPercentage, splitno + 1);
        setupnewSplitBill(splitBill);

    }

    private void setupnewSplitBill(SplitBill splitBill) {
        textViewtipAmount.setText(currency+String.valueOf(CalcSplitBill.round((splitBill.getBillWithTip()-splitBill.getBillTotal()), 2)));
        textView_billTotalAmount.setText(currency+String.valueOf(CalcSplitBill.round(splitBill.getBillWithTip(), 2)));
        textView_splitAmount.setText(currency + String.valueOf(CalcSplitBill.round(splitBill.getSplitAmount(), 2)) + "/Person");
    }


    //for dismissing softkeyboard while taping outside of the edittext
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        boolean handleReturn = super.dispatchTouchEvent(ev);

        View view = getCurrentFocus();

        int x = (int) ev.getX();
        int y = (int) ev.getY();

        if(view instanceof EditText){
            View innerView = getCurrentFocus();

            if (ev.getAction() == MotionEvent.ACTION_UP &&
                    !getLocationOnScreen((EditText) innerView).contains(x, y)) {

                InputMethodManager input = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                input.hideSoftInputFromWindow(getWindow().getCurrentFocus()
                        .getWindowToken(), 0);
            }
        }

        return handleReturn;
    }

    protected Rect getLocationOnScreen(EditText mEditText) {
        Rect mRect = new Rect();
        int[] location = new int[2];

        mEditText.getLocationOnScreen(location);

        mRect.left = location[0];
        mRect.top = location[1];
        mRect.right = location[0] + mEditText.getWidth();
        mRect.bottom = location[1] + mEditText.getHeight();

        return mRect;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(net.logicshades.tipcalculator.R.menu.menu_main,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case net.logicshades.tipcalculator.R.id.action_settings:
                Intent i = new Intent(this, SettingsActivity.class);
                startActivity(i);
                return true;

            case net.logicshades.tipcalculator.R.id.action_clear:
                clearData();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
