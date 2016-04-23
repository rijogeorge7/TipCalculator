package com.logicshades.tipcalculator.util;

import net.logicshades.tipcalculator.domain.SplitBill;
import net.logicshades.tipcalculator.util.CalcSplitBill;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by rijogeorge on 4/12/16.
 */
public class CalcSplitBillTest {

    private CalcSplitBill calcBillObj;
    @Before
    public void setup() throws Exception {
        calcBillObj=new CalcSplitBill();
    }

    @Test
    public void GetSplitAmount() throws Exception {
        double billTotal=100;
        int tip=15;
        int splitNo=4;

        SplitBill bill=calcBillObj.getSplitAmount(billTotal, tip, splitNo);

        assertEquals("total bill calculated", 115, bill.getBillWithTip(),0.01);
        assertEquals("bill split calculated", 28.75, bill.getSplitAmount(),0.01);
    }

    @Test
    public void round() throws Exception {
        double value=55.727272, roundValue=55.73;
        int places=2;

        double answer=CalcSplitBill.round(value, places);

        assertEquals("rounded no", roundValue, answer,0.00);
    }

    @After
    public void tearDown() throws Exception {

    }

}