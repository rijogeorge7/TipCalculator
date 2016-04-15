package com.logicshades.tipcalculator.util;

import com.logicshades.tipcalculator.domain.SplitBill;

/**
 * Created by rijogeorge on 4/12/16.
 */
public class CalcSplitBill {
    SplitBill bill;

    public SplitBill getSplitAmount(double billTotal, int tipPercentage, int split) {
        double billWithTip = ((billTotal * tipPercentage) / 100)+ billTotal;
        double splitAmount = billWithTip / split;
        bill = new SplitBill(billTotal, billWithTip, tipPercentage, splitAmount,split );
        return bill;
    }
}
