package com.salary.payment;

public class CommissionedClassification implements PaymentClassification {
    private float mCommissionRate;
    private float mSalary;

    public CommissionedClassification(float commissionRate, float salary) {
        this.mCommissionRate = commissionRate;
        this.mSalary = salary;
    }

    @Override
    public double getSalary() {
        return 0;
    }
}
