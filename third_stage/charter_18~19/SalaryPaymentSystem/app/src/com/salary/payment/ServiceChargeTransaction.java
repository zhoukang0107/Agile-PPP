package com.salary.payment;

public class ServiceChargeTransaction implements Transaction {
    private Long mMemeberId;
    private Date mDate;
    private float mAmount;

    public ServiceChargeTransaction(long memberId, Date date, int amount) {
        this.mAmount = amount;
        this.mMemeberId = memberId;
        this.mDate = date;
    }

    @Override
    public void execute() {
        Employee employee = GPayrollDatabase.getUnionMember(mMemeberId);
        if (employee == null){
            throw new NullPointerException("");
        }
        ServiceCharge serviceCharge = new ServiceCharge(mAmount,mDate);
        Affiliation affiliation = employee.getAffiliation();
        if (affiliation instanceof UnionAffiliation){
            UnionAffiliation unionAffiliation = (UnionAffiliation) affiliation;
            unionAffiliation.addServiceCharge(serviceCharge);
        }
    }
}
