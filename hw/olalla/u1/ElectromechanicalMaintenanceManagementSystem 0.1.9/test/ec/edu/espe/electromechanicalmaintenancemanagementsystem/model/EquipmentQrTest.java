package ec.edu.espe.electromechanicalmaintenancemanagementsystem.model;

import org.junit.Test;
import static org.junit.Assert.*;
import ec.edu.espe.electromechanicalmaintenancemanagementsystem.testutils.TestData;

public class EquipmentQrTest {

    @Test
    public void testCreateQrCode1() {
        System.out.println("createQrCode1");
        String[] row = TestData.row("equipment_createQrCode.csv", 1);
        int equipmentId = Integer.parseInt(row[1]);
        String serialNumber = row[2];
        Equipment instance = new Equipment(equipmentId, "Motor", serialNumber, "Plant B", "Available");
        String expResult = row[3];
        String result = instance.createQrCode().getGenerateUrl();
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateQrCode2() {
        System.out.println("createQrCode2");
        String[] row = TestData.row("equipment_createQrCode.csv", 2);
        int equipmentId = Integer.parseInt(row[1]);
        String serialNumber = row[2];
        Equipment instance = new Equipment(equipmentId, "Motor", serialNumber, "Plant B", "Available");
        String expResult = row[3];
        String result = instance.createQrCode().getGenerateUrl();
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateQrCode3() {
        System.out.println("createQrCode3");
        String[] row = TestData.row("equipment_createQrCode.csv", 3);
        int equipmentId = Integer.parseInt(row[1]);
        String serialNumber = row[2];
        Equipment instance = new Equipment(equipmentId, "Motor", serialNumber, "Plant B", "Available");
        String expResult = row[3];
        String result = instance.createQrCode().getGenerateUrl();
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateQrCode4() {
        System.out.println("createQrCode4");
        String[] row = TestData.row("equipment_createQrCode.csv", 4);
        int equipmentId = Integer.parseInt(row[1]);
        String serialNumber = row[2];
        Equipment instance = new Equipment(equipmentId, "Motor", serialNumber, "Plant B", "Available");
        String expResult = row[3];
        String result = instance.createQrCode().getGenerateUrl();
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateQrCode5() {
        System.out.println("createQrCode5");
        String[] row = TestData.row("equipment_createQrCode.csv", 5);
        int equipmentId = Integer.parseInt(row[1]);
        String serialNumber = row[2];
        Equipment instance = new Equipment(equipmentId, "Motor", serialNumber, "Plant B", "Available");
        String expResult = row[3];
        String result = instance.createQrCode().getGenerateUrl();
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateQrCode6() {
        System.out.println("createQrCode6");
        String[] row = TestData.row("equipment_createQrCode.csv", 6);
        int equipmentId = Integer.parseInt(row[1]);
        String serialNumber = row[2];
        Equipment instance = new Equipment(equipmentId, "Motor", serialNumber, "Plant B", "Available");
        String expResult = row[3];
        String result = instance.createQrCode().getGenerateUrl();
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateQrCode7() {
        System.out.println("createQrCode7");
        String[] row = TestData.row("equipment_createQrCode.csv", 7);
        int equipmentId = Integer.parseInt(row[1]);
        String serialNumber = row[2];
        Equipment instance = new Equipment(equipmentId, "Motor", serialNumber, "Plant B", "Available");
        String expResult = row[3];
        String result = instance.createQrCode().getGenerateUrl();
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateQrCode8() {
        System.out.println("createQrCode8");
        String[] row = TestData.row("equipment_createQrCode.csv", 8);
        int equipmentId = Integer.parseInt(row[1]);
        String serialNumber = row[2];
        Equipment instance = new Equipment(equipmentId, "Motor", serialNumber, "Plant B", "Available");
        String expResult = row[3];
        String result = instance.createQrCode().getGenerateUrl();
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateQrCode9() {
        System.out.println("createQrCode9");
        String[] row = TestData.row("equipment_createQrCode.csv", 9);
        int equipmentId = Integer.parseInt(row[1]);
        String serialNumber = row[2];
        Equipment instance = new Equipment(equipmentId, "Motor", serialNumber, "Plant B", "Available");
        String expResult = row[3];
        String result = instance.createQrCode().getGenerateUrl();
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateQrCode10() {
        System.out.println("createQrCode10");
        String[] row = TestData.row("equipment_createQrCode.csv", 10);
        int equipmentId = Integer.parseInt(row[1]);
        String serialNumber = row[2];
        Equipment instance = new Equipment(equipmentId, "Motor", serialNumber, "Plant B", "Available");
        String expResult = row[3];
        String result = instance.createQrCode().getGenerateUrl();
        assertEquals(expResult, result);
    }

}
