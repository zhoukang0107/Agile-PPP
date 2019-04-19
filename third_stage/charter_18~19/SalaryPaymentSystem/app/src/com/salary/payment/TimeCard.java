package com.salary.payment;

public class TimeCard {
    private Date mDate;
    private float mHours;

    public TimeCard(Date date, float hours) {
        this.mDate = date;
        this.mHours = hours;
    }

    public float getHours() {
        return mHours;
    }

    public Date getDate(){
        return mDate;
    }
}
