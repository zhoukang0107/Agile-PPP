package com.salary.payment;

import java.util.List;

public class PaydayTransaction implements Transaction {
    private Date mPayDate;
    private PayCheck mPayCheck;
    public PaydayTransaction(Date payDate) {
        mPayDate = payDate;
    }

    @Override
    public void execute() {
        List<Long> empIds = GPayrollDatabase.getAllEmployeeIds();
        for (Long empId : empIds){
            Employee employee = GPayrollDatabase.getEmployee(empId);
            if (employee.isPayDate(mPayDate)){
                mPayCheck = new PayCheck(employee.getPayPeriodStartDate(mPayDate),mPayCheck);
                employee.payDate(mPayCheck);
            }
        }

    }

    public PayCheck getPayCheck() {
        return mPayCheck;
    }
}
