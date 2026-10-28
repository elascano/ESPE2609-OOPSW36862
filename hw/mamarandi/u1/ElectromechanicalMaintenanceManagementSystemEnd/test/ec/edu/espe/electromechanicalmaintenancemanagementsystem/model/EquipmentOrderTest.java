package ec.edu.espe.electromechanicalmaintenancemanagementsystem.model;

import org.junit.Test;
import static org.junit.Assert.*;
import ec.edu.espe.electromechanicalmaintenancemanagementsystem.testutils.TestData;

public class EquipmentOrderTest {

    @Test
    public void testRequestMaintenanceOrder1() {
        System.out.println("requestMaintenanceOrder1");
        String[] row = TestData.row("equipment_requestMaintenanceOrder.csv", 1);
        String name = row[1];
        int timesRequested = Integer.parseInt(row[2]);
        Equipment instance = new Equipment(1, name, "SN-001", "Plant A", "Available");
        int expLastId = Integer.parseInt(row[3]);
        String expDescription = row[4];
        String expStatus = row[5];
        WorkOrder result = null;
        for (int i = 0; i < timesRequested; i++) result = instance.requestMaintenanceOrder();
        assertEquals(expLastId, result.getWorkOrderId());
        assertEquals(expDescription, result.getDescription());
        assertEquals(expStatus, result.getStatus());
    }

    @Test
    public void testRequestMaintenanceOrder2() {
        System.out.println("requestMaintenanceOrder2");
        String[] row = TestData.row("equipment_requestMaintenanceOrder.csv", 2);
        String name = row[1];
        int timesRequested = Integer.parseInt(row[2]);
        Equipment instance = new Equipment(1, name, "SN-001", "Plant A", "Available");
        int expLastId = Integer.parseInt(row[3]);
        String expDescription = row[4];
        String expStatus = row[5];
        WorkOrder result = null;
        for (int i = 0; i < timesRequested; i++) result = instance.requestMaintenanceOrder();
        assertEquals(expLastId, result.getWorkOrderId());
        assertEquals(expDescription, result.getDescription());
        assertEquals(expStatus, result.getStatus());
    }

    @Test
    public void testRequestMaintenanceOrder3() {
        System.out.println("requestMaintenanceOrder3");
        String[] row = TestData.row("equipment_requestMaintenanceOrder.csv", 3);
        String name = row[1];
        int timesRequested = Integer.parseInt(row[2]);
        Equipment instance = new Equipment(1, name, "SN-001", "Plant A", "Available");
        int expLastId = Integer.parseInt(row[3]);
        String expDescription = row[4];
        String expStatus = row[5];
        WorkOrder result = null;
        for (int i = 0; i < timesRequested; i++) result = instance.requestMaintenanceOrder();
        assertEquals(expLastId, result.getWorkOrderId());
        assertEquals(expDescription, result.getDescription());
        assertEquals(expStatus, result.getStatus());
    }

    @Test
    public void testRequestMaintenanceOrder4() {
        System.out.println("requestMaintenanceOrder4");
        String[] row = TestData.row("equipment_requestMaintenanceOrder.csv", 4);
        String name = row[1];
        int timesRequested = Integer.parseInt(row[2]);
        Equipment instance = new Equipment(1, name, "SN-001", "Plant A", "Available");
        int expLastId = Integer.parseInt(row[3]);
        String expDescription = row[4];
        String expStatus = row[5];
        WorkOrder result = null;
        for (int i = 0; i < timesRequested; i++) result = instance.requestMaintenanceOrder();
        assertEquals(expLastId, result.getWorkOrderId());
        assertEquals(expDescription, result.getDescription());
        assertEquals(expStatus, result.getStatus());
    }

    @Test
    public void testRequestMaintenanceOrder5() {
        System.out.println("requestMaintenanceOrder5");
        String[] row = TestData.row("equipment_requestMaintenanceOrder.csv", 5);
        String name = row[1];
        int timesRequested = Integer.parseInt(row[2]);
        Equipment instance = new Equipment(1, name, "SN-001", "Plant A", "Available");
        int expLastId = Integer.parseInt(row[3]);
        String expDescription = row[4];
        String expStatus = row[5];
        WorkOrder result = null;
        for (int i = 0; i < timesRequested; i++) result = instance.requestMaintenanceOrder();
        assertEquals(expLastId, result.getWorkOrderId());
        assertEquals(expDescription, result.getDescription());
        assertEquals(expStatus, result.getStatus());
    }

