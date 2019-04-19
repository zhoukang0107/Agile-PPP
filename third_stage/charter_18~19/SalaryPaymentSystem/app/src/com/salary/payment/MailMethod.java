package com.salary.payment;

public class MailMethod implements PaymentMethod {
    private String mAddress;

    public MailMethod(String address) {
        this.mAddress = address;
    }
}
