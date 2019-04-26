package com.salary.payment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
