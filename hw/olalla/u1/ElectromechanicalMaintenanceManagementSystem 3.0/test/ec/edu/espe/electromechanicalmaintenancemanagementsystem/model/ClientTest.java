package ec.edu.espe.electromechanicalmaintenancemanagementsystem.model;

import org.junit.Test;
import static org.junit.Assert.*;
import ec.edu.espe.electromechanicalmaintenancemanagementsystem.testutils.TestData;

public class ClientTest {

    @Test
    public void testAddEquipment1() {
        System.out.println("addEquipment1");
        String[] row = TestData.row("client_addEquipment.csv", 1);
        int timesAdded = Integer.parseInt(row[1]);
        int removeId = Integer.parseInt(row[2]);
        Client instance = new Client(1, "ESPE", "0999999999", "Sangolqui");
        Equipment equipment = new Equipment(1, "Compressor", "SN-001", "Plant A", "Available");
        int expCountAfterAdd = Integer.parseInt(row[3]);
        int expCountAfterRemove = Integer.parseInt(row[4]);
        for (int i = 0; i < timesAdded; i++) instance.addEquipment(equipment);
        assertEquals(expCountAfterAdd, instance.getEquipmentList().length);
        instance.removeEquipment(removeId);
        assertEquals(expCountAfterRemove, instance.getEquipmentList().length);
    }

    @Test
    public void testAddEquipment2() {
        System.out.println("addEquipment2");
        String[] row = TestData.row("client_addEquipment.csv", 2);
        int timesAdded = Integer.parseInt(row[1]);
        int removeId = Integer.parseInt(row[2]);
        Client instance = new Client(1, "ESPE", "0999999999", "Sangolqui");
        Equipment equipment = new Equipment(1, "Compressor", "SN-001", "Plant A", "Available");
        int expCountAfterAdd = Integer.parseInt(row[3]);
        int expCountAfterRemove = Integer.parseInt(row[4]);
        for (int i = 0; i < timesAdded; i++) instance.addEquipment(equipment);
        assertEquals(expCountAfterAdd, instance.getEquipmentList().length);
        instance.removeEquipment(removeId);
        assertEquals(expCountAfterRemove, instance.getEquipmentList().length);
    }

    @Test
    public void testAddEquipment3() {
        System.out.println("addEquipment3");
        String[] row = TestData.row("client_addEquipment.csv", 3);
        int timesAdded = Integer.parseInt(row[1]);
        int removeId = Integer.parseInt(row[2]);
        Client instance = new Client(1, "ESPE", "0999999999", "Sangolqui");
        Equipment equipment = new Equipment(1, "Compressor", "SN-001", "Plant A", "Available");
        int expCountAfterAdd = Integer.parseInt(row[3]);
        int expCountAfterRemove = Integer.parseInt(row[4]);
        for (int i = 0; i < timesAdded; i++) instance.addEquipment(equipment);
        assertEquals(expCountAfterAdd, instance.getEquipmentList().length);
        instance.removeEquipment(removeId);
        assertEquals(expCountAfterRemove, instance.getEquipmentList().length);
    }

    @Test
    public void testAddEquipment4() {
        System.out.println("addEquipment4");
        String[] row = TestData.row("client_addEquipment.csv", 4);
        int timesAdded = Integer.parseInt(row[1]);
        int removeId = Integer.parseInt(row[2]);
        Client instance = new Client(1, "ESPE", "0999999999", "Sangolqui");
        Equipment equipment = new Equipment(1, "Compressor", "SN-001", "Plant A", "Available");
        int expCountAfterAdd = Integer.parseInt(row[3]);
        int expCountAfterRemove = Integer.parseInt(row[4]);
        for (int i = 0; i < timesAdded; i++) instance.addEquipment(equipment);
        assertEquals(expCountAfterAdd, instance.getEquipmentList().length);
        instance.removeEquipment(removeId);
        assertEquals(expCountAfterRemove, instance.getEquipmentList().length);
    }

    @Test
    public void testAddEquipment5() {
        System.out.println("addEquipment5");
        String[] row = TestData.row("client_addEquipment.csv", 5);
        int timesAdded = Integer.parseInt(row[1]);
        int removeId = Integer.parseInt(row[2]);
        Client instance = new Client(1, "ESPE", "0999999999", "Sangolqui");
        Equipment equipment = new Equipment(1, "Compressor", "SN-001", "Plant A", "Available");
        int expCountAfterAdd = Integer.parseInt(row[3]);
        int expCountAfterRemove = Integer.parseInt(row[4]);
        for (int i = 0; i < timesAdded; i++) instance.addEquipment(equipment);
        assertEquals(expCountAfterAdd, instance.getEquipmentList().length);
        instance.removeEquipment(removeId);
        assertEquals(expCountAfterRemove, instance.getEquipmentList().length);
    }

