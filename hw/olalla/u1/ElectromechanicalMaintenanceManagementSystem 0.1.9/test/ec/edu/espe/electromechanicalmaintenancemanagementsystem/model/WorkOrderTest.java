package ec.edu.espe.electromechanicalmaintenancemanagementsystem.model;

import org.junit.Test;
import static org.junit.Assert.*;
import ec.edu.espe.electromechanicalmaintenancemanagementsystem.testutils.TestData;

public class WorkOrderTest {

    @Test
    public void testCloseOrder1() {
        System.out.println("closeOrder1");
        String[] row = TestData.row("workorder_closeOrder.csv", 1);
        int totalTasks = Integer.parseInt(row[1]);
        int completedTasks = Integer.parseInt(row[2]);
        WorkOrder instance = new WorkOrder(1, "Annual service", "Medium", "Pending", "2026-10-24");
        Technician technician = new Technician(1, "Ana", "Mechanical", true);
        technician.acceptWorkOrder(instance);
        for (int i = 0; i < totalTasks; i++) {
            MaintenanceTask task = instance.createTask();
            if (i < completedTasks) task.setIsCompleted(true);
        }
        String expStatus = row[3];
        boolean expTechAvailable = Boolean.parseBoolean(row[4]);
        instance.closeOrder();
        assertEquals(expStatus, instance.getStatus());
        assertEquals(expTechAvailable, technician.getAvailabilityStatus());
    }

    @Test
    public void testCloseOrder2() {
        System.out.println("closeOrder2");
        String[] row = TestData.row("workorder_closeOrder.csv", 2);
        int totalTasks = Integer.parseInt(row[1]);
        int completedTasks = Integer.parseInt(row[2]);
        WorkOrder instance = new WorkOrder(1, "Annual service", "Medium", "Pending", "2026-10-24");
        Technician technician = new Technician(1, "Ana", "Mechanical", true);
        technician.acceptWorkOrder(instance);
        for (int i = 0; i < totalTasks; i++) {
            MaintenanceTask task = instance.createTask();
            if (i < completedTasks) task.setIsCompleted(true);
        }
        String expStatus = row[3];
        boolean expTechAvailable = Boolean.parseBoolean(row[4]);
        instance.closeOrder();
        assertEquals(expStatus, instance.getStatus());
        assertEquals(expTechAvailable, technician.getAvailabilityStatus());
    }

    @Test
    public void testCloseOrder3() {
        System.out.println("closeOrder3");
        String[] row = TestData.row("workorder_closeOrder.csv", 3);
        int totalTasks = Integer.parseInt(row[1]);
        int completedTasks = Integer.parseInt(row[2]);
        WorkOrder instance = new WorkOrder(1, "Annual service", "Medium", "Pending", "2026-10-24");
        Technician technician = new Technician(1, "Ana", "Mechanical", true);
        technician.acceptWorkOrder(instance);
        for (int i = 0; i < totalTasks; i++) {
            MaintenanceTask task = instance.createTask();
            if (i < completedTasks) task.setIsCompleted(true);
        }
        String expStatus = row[3];
        boolean expTechAvailable = Boolean.parseBoolean(row[4]);
        instance.closeOrder();
        assertEquals(expStatus, instance.getStatus());
        assertEquals(expTechAvailable, technician.getAvailabilityStatus());
    }

    @Test
    public void testCloseOrder4() {
        System.out.println("closeOrder4");
        String[] row = TestData.row("workorder_closeOrder.csv", 4);
        int totalTasks = Integer.parseInt(row[1]);
        int completedTasks = Integer.parseInt(row[2]);
        WorkOrder instance = new WorkOrder(1, "Annual service", "Medium", "Pending", "2026-10-24");
        Technician technician = new Technician(1, "Ana", "Mechanical", true);
        technician.acceptWorkOrder(instance);
        for (int i = 0; i < totalTasks; i++) {
            MaintenanceTask task = instance.createTask();
            if (i < completedTasks) task.setIsCompleted(true);
        }
        String expStatus = row[3];
        boolean expTechAvailable = Boolean.parseBoolean(row[4]);
        instance.closeOrder();
        assertEquals(expStatus, instance.getStatus());
        assertEquals(expTechAvailable, technician.getAvailabilityStatus());
    }

    @Test
    public void testCloseOrder5() {
        System.out.println("closeOrder5");
        String[] row = TestData.row("workorder_closeOrder.csv", 5);
        int totalTasks = Integer.parseInt(row[1]);
        int completedTasks = Integer.parseInt(row[2]);
        WorkOrder instance = new WorkOrder(1, "Annual service", "Medium", "Pending", "2026-10-24");
        Technician technician = new Technician(1, "Ana", "Mechanical", true);
        technician.acceptWorkOrder(instance);
        for (int i = 0; i < totalTasks; i++) {
            MaintenanceTask task = instance.createTask();
            if (i < completedTasks) task.setIsCompleted(true);
        }
        String expStatus = row[3];
        boolean expTechAvailable = Boolean.parseBoolean(row[4]);
        instance.closeOrder();
        assertEquals(expStatus, instance.getStatus());
        assertEquals(expTechAvailable, technician.getAvailabilityStatus());
    }

