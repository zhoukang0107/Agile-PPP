package com.salary.payment;

import java.util.*;

public class HourlyClassification implements PaymentClassification {
    private float mHonrlyRate;
    private List<TimeCard> mTimeCardList;
    public HourlyClassification(float honrlyRate) {
        this.mHonrlyRate = honrlyRate;
        mTimeCardList = new ArrayList<>();
    }

    public float getRate() {
        return mHonrlyRate;
    }

    @Override
    public double getSalary() {
        return 0;
    }

    @Override
    public double calculatePay(PayCheck payCheck) {
        double totalPay = 0;
        Date payPeriod = payCheck.getPayDate();
        for (TimeCard card : mTimeCardList){
            if (isInPayPeriod(card, payPeriod)){
                totalPay += calculatePayForTimeCard(card);
            }
        }

        return 0;
    }

    private boolean isInPayPeriod(TimeCard card, Date payPeriod) {
        Date payPeriodEndDate = payPeriod;
        //Date payPeriodStartDate = payPeriod - 5;

        return false;
    }

    private double calculatePayForTimeCard(TimeCard card) {
        double hours = card.getHours();
        double overTime = Math.max(0.0, hours - 8);
        double straightTime = hours - overTime;
        return straightTime * mHonrlyRate + overTime * mHonrlyRate * 1.5;
    }

    public void addTimeCard(TimeCard timeCard) {
        mTimeCardList.add(timeCard);
    }

    public TimeCard getTimeCard(Date date) {
        for (TimeCard timeCard: mTimeCardList){
            if (timeCard.getDate().equals(date)){
                return timeCard;
            }
        }
        return null;
    }
}
