package ec.edu.espe.electromechanicalmaintenancemanagementsystem.model;

import org.junit.Test;
import static org.junit.Assert.*;
import ec.edu.espe.electromechanicalmaintenancemanagementsystem.testutils.TestData;

public class WorkOrderTaskTest {

    @Test
    public void testCreateTask1() {
        System.out.println("createTask1");
        String[] row = TestData.row("workorder_createTask.csv", 1);
        int timesCreated = Integer.parseInt(row[1]);
        WorkOrder instance = new WorkOrder(1, "Overhaul", "High", "Pending", "2026-10-24");
        int expLastId = Integer.parseInt(row[2]);
        int expCount = Integer.parseInt(row[3]);
        String expDescription = row[4];
        MaintenanceTask last = null;
        for (int i = 0; i < timesCreated; i++) last = instance.createTask();
        assertEquals(expLastId, last.getTaskId());
        assertEquals(expCount, instance.getTasks().size());
        assertEquals(expDescription, last.getTaskDescription());
    }

    @Test
    public void testCreateTask2() {
        System.out.println("createTask2");
        String[] row = TestData.row("workorder_createTask.csv", 2);
        int timesCreated = Integer.parseInt(row[1]);
        WorkOrder instance = new WorkOrder(1, "Overhaul", "High", "Pending", "2026-10-24");
        int expLastId = Integer.parseInt(row[2]);
        int expCount = Integer.parseInt(row[3]);
        String expDescription = row[4];
        MaintenanceTask last = null;
        for (int i = 0; i < timesCreated; i++) last = instance.createTask();
        assertEquals(expLastId, last.getTaskId());
        assertEquals(expCount, instance.getTasks().size());
        assertEquals(expDescription, last.getTaskDescription());
    }

    @Test
    public void testCreateTask3() {
        System.out.println("createTask3");
        String[] row = TestData.row("workorder_createTask.csv", 3);
        int timesCreated = Integer.parseInt(row[1]);
        WorkOrder instance = new WorkOrder(1, "Overhaul", "High", "Pending", "2026-10-24");
        int expLastId = Integer.parseInt(row[2]);
        int expCount = Integer.parseInt(row[3]);
        String expDescription = row[4];
        MaintenanceTask last = null;
        for (int i = 0; i < timesCreated; i++) last = instance.createTask();
        assertEquals(expLastId, last.getTaskId());
        assertEquals(expCount, instance.getTasks().size());
        assertEquals(expDescription, last.getTaskDescription());
    }

    @Test
    public void testCreateTask4() {
        System.out.println("createTask4");
        String[] row = TestData.row("workorder_createTask.csv", 4);
        int timesCreated = Integer.parseInt(row[1]);
        WorkOrder instance = new WorkOrder(1, "Overhaul", "High", "Pending", "2026-10-24");
        int expLastId = Integer.parseInt(row[2]);
        int expCount = Integer.parseInt(row[3]);
        String expDescription = row[4];
        MaintenanceTask last = null;
        for (int i = 0; i < timesCreated; i++) last = instance.createTask();
        assertEquals(expLastId, last.getTaskId());
        assertEquals(expCount, instance.getTasks().size());
        assertEquals(expDescription, last.getTaskDescription());
    }

    @Test
    public void testCreateTask5() {
        System.out.println("createTask5");
        String[] row = TestData.row("workorder_createTask.csv", 5);
        int timesCreated = Integer.parseInt(row[1]);
        WorkOrder instance = new WorkOrder(1, "Overhaul", "High", "Pending", "2026-10-24");
        int expLastId = Integer.parseInt(row[2]);
        int expCount = Integer.parseInt(row[3]);
        String expDescription = row[4];
        MaintenanceTask last = null;
        for (int i = 0; i < timesCreated; i++) last = instance.createTask();
        assertEquals(expLastId, last.getTaskId());
        assertEquals(expCount, instance.getTasks().size());
        assertEquals(expDescription, last.getTaskDescription());
    }

    @Test
    public void testCreateTask6() {
        System.out.println("createTask6");
        String[] row = TestData.row("workorder_createTask.csv", 6);
        int timesCreated = Integer.parseInt(row[1]);
        WorkOrder instance = new WorkOrder(1, "Overhaul", "High", "Pending", "2026-10-24");
        int expLastId = Integer.parseInt(row[2]);
        int expCount = Integer.parseInt(row[3]);
        String expDescription = row[4];
        MaintenanceTask last = null;
        for (int i = 0; i < timesCreated; i++) last = instance.createTask();
        assertEquals(expLastId, last.getTaskId());
        assertEquals(expCount, instance.getTasks().size());
        assertEquals(expDescription, last.getTaskDescription());
    }

    @Test
    public void testCreateTask7() {
        System.out.println("createTask7");
        String[] row = TestData.row("workorder_createTask.csv", 7);
        int timesCreated = Integer.parseInt(row[1]);
        WorkOrder instance = new WorkOrder(1, "Overhaul", "High", "Pending", "2026-10-24");
        int expLastId = Integer.parseInt(row[2]);
        int expCount = Integer.parseInt(row[3]);
        String expDescription = row[4];
        MaintenanceTask last = null;
        for (int i = 0; i < timesCreated; i++) last = instance.createTask();
        assertEquals(expLastId, last.getTaskId());
        assertEquals(expCount, instance.getTasks().size());
        assertEquals(expDescription, last.getTaskDescription());
    }

    @Test
    public void testCreateTask8() {
        System.out.println("createTask8");
        String[] row = TestData.row("workorder_createTask.csv", 8);
        int timesCreated = Integer.parseInt(row[1]);
        WorkOrder instance = new WorkOrder(1, "Overhaul", "High", "Pending", "2026-10-24");
        int expLastId = Integer.parseInt(row[2]);
        int expCount = Integer.parseInt(row[3]);
        String expDescription = row[4];
        MaintenanceTask last = null;
        for (int i = 0; i < timesCreated; i++) last = instance.createTask();
        assertEquals(expLastId, last.getTaskId());
        assertEquals(expCount, instance.getTasks().size());
        assertEquals(expDescription, last.getTaskDescription());
    }

    @Test
    public void testCreateTask9() {
        System.out.println("createTask9");
        String[] row = TestData.row("workorder_createTask.csv", 9);
        int timesCreated = Integer.parseInt(row[1]);
        WorkOrder instance = new WorkOrder(1, "Overhaul", "High", "Pending", "2026-10-24");
        int expLastId = Integer.parseInt(row[2]);
        int expCount = Integer.parseInt(row[3]);
        String expDescription = row[4];
        MaintenanceTask last = null;
        for (int i = 0; i < timesCreated; i++) last = instance.createTask();
        assertEquals(expLastId, last.getTaskId());
        assertEquals(expCount, instance.getTasks().size());
        assertEquals(expDescription, last.getTaskDescription());
    }

    @Test
    public void testCreateTask10() {
        System.out.println("createTask10");
        String[] row = TestData.row("workorder_createTask.csv", 10);
        int timesCreated = Integer.parseInt(row[1]);
        WorkOrder instance = new WorkOrder(1, "Overhaul", "High", "Pending", "2026-10-24");
        int expLastId = Integer.parseInt(row[2]);
        int expCount = Integer.parseInt(row[3]);
        String expDescription = row[4];
        MaintenanceTask last = null;
        for (int i = 0; i < timesCreated; i++) last = instance.createTask();
        assertEquals(expLastId, last.getTaskId());
        assertEquals(expCount, instance.getTasks().size());
        assertEquals(expDescription, last.getTaskDescription());
    }

}