    @Test
    public void testRequestMaintenanceOrder6() {
        System.out.println("requestMaintenanceOrder6");
        String[] row = TestData.row("equipment_requestMaintenanceOrder.csv", 6);
        String name = row[1];
        int timesRequested = Integer.parseInt(row[2]);
        Equipment instance = new Equipment(1, name, "SN-001", "Plant A", "Available");
        int expLastId = Integer.parseInt(row[3]);
        String expDescription = row[4];
        String expStatus = row[5];
        WorkOrder result = null;
        for (int i = 0; i < timesRequested; i++) result = instance.requestMaintenanceOrder();
        assertEquals(expLastId, result.getWorkOrderId());
        assertEquals(expDescription, result.getDescription());
        assertEquals(expStatus, result.getStatus());
    }

    @Test
    public void testRequestMaintenanceOrder7() {
        System.out.println("requestMaintenanceOrder7");
        String[] row = TestData.row("equipment_requestMaintenanceOrder.csv", 7);
        String name = row[1];
        int timesRequested = Integer.parseInt(row[2]);
        Equipment instance = new Equipment(1, name, "SN-001", "Plant A", "Available");
        int expLastId = Integer.parseInt(row[3]);
        String expDescription = row[4];
        String expStatus = row[5];
        WorkOrder result = null;
        for (int i = 0; i < timesRequested; i++) result = instance.requestMaintenanceOrder();
        assertEquals(expLastId, result.getWorkOrderId());
        assertEquals(expDescription, result.getDescription());
        assertEquals(expStatus, result.getStatus());
    }

    @Test
    public void testRequestMaintenanceOrder8() {
        System.out.println("requestMaintenanceOrder8");
        String[] row = TestData.row("equipment_requestMaintenanceOrder.csv", 8);
        String name = row[1];
        int timesRequested = Integer.parseInt(row[2]);
        Equipment instance = new Equipment(1, name, "SN-001", "Plant A", "Available");
        int expLastId = Integer.parseInt(row[3]);
        String expDescription = row[4];
        String expStatus = row[5];
        WorkOrder result = null;
        for (int i = 0; i < timesRequested; i++) result = instance.requestMaintenanceOrder();
        assertEquals(expLastId, result.getWorkOrderId());
        assertEquals(expDescription, result.getDescription());
        assertEquals(expStatus, result.getStatus());
    }

    @Test
    public void testRequestMaintenanceOrder9() {
        System.out.println("requestMaintenanceOrder9");
        String[] row = TestData.row("equipment_requestMaintenanceOrder.csv", 9);
        String name = row[1];
        int timesRequested = Integer.parseInt(row[2]);
        Equipment instance = new Equipment(1, name, "SN-001", "Plant A", "Available");
        int expLastId = Integer.parseInt(row[3]);
        String expDescription = row[4];
        String expStatus = row[5];
        WorkOrder result = null;
        for (int i = 0; i < timesRequested; i++) result = instance.requestMaintenanceOrder();
        assertEquals(expLastId, result.getWorkOrderId());
        assertEquals(expDescription, result.getDescription());
        assertEquals(expStatus, result.getStatus());
    }

    @Test
    public void testRequestMaintenanceOrder10() {
        System.out.println("requestMaintenanceOrder10");
        String[] row = TestData.row("equipment_requestMaintenanceOrder.csv", 10);
        String name = row[1];
        int timesRequested = Integer.parseInt(row[2]);
        Equipment instance = new Equipment(1, name, "SN-001", "Plant A", "Available");
        int expLastId = Integer.parseInt(row[3]);
        String expDescription = row[4];
        String expStatus = row[5];
        WorkOrder result = null;
        for (int i = 0; i < timesRequested; i++) result = instance.requestMaintenanceOrder();
        assertEquals(expLastId, result.getWorkOrderId());
        assertEquals(expDescription, result.getDescription());
        assertEquals(expStatus, result.getStatus());
    }

}
