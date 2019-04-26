package com.salary.payment;

public class ChangeNameTransaction extends ChangeEmployeeTransaction {
    private String mName;

    public ChangeNameTransaction(Long empId, String name) {
        super(empId);
        this.mName = name;
    }

    @Override
    protected Employee change(Employee employee) {
        employee.setName(mName);
        return employee;
    }
}
