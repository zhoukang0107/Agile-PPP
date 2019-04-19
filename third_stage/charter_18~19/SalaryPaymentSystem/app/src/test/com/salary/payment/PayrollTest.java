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

}
