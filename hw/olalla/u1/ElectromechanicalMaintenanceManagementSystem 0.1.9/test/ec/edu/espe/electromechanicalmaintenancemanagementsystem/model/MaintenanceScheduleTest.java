package ec.edu.espe.electromechanicalmaintenancemanagementsystem.model;

import org.junit.Test;
import static org.junit.Assert.*;
import ec.edu.espe.electromechanicalmaintenancemanagementsystem.testutils.TestData;

public class MaintenanceScheduleTest {

    @Test
    public void testCreateWorkOrder1() {
        System.out.println("createWorkOrder1");
        String[] row = TestData.row("schedule_createWorkOrder.csv", 1);
        String frequency = row[1];
        String scheduleDate = row[2];
        MaintenanceSchedule instance = new MaintenanceSchedule(1, frequency, scheduleDate, true);
        String expDescription = row[3];
        String expDate = row[4];
        WorkOrder result = instance.createWorkOrder();
        assertEquals(expDescription, result.getDescription());
        assertEquals(expDate, result.getCreationDate());
    }

    @Test
    public void testCreateWorkOrder2() {
        System.out.println("createWorkOrder2");
        String[] row = TestData.row("schedule_createWorkOrder.csv", 2);
        String frequency = row[1];
        String scheduleDate = row[2];
        MaintenanceSchedule instance = new MaintenanceSchedule(1, frequency, scheduleDate, true);
        String expDescription = row[3];
        String expDate = row[4];
        WorkOrder result = instance.createWorkOrder();
        assertEquals(expDescription, result.getDescription());
        assertEquals(expDate, result.getCreationDate());
    }

    @Test
    public void testCreateWorkOrder3() {
        System.out.println("createWorkOrder3");
        String[] row = TestData.row("schedule_createWorkOrder.csv", 3);
        String frequency = row[1];
        String scheduleDate = row[2];
        MaintenanceSchedule instance = new MaintenanceSchedule(1, frequency, scheduleDate, true);
        String expDescription = row[3];
        String expDate = row[4];
        WorkOrder result = instance.createWorkOrder();
        assertEquals(expDescription, result.getDescription());
        assertEquals(expDate, result.getCreationDate());
    }

    @Test
    public void testCreateWorkOrder4() {
        System.out.println("createWorkOrder4");
        String[] row = TestData.row("schedule_createWorkOrder.csv", 4);
        String frequency = row[1];
        String scheduleDate = row[2];
        MaintenanceSchedule instance = new MaintenanceSchedule(1, frequency, scheduleDate, true);
        String expDescription = row[3];
        String expDate = row[4];
        WorkOrder result = instance.createWorkOrder();
        assertEquals(expDescription, result.getDescription());
        assertEquals(expDate, result.getCreationDate());
    }

    @Test
    public void testCreateWorkOrder5() {
        System.out.println("createWorkOrder5");
        String[] row = TestData.row("schedule_createWorkOrder.csv", 5);
        String frequency = row[1];
        String scheduleDate = row[2];
        MaintenanceSchedule instance = new MaintenanceSchedule(1, frequency, scheduleDate, true);
        String expDescription = row[3];
        String expDate = row[4];
        WorkOrder result = instance.createWorkOrder();
        assertEquals(expDescription, result.getDescription());
        assertEquals(expDate, result.getCreationDate());
    }

    @Test
    public void testCreateWorkOrder6() {
        System.out.println("createWorkOrder6");
        String[] row = TestData.row("schedule_createWorkOrder.csv", 6);
        String frequency = row[1];
        String scheduleDate = row[2];
        MaintenanceSchedule instance = new MaintenanceSchedule(1, frequency, scheduleDate, true);
        String expDescription = row[3];
        String expDate = row[4];
        WorkOrder result = instance.createWorkOrder();
        assertEquals(expDescription, result.getDescription());
        assertEquals(expDate, result.getCreationDate());
    }

    @Test
    public void testCreateWorkOrder7() {
        System.out.println("createWorkOrder7");
        String[] row = TestData.row("schedule_createWorkOrder.csv", 7);
        String frequency = row[1];
        String scheduleDate = row[2];
        MaintenanceSchedule instance = new MaintenanceSchedule(1, frequency, scheduleDate, true);
        String expDescription = row[3];
        String expDate = row[4];
        WorkOrder result = instance.createWorkOrder();
        assertEquals(expDescription, result.getDescription());
        assertEquals(expDate, result.getCreationDate());
    }

    @Test
    public void testCreateWorkOrder8() {
        System.out.println("createWorkOrder8");
        String[] row = TestData.row("schedule_createWorkOrder.csv", 8);
        String frequency = row[1];
        String scheduleDate = row[2];
        MaintenanceSchedule instance = new MaintenanceSchedule(1, frequency, scheduleDate, true);
        String expDescription = row[3];
        String expDate = row[4];
        WorkOrder result = instance.createWorkOrder();
        assertEquals(expDescription, result.getDescription());
        assertEquals(expDate, result.getCreationDate());
    }

    @Test
    public void testCreateWorkOrder9() {
        System.out.println("createWorkOrder9");
        String[] row = TestData.row("schedule_createWorkOrder.csv", 9);
        String frequency = row[1];
        String scheduleDate = row[2];
        MaintenanceSchedule instance = new MaintenanceSchedule(1, frequency, scheduleDate, true);
        String expDescription = row[3];
        String expDate = row[4];
        WorkOrder result = instance.createWorkOrder();
        assertEquals(expDescription, result.getDescription());
        assertEquals(expDate, result.getCreationDate());
    }

    @Test
    public void testCreateWorkOrder10() {
        System.out.println("createWorkOrder10");
        String[] row = TestData.row("schedule_createWorkOrder.csv", 10);
        String frequency = row[1];
        String scheduleDate = row[2];
        MaintenanceSchedule instance = new MaintenanceSchedule(1, frequency, scheduleDate, true);
        String expDescription = row[3];
        String expDate = row[4];
        WorkOrder result = instance.createWorkOrder();
        assertEquals(expDescription, result.getDescription());
        assertEquals(expDate, result.getCreationDate());
    }

}
