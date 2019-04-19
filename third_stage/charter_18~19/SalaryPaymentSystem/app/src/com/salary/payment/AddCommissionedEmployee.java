package com.salary.payment;

public class AddCommissionedEmployee extends AddEmployeeTransaction {
    private int salary;
    private float commissionRate;

    public AddCommissionedEmployee(long empId, String itsAddress, String itsName, int salary, float commissionRate) {
        super(empId, itsAddress, itsName);
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    @Override
    protected PaymentSchedule getSchedule() {
        return new BlweeklySchedule();
    }

    @Override
    protected PaymentClassification getClassification() {
        return new CommissionedClassification(commissionRate, salary);
    }
}
