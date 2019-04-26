package com.salary.payment;

public class ChangeSalariedTransaction extends ChangeClassificationTransaction {
    private float mSlary;

    public ChangeSalariedTransaction(Long empId, float mSlary) {
        super(empId);
        this.mSlary = mSlary;
    }

    @Override
    protected PaymentClassification getClassification() {
        return new SalariedClassification(mSlary);
    }

    @Override
    protected PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }
}
