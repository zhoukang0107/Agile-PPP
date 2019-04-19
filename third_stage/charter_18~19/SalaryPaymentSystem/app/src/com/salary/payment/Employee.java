package com.salary.payment;

public class Employee {
    private Long mEmpId;
    private String mName;
    private String mAddress;
    /**
     * 策略模式来适用员工不同的支付方式、支付日期表和支付工具
     */
    private PaymentMethod mMethod;
    private PaymentClassification mClassification;
    private PaymentSchedule mSchedule;
    private Affiliation mAffiliation;

    public Employee(Long mEmpId, String mName, String mAddress) {
        this.mEmpId = mEmpId;
        this.mName = mName;
        this.mAddress = mAddress;
    }

    public void setSchedule(PaymentSchedule schedule){
        mSchedule = schedule;
    }

    public void setClassification(PaymentClassification classification){
        mClassification = classification;
    }

    public void setMothod(PaymentMethod method){
        mMethod = method;
    }

    public String getName() {
        return mName;
    }

    public PaymentClassification getClassification() {
        return mClassification;
    }

    public PaymentSchedule getSchedule() {
        return mSchedule;
    }

    public PaymentMethod getMethod() {
        return mMethod;
    }

    public void setAffiliation(Affiliation affiliation) {
        this.mAffiliation = affiliation;
    }

    public Affiliation getAffiliation(){
        return mAffiliation;
    }
}
