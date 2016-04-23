package net.logicshades.tipcalculator.util;

import net.logicshades.tipcalculator.domain.SplitBill;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

    public static double round(double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
