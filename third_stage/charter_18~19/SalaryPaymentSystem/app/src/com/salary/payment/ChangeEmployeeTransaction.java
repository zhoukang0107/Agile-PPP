package com.salary.payment;

public abstract class ChangeEmployeeTransaction implements Transaction {
    protected Long mEmpId;

    public ChangeEmployeeTransaction(Long empId) {
        this.mEmpId = empId;
    }

    @Override
    public void execute() {
        Employee employee = GPayrollDatabase.getEmployee(mEmpId);
        if (employee != null){
            employee = change(employee);
            GPayrollDatabase.addEmployee(mEmpId, employee);
        }
    }

    protected abstract Employee change(Employee employee);

}
