package com.salary.payment;

public class SalariedClassification implements PaymentClassification {
    private float mSalary;

    public SalariedClassification(float salary) {
        this.mSalary = salary;
    }

    @Override
    public double getSalary() {
        return mSalary;
    }
}
