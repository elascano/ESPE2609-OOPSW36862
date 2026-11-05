package ec.edu.espe.electromechanicalmaintenancemanagementsystem.model;

import org.junit.Test;
import static org.junit.Assert.*;
import ec.edu.espe.electromechanicalmaintenancemanagementsystem.testutils.TestData;

public class ScheduleDateTest {

    @Test
    public void testUpdateScheduleDate1() {
        System.out.println("updateScheduleDate1");
        String[] row = TestData.row("schedule_updateScheduleDate.csv", 1);
        String initialDate = row[1];
        String newDate = row[2];
        MaintenanceSchedule instance = new MaintenanceSchedule(1, "Monthly", initialDate, true);
        String expResult = row[3];
        instance.updateScheduleDate(newDate);
        String result = instance.getScheduleDate();
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateScheduleDate2() {
        System.out.println("updateScheduleDate2");
        String[] row = TestData.row("schedule_updateScheduleDate.csv", 2);
        String initialDate = row[1];
        String newDate = row[2];
        MaintenanceSchedule instance = new MaintenanceSchedule(1, "Monthly", initialDate, true);
        String expResult = row[3];
        instance.updateScheduleDate(newDate);
        String result = instance.getScheduleDate();
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateScheduleDate3() {
        System.out.println("updateScheduleDate3");
        String[] row = TestData.row("schedule_updateScheduleDate.csv", 3);
        String initialDate = row[1];
        String newDate = row[2];
        MaintenanceSchedule instance = new MaintenanceSchedule(1, "Monthly", initialDate, true);
        String expResult = row[3];
        instance.updateScheduleDate(newDate);
        String result = instance.getScheduleDate();
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateScheduleDate4() {
        System.out.println("updateScheduleDate4");
        String[] row = TestData.row("schedule_updateScheduleDate.csv", 4);
        String initialDate = row[1];
        String newDate = row[2];
        MaintenanceSchedule instance = new MaintenanceSchedule(1, "Monthly", initialDate, true);
        String expResult = row[3];
        instance.updateScheduleDate(newDate);
        String result = instance.getScheduleDate();
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateScheduleDate5() {
        System.out.println("updateScheduleDate5");
        String[] row = TestData.row("schedule_updateScheduleDate.csv", 5);
        String initialDate = row[1];
        String newDate = row[2];
        MaintenanceSchedule instance = new MaintenanceSchedule(1, "Monthly", initialDate, true);
        String expResult = row[3];
        instance.updateScheduleDate(newDate);
        String result = instance.getScheduleDate();
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateScheduleDate6() {
        System.out.println("updateScheduleDate6");
        String[] row = TestData.row("schedule_updateScheduleDate.csv", 6);
        String initialDate = row[1];
        String newDate = row[2];
        MaintenanceSchedule instance = new MaintenanceSchedule(1, "Monthly", initialDate, true);
        String expResult = row[3];
        instance.updateScheduleDate(newDate);
        String result = instance.getScheduleDate();
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateScheduleDate7() {
        System.out.println("updateScheduleDate7");
        String[] row = TestData.row("schedule_updateScheduleDate.csv", 7);
        String initialDate = row[1];
        String newDate = row[2];
        MaintenanceSchedule instance = new MaintenanceSchedule(1, "Monthly", initialDate, true);
        String expResult = row[3];
        instance.updateScheduleDate(newDate);
        String result = instance.getScheduleDate();
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateScheduleDate8() {
        System.out.println("updateScheduleDate8");
        String[] row = TestData.row("schedule_updateScheduleDate.csv", 8);
        String initialDate = row[1];
        String newDate = row[2];
        MaintenanceSchedule instance = new MaintenanceSchedule(1, "Monthly", initialDate, true);
        String expResult = row[3];
        instance.updateScheduleDate(newDate);
        String result = instance.getScheduleDate();
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateScheduleDate9() {
        System.out.println("updateScheduleDate9");
        String[] row = TestData.row("schedule_updateScheduleDate.csv", 9);
        String initialDate = row[1];
        String newDate = row[2];
        MaintenanceSchedule instance = new MaintenanceSchedule(1, "Monthly", initialDate, true);
        String expResult = row[3];
        instance.updateScheduleDate(newDate);
        String result = instance.getScheduleDate();
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateScheduleDate10() {
        System.out.println("updateScheduleDate10");
        String[] row = TestData.row("schedule_updateScheduleDate.csv", 10);
        String initialDate = row[1];
        String newDate = row[2];
        MaintenanceSchedule instance = new MaintenanceSchedule(1, "Monthly", initialDate, true);
        String expResult = row[3];
        instance.updateScheduleDate(newDate);
        String result = instance.getScheduleDate();
        assertEquals(expResult, result);
    }

}
