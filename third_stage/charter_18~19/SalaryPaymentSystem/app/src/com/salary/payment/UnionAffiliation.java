package com.salary.payment;

import java.util.ArrayList;
import java.util.List;

public class UnionAffiliation implements Affiliation{
    private List<ServiceCharge> mServiceCharge;

    public UnionAffiliation(long memberId, int i) {
        mServiceCharge = new ArrayList<>();
    }

    public ServiceCharge getServiceCharge(int index) {
        return mServiceCharge.get(index);
    }

    public void addServiceCharge(ServiceCharge serviceCharge) {
        mServiceCharge.add(serviceCharge);
    }

    public double getDues() {
        return 0;
    }
}
