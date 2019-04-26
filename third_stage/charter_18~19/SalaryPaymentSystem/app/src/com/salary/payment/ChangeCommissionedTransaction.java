package com.salary.payment;

public class ChangeCommissionedTransaction extends ChangeClassificationTransaction {
    private float mCommissionRate;
    private float mSalary;

    public ChangeCommissionedTransaction(Long empId, float mCommissionRate, float mSalary) {
        super(empId);
        this.mCommissionRate = mCommissionRate;
        this.mSalary = mSalary;
    }

    @Override
    protected PaymentClassification getClassification() {
        return new CommissionedClassification(mCommissionRate, mSalary);
    }

    @Override
    protected PaymentSchedule getSchedule() {
        return new BlweeklySchedule();
    }
}
