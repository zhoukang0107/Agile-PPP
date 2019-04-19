package com.salary.payment;

public class Date{
    private long year;
    private int month;
    private int day;

    public Date(long year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public int hashCode() {
        return Long.valueOf(year).hashCode() + Integer.valueOf(month).hashCode() + Integer.valueOf(day).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Date){
            Date o = (Date) obj;
            if (year == o.year &&
            month == o.month&&
            day == o.day){
                return true;
            }
        }
        return false;
    }
}
