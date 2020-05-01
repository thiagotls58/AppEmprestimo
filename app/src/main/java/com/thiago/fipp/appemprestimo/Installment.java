package com.thiago.fipp.appemprestimo;

public class Installment {
    private int number;
    private double value, outstandingBalance;

    public Installment(int number, double value, double outstandingBalance) {
        this.number = number;
        this.value = value;
        this.outstandingBalance = outstandingBalance;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getOutstandingBalance() {
        return outstandingBalance;
    }

    public void setOutstandingBalance(double outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }

    @Override
    public String toString() {
        return String.format("%02d - %10.2f Outstanding Balance: %10.2f", this.number,this.value, this.outstandingBalance);
    }
}