    @Test
    public void testAddEquipment6() {
        System.out.println("addEquipment6");
        String[] row = TestData.row("client_addEquipment.csv", 6);
        int timesAdded = Integer.parseInt(row[1]);
        int removeId = Integer.parseInt(row[2]);
        Client instance = new Client(1, "ESPE", "0999999999", "Sangolqui");
        Equipment equipment = new Equipment(1, "Compressor", "SN-001", "Plant A", "Available");
        int expCountAfterAdd = Integer.parseInt(row[3]);
        int expCountAfterRemove = Integer.parseInt(row[4]);
        for (int i = 0; i < timesAdded; i++) instance.addEquipment(equipment);
        assertEquals(expCountAfterAdd, instance.getEquipmentList().length);
        instance.removeEquipment(removeId);
        assertEquals(expCountAfterRemove, instance.getEquipmentList().length);
    }

    @Test
    public void testAddEquipment7() {
        System.out.println("addEquipment7");
        String[] row = TestData.row("client_addEquipment.csv", 7);
        int timesAdded = Integer.parseInt(row[1]);
        int removeId = Integer.parseInt(row[2]);
        Client instance = new Client(1, "ESPE", "0999999999", "Sangolqui");
        Equipment equipment = new Equipment(1, "Compressor", "SN-001", "Plant A", "Available");
        int expCountAfterAdd = Integer.parseInt(row[3]);
        int expCountAfterRemove = Integer.parseInt(row[4]);
        for (int i = 0; i < timesAdded; i++) instance.addEquipment(equipment);
        assertEquals(expCountAfterAdd, instance.getEquipmentList().length);
        instance.removeEquipment(removeId);
        assertEquals(expCountAfterRemove, instance.getEquipmentList().length);
    }

    @Test
    public void testAddEquipment8() {
        System.out.println("addEquipment8");
        String[] row = TestData.row("client_addEquipment.csv", 8);
        int timesAdded = Integer.parseInt(row[1]);
        int removeId = Integer.parseInt(row[2]);
        Client instance = new Client(1, "ESPE", "0999999999", "Sangolqui");
        Equipment equipment = new Equipment(1, "Compressor", "SN-001", "Plant A", "Available");
        int expCountAfterAdd = Integer.parseInt(row[3]);
        int expCountAfterRemove = Integer.parseInt(row[4]);
        for (int i = 0; i < timesAdded; i++) instance.addEquipment(equipment);
        assertEquals(expCountAfterAdd, instance.getEquipmentList().length);
        instance.removeEquipment(removeId);
        assertEquals(expCountAfterRemove, instance.getEquipmentList().length);
    }

    @Test
    public void testAddEquipment9() {
        System.out.println("addEquipment9");
        String[] row = TestData.row("client_addEquipment.csv", 9);
        int timesAdded = Integer.parseInt(row[1]);
        int removeId = Integer.parseInt(row[2]);
        Client instance = new Client(1, "ESPE", "0999999999", "Sangolqui");
        Equipment equipment = new Equipment(1, "Compressor", "SN-001", "Plant A", "Available");
        int expCountAfterAdd = Integer.parseInt(row[3]);
        int expCountAfterRemove = Integer.parseInt(row[4]);
        for (int i = 0; i < timesAdded; i++) instance.addEquipment(equipment);
        assertEquals(expCountAfterAdd, instance.getEquipmentList().length);
        instance.removeEquipment(removeId);
        assertEquals(expCountAfterRemove, instance.getEquipmentList().length);
    }

    @Test
    public void testAddEquipment10() {
        System.out.println("addEquipment10");
        String[] row = TestData.row("client_addEquipment.csv", 10);
        int timesAdded = Integer.parseInt(row[1]);
        int removeId = Integer.parseInt(row[2]);
        Client instance = new Client(1, "ESPE", "0999999999", "Sangolqui");
        Equipment equipment = new Equipment(1, "Compressor", "SN-001", "Plant A", "Available");
        int expCountAfterAdd = Integer.parseInt(row[3]);
        int expCountAfterRemove = Integer.parseInt(row[4]);
        for (int i = 0; i < timesAdded; i++) instance.addEquipment(equipment);
        assertEquals(expCountAfterAdd, instance.getEquipmentList().length);
        instance.removeEquipment(removeId);
        assertEquals(expCountAfterRemove, instance.getEquipmentList().length);
    }

}
