package com.salary.payment;

public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction {

    public ChangeClassificationTransaction(Long empId) {
        super(empId);
    }

    @Override
    protected Employee change(Employee employee) {
        employee.setClassification(getClassification());
        employee.setSchedule(getSchedule());
        return employee;
    }

    protected abstract PaymentClassification getClassification();

    protected abstract  PaymentSchedule getSchedule();
}
