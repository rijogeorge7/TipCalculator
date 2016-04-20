package com.logicshades.tipcalculator.domain;

/**
 * Created by rijogeorge on 4/12/16.
 */
public class SplitBill {

    private double billTotal;
    private double billWithTip;
    private int tip;
    private double splitAmount;
    private int splitNo;

    public SplitBill(double billTotal, double billWithTip, int tip, double splitAmount, int splitNo) {
        this.billTotal = billTotal;
        this.billWithTip = billWithTip;
        this.tip = tip;
        this.splitAmount = splitAmount;
        this.splitNo = splitNo;
    }

    public double getBillTotal() {
        return billTotal;
    }

    public void setBillTotal(double billTotal) {
        this.billTotal = billTotal;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public double getBillWithTip() {
        return billWithTip;
    }

    public void setBillWithTip(double billWithTip) {
        this.billWithTip = billWithTip;
    }

    public double getSplitAmount() {
        return splitAmount;
    }

    public void setSplitAmount(double splitAmount) {
        this.splitAmount = splitAmount;
    }

    public int getSplitNo() {
        return splitNo;
    }

    public void setSplitNo(int splitNo) {
        this.splitNo = splitNo;
    }

}
