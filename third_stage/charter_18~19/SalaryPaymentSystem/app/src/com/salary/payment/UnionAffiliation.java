package com.salary.payment;

public class UnionAffiliation implements Affiliation{
    private ServiceCharge mServiceCharge;

    public UnionAffiliation(long memberId, int i) {
    }

    public ServiceCharge getServiceCharge(int i) {
        return mServiceCharge;
    }

    public void addServiceCharge(ServiceCharge serviceCharge) {
        mServiceCharge = serviceCharge;
    }
}
