package com.logicshades.tipcalculator.util;

import com.logicshades.tipcalculator.domain.SplitBill;

/**
 * Created by rijogeorge on 4/12/16.
 */
public class CalcSplitBill {
    SplitBill bill;

    public SplitBill getSplitAmount(double billTotal, double tipPercentage, int split) {
        double splitAmount = (((billTotal * tipPercentage) / 100) + billTotal) / split;
        bill = new SplitBill(billTotal, tipPercentage, split, splitAmount);
        return bill;
    }
}
