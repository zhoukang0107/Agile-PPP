package com.salary.payment;

import java.util.ArrayList;
import java.util.List;

public class UnionAffiliation implements Affiliation{
    private Double mDues;
    private Long mMemberId;
    private List<ServiceCharge> mServiceCharge;

    public UnionAffiliation(long memberId, double dues) {
        mDues = dues;
        mMemberId = memberId;
        mServiceCharge = new ArrayList<>();
    }

    public ServiceCharge getServiceCharge(int index) {
        return mServiceCharge.get(index);
    }

    public void addServiceCharge(ServiceCharge serviceCharge) {
        mServiceCharge.add(serviceCharge);
    }

    public double getDues() {
        return mDues;
    }

    public long getMemberId() {
        return mMemberId;
    }

    @Override
    public double calculateDeductions() {
        //double totalDues = 0;
        //int fridays = NumberOfFridaysInPayPeriod()
        return 0.0f;
    }
}
