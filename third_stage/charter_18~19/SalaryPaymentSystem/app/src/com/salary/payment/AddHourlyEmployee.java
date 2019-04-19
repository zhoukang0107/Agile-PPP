package com.salary.payment;

public class AddHourlyEmployee extends AddEmployeeTransaction {
    private float hourlyRate;

    public AddHourlyEmployee(long empId, String itsAddress, String itsName, float hourlyRate) {
        super(empId, itsAddress, itsName);
        this.hourlyRate = hourlyRate;
    }


    @Override
    protected PaymentSchedule getSchedule() {
        return new WeeklySchedule();
    }

    @Override
    protected PaymentClassification getClassification() {
        return new HourlyClassification(hourlyRate);
    }
}
