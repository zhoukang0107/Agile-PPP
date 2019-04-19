package com.salary.payment;


public class TimecardTransaction implements Transaction {
    private Long mEmpId;
    private Date mDate;
    private float mHours;

    public TimecardTransaction(Long mEmpId, Date mDate, float mHours) {
        this.mEmpId = mEmpId;
        this.mDate = mDate;
        this.mHours = mHours;
    }

    @Override
    public void execute() {
        Employee employee = GPayrollDatabase.getEmployee(mEmpId);
        if (employee == null){
            throw new NullPointerException("no such employee");
        }
        PaymentClassification classification = employee.getClassification();
        if (classification instanceof HourlyClassification){
            HourlyClassification hourlyClassification = (HourlyClassification) classification;
            TimeCard timeCard = new TimeCard(mDate, mHours);
            hourlyClassification.addTimeCard(timeCard);
        } else {
            throw new IllegalArgumentException("tried to add timecard to non-hourly employee!");
        }
    }
}
