package ec.edu.espe.electromechanicalmaintenancemanagementsystem.model;

import org.junit.Test;
import static org.junit.Assert.*;
import ec.edu.espe.electromechanicalmaintenancemanagementsystem.testutils.TestData;

public class EquipmentTest {

    @Test
    public void testUpdateStatus1() {
        System.out.println("updateStatus1");
        String[] row = TestData.row("equipment_updateStatus.csv", 1);
        Equipment instance = new Equipment(1, "Pump", "SN-001", "Plant A", "Available");
        String newStatus = row[1];
        String expResult = row[2];
        instance.updateStatus(newStatus);
        String result = instance.getStatus();
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateStatus2() {
        System.out.println("updateStatus2");
        String[] row = TestData.row("equipment_updateStatus.csv", 2);
        Equipment instance = new Equipment(1, "Pump", "SN-001", "Plant A", "Available");
        String newStatus = row[1];
        String expResult = row[2];
        instance.updateStatus(newStatus);
        String result = instance.getStatus();
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateStatus3() {
        System.out.println("updateStatus3");
        String[] row = TestData.row("equipment_updateStatus.csv", 3);
        Equipment instance = new Equipment(1, "Pump", "SN-001", "Plant A", "Available");
        String newStatus = row[1];
        String expResult = row[2];
        instance.updateStatus(newStatus);
        String result = instance.getStatus();
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateStatus4() {
        System.out.println("updateStatus4");
        String[] row = TestData.row("equipment_updateStatus.csv", 4);
        Equipment instance = new Equipment(1, "Pump", "SN-001", "Plant A", "Available");
        String newStatus = row[1];
        String expResult = row[2];
        instance.updateStatus(newStatus);
        String result = instance.getStatus();
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateStatus5() {
        System.out.println("updateStatus5");
        String[] row = TestData.row("equipment_updateStatus.csv", 5);
        Equipment instance = new Equipment(1, "Pump", "SN-001", "Plant A", "Available");
        String newStatus = row[1];
        String expResult = row[2];
        instance.updateStatus(newStatus);
        String result = instance.getStatus();
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateStatus6() {
        System.out.println("updateStatus6");
        String[] row = TestData.row("equipment_updateStatus.csv", 6);
        Equipment instance = new Equipment(1, "Pump", "SN-001", "Plant A", "Available");
        String newStatus = row[1];
        String expResult = row[2];
        instance.updateStatus(newStatus);
        String result = instance.getStatus();
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateStatus7() {
        System.out.println("updateStatus7");
        String[] row = TestData.row("equipment_updateStatus.csv", 7);
        Equipment instance = new Equipment(1, "Pump", "SN-001", "Plant A", "Available");
        String newStatus = row[1];
        String expResult = row[2];
        instance.updateStatus(newStatus);
        String result = instance.getStatus();
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateStatus8() {
        System.out.println("updateStatus8");
        String[] row = TestData.row("equipment_updateStatus.csv", 8);
        Equipment instance = new Equipment(1, "Pump", "SN-001", "Plant A", "Available");
        String newStatus = row[1];
        String expResult = row[2];
        instance.updateStatus(newStatus);
        String result = instance.getStatus();
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateStatus9() {
        System.out.println("updateStatus9");
        String[] row = TestData.row("equipment_updateStatus.csv", 9);
        Equipment instance = new Equipment(1, "Pump", "SN-001", "Plant A", "Available");
        String newStatus = row[1];
        String expResult = row[2];
        instance.updateStatus(newStatus);
        String result = instance.getStatus();
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateStatus10() {
        System.out.println("updateStatus10");
        String[] row = TestData.row("equipment_updateStatus.csv", 10);
        Equipment instance = new Equipment(1, "Pump", "SN-001", "Plant A", "Available");
        String newStatus = row[1];
        String expResult = row[2];
        instance.updateStatus(newStatus);
        String result = instance.getStatus();
        assertEquals(expResult, result);
    }

}