    @Test
    public void testCloseOrder6() {
        System.out.println("closeOrder6");
        String[] row = TestData.row("workorder_closeOrder.csv", 6);
        int totalTasks = Integer.parseInt(row[1]);
        int completedTasks = Integer.parseInt(row[2]);
        WorkOrder instance = new WorkOrder(1, "Annual service", "Medium", "Pending", "2026-10-24");
        Technician technician = new Technician(1, "Ana", "Mechanical", true);
        technician.acceptWorkOrder(instance);
        for (int i = 0; i < totalTasks; i++) {
            MaintenanceTask task = instance.createTask();
            if (i < completedTasks) task.setIsCompleted(true);
        }
        String expStatus = row[3];
        boolean expTechAvailable = Boolean.parseBoolean(row[4]);
        instance.closeOrder();
        assertEquals(expStatus, instance.getStatus());
        assertEquals(expTechAvailable, technician.getAvailabilityStatus());
    }

    @Test
    public void testCloseOrder7() {
        System.out.println("closeOrder7");
        String[] row = TestData.row("workorder_closeOrder.csv", 7);
        int totalTasks = Integer.parseInt(row[1]);
        int completedTasks = Integer.parseInt(row[2]);
        WorkOrder instance = new WorkOrder(1, "Annual service", "Medium", "Pending", "2026-10-24");
        Technician technician = new Technician(1, "Ana", "Mechanical", true);
        technician.acceptWorkOrder(instance);
        for (int i = 0; i < totalTasks; i++) {
            MaintenanceTask task = instance.createTask();
            if (i < completedTasks) task.setIsCompleted(true);
        }
        String expStatus = row[3];
        boolean expTechAvailable = Boolean.parseBoolean(row[4]);
        instance.closeOrder();
        assertEquals(expStatus, instance.getStatus());
        assertEquals(expTechAvailable, technician.getAvailabilityStatus());
    }

    @Test
    public void testCloseOrder8() {
        System.out.println("closeOrder8");
        String[] row = TestData.row("workorder_closeOrder.csv", 8);
        int totalTasks = Integer.parseInt(row[1]);
        int completedTasks = Integer.parseInt(row[2]);
        WorkOrder instance = new WorkOrder(1, "Annual service", "Medium", "Pending", "2026-10-24");
        Technician technician = new Technician(1, "Ana", "Mechanical", true);
        technician.acceptWorkOrder(instance);
        for (int i = 0; i < totalTasks; i++) {
            MaintenanceTask task = instance.createTask();
            if (i < completedTasks) task.setIsCompleted(true);
        }
        String expStatus = row[3];
        boolean expTechAvailable = Boolean.parseBoolean(row[4]);
        instance.closeOrder();
        assertEquals(expStatus, instance.getStatus());
        assertEquals(expTechAvailable, technician.getAvailabilityStatus());
    }

    @Test
    public void testCloseOrder9() {
        System.out.println("closeOrder9");
        String[] row = TestData.row("workorder_closeOrder.csv", 9);
        int totalTasks = Integer.parseInt(row[1]);
        int completedTasks = Integer.parseInt(row[2]);
        WorkOrder instance = new WorkOrder(1, "Annual service", "Medium", "Pending", "2026-10-24");
        Technician technician = new Technician(1, "Ana", "Mechanical", true);
        technician.acceptWorkOrder(instance);
        for (int i = 0; i < totalTasks; i++) {
            MaintenanceTask task = instance.createTask();
            if (i < completedTasks) task.setIsCompleted(true);
        }
        String expStatus = row[3];
        boolean expTechAvailable = Boolean.parseBoolean(row[4]);
        instance.closeOrder();
        assertEquals(expStatus, instance.getStatus());
        assertEquals(expTechAvailable, technician.getAvailabilityStatus());
    }

    @Test
    public void testCloseOrder10() {
        System.out.println("closeOrder10");
        String[] row = TestData.row("workorder_closeOrder.csv", 10);
        int totalTasks = Integer.parseInt(row[1]);
        int completedTasks = Integer.parseInt(row[2]);
        WorkOrder instance = new WorkOrder(1, "Annual service", "Medium", "Pending", "2026-10-24");
        Technician technician = new Technician(1, "Ana", "Mechanical", true);
        technician.acceptWorkOrder(instance);
        for (int i = 0; i < totalTasks; i++) {
            MaintenanceTask task = instance.createTask();
            if (i < completedTasks) task.setIsCompleted(true);
        }
        String expStatus = row[3];
        boolean expTechAvailable = Boolean.parseBoolean(row[4]);
        instance.closeOrder();
        assertEquals(expStatus, instance.getStatus());
        assertEquals(expTechAvailable, technician.getAvailabilityStatus());
    }

}
