package com.salary.payment;

public class DeleteEmployeeTransaction implements Transaction {
    private Long mEmpId;

    public DeleteEmployeeTransaction(Long empId) {
        this.mEmpId = empId;
    }

    @Override
    public void execute() {
        GPayrollDatabase.deleteEmployee(mEmpId);
    }
}
