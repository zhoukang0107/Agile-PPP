package com.salary.payment;

public class MonthlySchedule implements PaymentSchedule {
    public boolean isLastDayOfMonth(Date date){
        return true;
    }

    public boolean isPayDate(Date date){
        return isLastDayOfMonth(date);
    }
}
