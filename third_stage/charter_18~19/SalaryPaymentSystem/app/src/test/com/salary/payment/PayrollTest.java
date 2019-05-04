package test.com.salary.payment;

import com.salary.payment.*;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class PayrollTest {

    /**
     * 增加雇员
     */
    @Test
    public void testAddSalariedEmployee(){
        long empId = 1;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "home", "Bob", 1000.0f);
        t.execute();

        Employee e = GPayrollDatabase.getEmployee(empId);
        assertEquals("Bob", e.getName());

        PaymentClassification pc = e.getClassification();
        assertEquals(true, pc instanceof SalariedClassification);

        assertEquals(1000.0 , pc.getSalary());
        PaymentSchedule ps = e.getSchedule();

        assertEquals(true, ps instanceof MonthlySchedule);

        PaymentMethod pm = e.getMethod();
        assertEquals(true , pm instanceof HoldMethod);

    }

    /**
     * 删除雇员
     */
    @Test
    public void testDeleteEmployee(){
        long empId = 2;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "company", "tom", 5000, 0.2f);
        t.execute();

        Employee employee = GPayrollDatabase.getEmployee(empId);
        assertNotNull(employee);

        DeleteEmployeeTransaction dt = new DeleteEmployeeTransaction(empId);
        dt.execute();

        Employee employee1 = GPayrollDatabase.getEmployee(empId);
        assertNull(employee1);
    }

    /**
     * 时间卡、销售凭条以及服务费用
     */
    @Test
    public void testTimeCardTransaction(){
        long empId = 3;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "home", "jerry", 100);
        t.execute();

        TimecardTransaction tt = new TimecardTransaction(empId,new Date(2019,4,19), 3.0f);
        tt.execute();

        Employee employee = GPayrollDatabase.getEmployee(empId);
        assertNotNull(employee);

        PaymentClassification classification = employee.getClassification();
        assertNotNull(classification);
        assertTrue(classification instanceof HourlyClassification);
        HourlyClassification hourlyClassification = (HourlyClassification) classification;
        TimeCard timeCard = hourlyClassification.getTimeCard(new Date(2019,4,19));
        assertNotNull(timeCard);

        assertEquals(3.0f , timeCard.getHours());

    }

    @Test
    public void testAddServiceCharge(){
        long empId = 4;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "beijin", "Bill", 200);
        t.execute();

        Employee employee = GPayrollDatabase.getEmployee(empId);
        assertNotNull(employee);

        long memberId = 5;
        UnionAffiliation affiliation = new UnionAffiliation(memberId, 20);
        employee.setAffiliation(affiliation);

        GPayrollDatabase.addUnionMember(memberId, employee);

        ServiceChargeTransaction st = new ServiceChargeTransaction(memberId, new Date(2019, 4, 19), 25);
        st.execute();

        ServiceCharge serviceCharge = affiliation.getServiceCharge(20190419);
        assertNotNull(serviceCharge);

        assertEquals(25, serviceCharge.getAmount());
    }

    @Test
    public void testChangeNameEmployee(){
        long empId = 6;
        AddHourlyEmployee t = new AddHourlyEmployee(empId,"shanghai", "jack", 250);
        t.execute();

        ChangeEmployeeTransaction change = new ChangeNameTransaction(empId, "daling");
        change.execute();

        Employee employee = GPayrollDatabase.getEmployee(empId);
        assertNotNull(employee);

        assertEquals(employee.getName(), "daling");
    }

    @Test
    public void testChangeHourlyTransaction(){
        long empId = 7;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId,"shanghai", "jack", 25000, 2.1f);
        t.execute();

        ChangeHourlyTransaction changeHourlyTransaction = new ChangeHourlyTransaction(empId, 200);
        changeHourlyTransaction.execute();

        Employee employee = GPayrollDatabase.getEmployee(empId);
        assertNotNull(employee);

        assertNotNull(employee.getClassification());
        assertTrue(employee.getClassification() instanceof HourlyClassification);
        assertEquals(200f, ((HourlyClassification)employee.getClassification()).getRate());

        assertTrue(employee.getSchedule() instanceof WeeklySchedule);
        assertNotNull(employee.getSchedule());

    }

    @Test
    public void testChangeMemberTransaction(){
        long empId = 8;
        long memberId = 100;
        AddHourlyEmployee t = new AddHourlyEmployee(empId,"shanghai", "jack", 200);
        t.execute();

        ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId, memberId, 99.42);
        cmt.execute();

        Employee employee = GPayrollDatabase.getEmployee(empId);
        assertNotNull(employee);

        Affiliation af = employee.getAffiliation();
        assertNotNull(af);

        assertTrue(af instanceof UnionAffiliation);
        UnionAffiliation uaf = (UnionAffiliation) af;

        assertEquals(99.42d, uaf.getDues());

        Employee employee1 = GPayrollDatabase.getUnionMember(memberId);
        assertNotNull(employee1);
        assertSame(employee, employee1);


    }

    @Test
    public void testPaySingleSalariedEmployee(){
        long empId = 8;
        AddSalariedEmployee t = new AddSalariedEmployee(empId,"china","Bob",1000);
        t.execute();
        Date payDate = new Date(2019,4,31);
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        PayCheck pc = pt.getPayCheck();
        assertNotNull(pc);
        assertEquals(payDate, pc.getPayDate());
        assertEquals(1000, pc.getGrossPay());
        assertEquals(0.0, pc.getDeductions());
        assertEquals(1000, pc.getNetPay());
    }

    @Test
    public void testPaySingleSalariedEmployeeOnWrongDate(){
        long empId = 9;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob2", "Home", 1000);
        t.execute();

        Date payDate = new Date(2019, 4,30);
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        PayCheck pc = pt.getPayCheck();
        assertNotNull(pc);
    }

    @Test
    public void testPaySingleHourlyEmployeeNoTimeCards(){
        long empId = 10;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "home", "Bon3", 18.0f);
        t.execute();
        Date payDate = new Date(2019, 4, 25);
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        ValidatePayCheck(pt, empId, payDate, 0.0);


    }

    private void ValidatePayCheck(PaydayTransaction pt, long empId, Date payDate, double pay) {
        PayCheck pc = pt.getPayCheck();
        assertNotNull(pc);
        assertEquals(pc.getPayDate(), payDate);
        assertEquals(pay, pc.getGrossPay());
        assertEquals("Hold", pc.getField("Disposition"));
        assertEquals(0.0 , pc.getDeductions());
        assertEquals(pay, pc.getNetPay());
    }

    @Test
    public void testPaySingleHourlyEmployeeOneTimeCard(){
        long empId = 11;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 18.25f);
        t.execute();
        Date payDate = new Date(2019, 4, 25);

        TimecardTransaction tc = new TimecardTransaction(empId, payDate, 3.0f);
        tc.execute();
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        ValidatePayCheck(pt, empId, payDate, 50);
    }

    @Test
    public void testPaySingleHourlyEmployeeOvertimeOneTimeCard(){
        long empId = 12;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 25);
        t.execute();
        Date payDate = new Date(2019,4, 24);

        TimecardTransaction tc = new TimecardTransaction(empId, payDate, 9.0f);
        tc.execute();
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        ValidatePayCheck(pt, empId, payDate, (8 + 1.5)*14.25);

    }

    @Test
    public void testPaySingleHourlyEmployeeOnWrongDate(){
        long empId = 13;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "beijing", "Bill", 20.0f);
        t.execute();
        Date payDate = new Date(2019, 4, 26);

        TimecardTransaction tc = new TimecardTransaction(empId, payDate, 9.0f);
        tc.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        PayCheck pc = pt.getPayCheck();
        assertNotNull(pc);


    }

    @Test
    public void testPaySingleHourlyEmployeeTwoTimeCards(){
        long empId = 15;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 18);
        t.execute();
        Date payDate = new Date(2019, 4, 26);

        TimecardTransaction tc = new TimecardTransaction(empId, payDate, 2);
        tc.execute();

        TimecardTransaction tc2 = new TimecardTransaction(empId, payDate, 5.0f);
        tc2.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        ValidatePayCheck(pt, empId, payDate, 7*26);
    }


    @Test
    public void testPaySingleHourlyEmployeeWithTimeCardsSpanningTwoPayPeriods(){
        long empId = 15;
        AddHourlyEmployee t = new AddHourlyEmployee(2019, "shanghai", "Home", 25.0f);
        t.execute();
        Date payDaye = new Date(2019, 4, 21);
        Date dateInPreviousPayPeriod = new Date(2019, 4, 15);

        TimecardTransaction tc = new TimecardTransaction(empId, payDaye, 2.0f;
        tc.execute();
        TimecardTransaction tc2 = new TimecardTransaction(empId, dateInPreviousPayPeriod, 5.0f);
        tc2.execute();

        PaydayTransaction pt = new PaydayTransaction(payDaye);
        pt.execute();

        ValidatePayCheck(pt, empId, payDaye, 2* 25.0f);
    }

    @Test
    public void testSalariedUnionMemberDues(){
        long empId = 15;
        AddSalariedEmployee t = new AddSalariedEmployee(empId,"", "", 1000);
        t.execute();

        int memberId = 7734;
        ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId, memberId, 9);
        cmt.execute();
        Date payDate = new Date(4,31, 2019);
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        ValidatePayCheck(pt, empId, payDate, 1000 - 9);
    }




}
