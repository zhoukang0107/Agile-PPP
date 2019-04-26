package com.salary.payment;

public abstract class ChangeAffiliationTransaction extends ChangeEmployeeTransaction {
    public ChangeAffiliationTransaction(Long empId) {
        super(empId);
    }

    @Override
    protected Employee change(Employee employee) {
        recordMemberShip(employee);
        employee.setAffiliation(getAffiliation());
        return employee;
    }

    protected abstract Affiliation getAffiliation();

    protected abstract void recordMemberShip(Employee employee);
}
