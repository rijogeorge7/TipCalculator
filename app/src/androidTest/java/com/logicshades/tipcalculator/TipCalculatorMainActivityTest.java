package com.logicshades.tipcalculator;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.logicshades.tipcalculator.activities.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by rijogeorge on 4/19/16.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TipCalculatorMainActivityTest {

    private String billAmount;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void initinputs(){
        billAmount="500.0";
    }

    @Test
    public void billAmountEditTextCheck(){
        onView(withId(R.id.editTextBillAmount))
                .perform(clearText(), closeSoftKeyboard());
        onView(withId(R.id.editTextBillAmount))
                .perform(typeText(billAmount), closeSoftKeyboard());
        onView(withId(R.id.textView_billTotalAmount))
                .check(matches(withText(billAmount)));
    }
}
