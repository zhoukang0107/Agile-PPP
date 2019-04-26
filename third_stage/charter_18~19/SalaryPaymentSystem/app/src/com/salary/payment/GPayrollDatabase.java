package com.salary.payment;

import java.util.HashMap;
import java.util.Map;

public class GPayrollDatabase {
    private static Map<Long, Employee> itsEmployees = new HashMap<>();
    private static Map<Long, Employee> itsUnionMember = new HashMap<>();

    public static Employee getEmployee(Long empId) {
        return itsEmployees.get(empId);
    }

    public static void addEmployee(Long empId, Employee employee){
        itsEmployees.put(empId, employee);
    }

    public static void clear(){
        itsEmployees.clear();
    }

    public static void deleteEmployee(Long mEmpId) {
        itsEmployees.remove(mEmpId);
    }

    public static void addUnionMember(long memberId, Employee employee) {
        itsUnionMember.put(memberId, employee);
    }

    public static Employee getUnionMember(Long memberId){
        return itsUnionMember.get(memberId);
    }

    public static void removeUnionMember(long memberId) {
        itsUnionMember.remove(memberId);
    }
}
