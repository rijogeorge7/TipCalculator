package com.logicshades.tipcalculator.util;

import com.logicshades.tipcalculator.domain.SplitBill;

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
    public void testGetSplitAmount() throws Exception {
        double billTotal=100;
        double tip=15;
        int splitNo=4;

        SplitBill bill=calcBillObj.getSplitAmount(billTotal, tip, splitNo);


        assertEquals("bill split calculated", 28.75, bill.getSplitAmount(),0.01);
    }

    @After
    public void tearDown() throws Exception {

    }

}