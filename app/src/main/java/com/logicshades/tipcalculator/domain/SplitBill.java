package com.logicshades.tipcalculator.domain;

/**
 * Created by rijogeorge on 4/12/16.
 */
public class SplitBill {

    private double billTotal;
    private double tip;
    private double splitAmount;
    private int splitNo;

    public SplitBill(double billTotal, double tip, int splitNo,double splitAmount) {
        this.billTotal = billTotal;
        this.tip = tip;
        this.splitAmount = splitAmount;
        this.splitNo = splitNo;
    }

    public double getSplitAmount() {
        return splitAmount;
    }

    public void setSplitAmount(double splitAmount) {
        this.splitAmount = splitAmount;
    }




    public double getBillTotal() {
        return billTotal;
    }

    public void setBillTotal(double billTotal) {
        this.billTotal = billTotal;
    }

    public double getTip() {
        return tip;
    }

    public void setTip(double tip) {
        this.tip = tip;
    }
}
