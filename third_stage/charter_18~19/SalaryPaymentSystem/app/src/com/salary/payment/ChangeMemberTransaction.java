package com.salary.payment;

public class ChangeMemberTransaction extends ChangeAffiliationTransaction{
    private long mMemberId;
    private double mDues;
    public ChangeMemberTransaction(long empId, long memberId, double dues) {
        super(empId);
        mMemberId = memberId;
        mDues = dues;
    }

    @Override
    protected Affiliation getAffiliation() {
        return new UnionAffiliation(mEmpId, mDues);
    }

    @Override
    protected void recordMemberShip(Employee employee) {
        GPayrollDatabase.addUnionMember(mMemberId, employee);
    }
}
