package com.salary.payment;

public abstract class AddEmployeeTransaction implements Transaction {
    protected Long empId;
    protected String itsAddress;
    protected String itsName;

    public AddEmployeeTransaction(long empId, String itsAddress, String itsName) {
        this.empId = empId;
        this.itsAddress = itsAddress;
        this.itsName = itsName;
    }

    @Override
    public void execute() {
        PaymentClassification classification = getClassification();
        PaymentSchedule schedule = getSchedule();
        PaymentMethod method = getMethod();
        Employee employee = new Employee(empId, itsName, itsAddress);
        employee.setClassification(classification);
        employee.setSchedule(schedule);
        employee.setMothod(method);
        GPayrollDatabase.addEmployee(empId, employee);
    }

    protected PaymentMethod getMethod(){
        return new HoldMethod();
    }

    protected abstract PaymentSchedule getSchedule();


    protected abstract PaymentClassification getClassification();
}
