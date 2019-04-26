package com.salary.payment;

public class ChangeHourlyTransaction extends ChangeClassificationTransaction {
    private float mHourlyRate;

    public ChangeHourlyTransaction(Long empId, float mHourlyRate) {
        super(empId);
        this.mHourlyRate = mHourlyRate;
    }

    @Override
    protected PaymentClassification getClassification() {
        return new HourlyClassification(mHourlyRate);
    }

    @Override
    protected PaymentSchedule getSchedule() {
        return new WeeklySchedule();
    }
}
