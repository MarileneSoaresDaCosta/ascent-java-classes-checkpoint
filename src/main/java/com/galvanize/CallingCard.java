
package com.galvanize;

public class CallingCard {

    // fields
    private int costPerMin;
    private int balance;
    private int remainingMinutes;

    // constructor
    CallingCard(int costPerMin) {
        this.costPerMin = costPerMin;
    }

    // methods
    @Override
    public String toString() {
        return "Cost: " + this.costPerMin +" balance: " +this.balance;
    }

    public void addDollars(int dollars) {
        // balance in cents
        this.balance += dollars * 100;
        this.remainingMinutes = this.balance / this.costPerMin;
    }

    public int getRemainingMinutes() {
        return remainingMinutes;
    }

    public void useMinutes(int usedMin) {
        this.remainingMinutes -= usedMin;

        this.balance -= (usedMin * this.costPerMin);
    }



}
