package com.salary.payment;

public class WeeklySchedule implements PaymentSchedule {
    @Override
    public boolean isPayDate(Date date) {
        return true;
    }
}
