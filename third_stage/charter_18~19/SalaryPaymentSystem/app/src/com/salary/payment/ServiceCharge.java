package com.salary.payment;

public class ServiceCharge {
    private float mAmount;
    private Date mDate;

    public ServiceCharge(float amount, Date mDate) {
        this.mAmount = amount;
        this.mDate = mDate;
    }

    public float getAmount() {
        return mAmount;
    }
}
