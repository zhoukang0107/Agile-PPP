package com.salary.payment;

public class AddSalariedEmployee extends AddEmployeeTransaction {
    private float salary;

    public AddSalariedEmployee(long empId, String itsAddress, String itsName, float salary) {
        super(empId, itsAddress, itsName);
        this.salary = salary;
    }

    @Override
    protected PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }

    @Override
    protected PaymentClassification getClassification() {
        return new SalariedClassification(salary);
    }
}
