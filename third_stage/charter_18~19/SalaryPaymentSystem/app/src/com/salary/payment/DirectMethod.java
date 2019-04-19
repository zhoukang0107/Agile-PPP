package com.salary.payment;

public class DirectMethod implements PaymentMethod {
    private String mBank;
    private String mAccount;

    public DirectMethod(String mBank, String mAccount) {
        this.mBank = mBank;
        this.mAccount = mAccount;
    }
}
