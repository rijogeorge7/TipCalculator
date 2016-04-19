package com.logicshades.tipcalculator.activities;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.logicshades.tipcalculator.R;
import com.logicshades.tipcalculator.domain.SplitBill;
import com.logicshades.tipcalculator.util.CalcSplitBill;

public class MainActivity extends AppCompatActivity {

    private EditText billAmount_et;
    private SeekBar seekBar_tip,seekBar_splitNo;
    private CalcSplitBill calcSplitBill;
    private TextView textView_billTotalAmount, textView_splitAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calcSplitBill=new CalcSplitBill();
        Typeface gothicFont = Typeface.createFromAsset(getAssets(), "Kozuka-Gothic-Pro-M_26793.ttf");

        TextView textView_billAmount=(TextView)findViewById(R.id.textView_billAmount);
        textView_billAmount.setTypeface(gothicFont);

        TextView textView_tip=(TextView)findViewById(R.id.textView_tip);
        textView_tip.setTypeface(gothicFont);

        TextView textViewtipAmount=(TextView)findViewById(R.id.textViewtipAmount);
        textViewtipAmount.setTypeface(gothicFont);

        TextView textViewsplitbill=(TextView)findViewById(R.id.textViewsplitbill);
        textViewsplitbill.setTypeface(gothicFont);

        final TextView textView_splitNo=(TextView)findViewById(R.id.textView_splitNo);
        textView_splitNo.setTypeface(gothicFont);

        textView_billTotalAmount=(TextView)findViewById(R.id.textView_billTotalAmount);
        textView_billTotalAmount.setTypeface(gothicFont);

        textView_splitAmount=(TextView)findViewById(R.id.textView_splitAmount);
        textView_splitAmount.setTypeface(gothicFont);

        seekBar_tip=(SeekBar)findViewById(R.id.seekBar_tip);
        seekBar_splitNo=(SeekBar)findViewById(R.id.seekBar_splitNo);
        seekBar_splitNo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(seekBar_splitNo.getProgress()==0)
                    textView_splitNo.setText("1 people");
                else
                    textView_splitNo.setText(String.valueOf(seekBar_splitNo.getProgress()+1)+" people");

                calculateSplitBill();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        billAmount_et=(EditText)findViewById(R.id.editTextBillAmount);
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

        initTipCalc();

    }

    private void initTipCalc() {
        calculateSplitBill();
    }

    private void calculateSplitBill() {

        if(billAmount_et.getText().toString().equals(""))
            return;
        double billAmount = Double.parseDouble(billAmount_et.getText().toString());
        int tipPercentage=seekBar_tip.getProgress();
        int splitno=seekBar_splitNo.getProgress();
        SplitBill splitBill=calcSplitBill.getSplitAmount(billAmount, tipPercentage, splitno+1);
        setupnewSplitBill(splitBill);

    }

    private void setupnewSplitBill(SplitBill splitBill) {
        textView_billTotalAmount.setText(String.valueOf(splitBill.getBillWithTip()));
        textView_splitAmount.setText(String.valueOf(splitBill.getSplitAmount()));
    }

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


}
