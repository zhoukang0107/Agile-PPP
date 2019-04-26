package com.salary.payment;

public class ChangeUnffiliatedTransaction extends ChangeAffiliationTransaction{
    public ChangeUnffiliatedTransaction(Long empId) {
        super(empId);
    }

    @Override
    protected Affiliation getAffiliation() {
        return new NoAffiliation();
    }

    @Override
    protected void recordMemberShip(Employee employee) {
        Affiliation affiliation = employee.getAffiliation();
        if (affiliation instanceof UnionAffiliation){
            long memberId = ((UnionAffiliation)affiliation).getMemberId();
            GPayrollDatabase.removeUnionMember(memberId);
        }
    }
}
