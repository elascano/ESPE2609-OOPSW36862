package ec.edu.espe.electromechanicalmaintenancemanagementsystem.model;

import org.junit.Test;
import static org.junit.Assert.*;
import ec.edu.espe.electromechanicalmaintenancemanagementsystem.testutils.TestData;

public class TechnicianTest {

    @Test
    public void testAcceptWorkOrder1() {
        System.out.println("acceptWorkOrder1");
        String[] row = TestData.row("technician_acceptWorkOrder.csv", 1);
        boolean available = Boolean.parseBoolean(row[1]);
        Technician instance = new Technician(1, "Carlos", "Electrical", available);
        WorkOrder order = new WorkOrder(1, "Replace motor", "High", "Pending", "2026-10-24");
        boolean expAvailableAfter = Boolean.parseBoolean(row[2]);
        boolean expAssigned = Boolean.parseBoolean(row[3]);
        instance.acceptWorkOrder(order);
        assertEquals(expAvailableAfter, instance.getAvailabilityStatus());
        assertEquals(expAssigned, order.getTechnician() != null);
    }

    @Test
    public void testAcceptWorkOrder2() {
        System.out.println("acceptWorkOrder2");
        String[] row = TestData.row("technician_acceptWorkOrder.csv", 2);
        boolean available = Boolean.parseBoolean(row[1]);
        Technician instance = new Technician(1, "Carlos", "Electrical", available);
        WorkOrder order = new WorkOrder(1, "Replace motor", "High", "Pending", "2026-10-24");
        boolean expAvailableAfter = Boolean.parseBoolean(row[2]);
        boolean expAssigned = Boolean.parseBoolean(row[3]);
        instance.acceptWorkOrder(order);
        assertEquals(expAvailableAfter, instance.getAvailabilityStatus());
        assertEquals(expAssigned, order.getTechnician() != null);
    }

    @Test
    public void testAcceptWorkOrder3() {
        System.out.println("acceptWorkOrder3");
        String[] row = TestData.row("technician_acceptWorkOrder.csv", 3);
        boolean available = Boolean.parseBoolean(row[1]);
        Technician instance = new Technician(1, "Carlos", "Electrical", available);
        WorkOrder order = new WorkOrder(1, "Replace motor", "High", "Pending", "2026-10-24");
        boolean expAvailableAfter = Boolean.parseBoolean(row[2]);
        boolean expAssigned = Boolean.parseBoolean(row[3]);
        instance.acceptWorkOrder(order);
        assertEquals(expAvailableAfter, instance.getAvailabilityStatus());
        assertEquals(expAssigned, order.getTechnician() != null);
    }

    @Test
    public void testAcceptWorkOrder4() {
        System.out.println("acceptWorkOrder4");
        String[] row = TestData.row("technician_acceptWorkOrder.csv", 4);
        boolean available = Boolean.parseBoolean(row[1]);
        Technician instance = new Technician(1, "Carlos", "Electrical", available);
        WorkOrder order = new WorkOrder(1, "Replace motor", "High", "Pending", "2026-10-24");
        boolean expAvailableAfter = Boolean.parseBoolean(row[2]);
        boolean expAssigned = Boolean.parseBoolean(row[3]);
        instance.acceptWorkOrder(order);
        assertEquals(expAvailableAfter, instance.getAvailabilityStatus());
        assertEquals(expAssigned, order.getTechnician() != null);
    }

    @Test
    public void testAcceptWorkOrder5() {
        System.out.println("acceptWorkOrder5");
        String[] row = TestData.row("technician_acceptWorkOrder.csv", 5);
        boolean available = Boolean.parseBoolean(row[1]);
        Technician instance = new Technician(1, "Carlos", "Electrical", available);
        WorkOrder order = new WorkOrder(1, "Replace motor", "High", "Pending", "2026-10-24");
        boolean expAvailableAfter = Boolean.parseBoolean(row[2]);
        boolean expAssigned = Boolean.parseBoolean(row[3]);
        instance.acceptWorkOrder(order);
        assertEquals(expAvailableAfter, instance.getAvailabilityStatus());
        assertEquals(expAssigned, order.getTechnician() != null);
    }

    @Test
    public void testAcceptWorkOrder6() {
        System.out.println("acceptWorkOrder6");
        String[] row = TestData.row("technician_acceptWorkOrder.csv", 6);
        boolean available = Boolean.parseBoolean(row[1]);
        Technician instance = new Technician(1, "Carlos", "Electrical", available);
        WorkOrder order = new WorkOrder(1, "Replace motor", "High", "Pending", "2026-10-24");
        boolean expAvailableAfter = Boolean.parseBoolean(row[2]);
        boolean expAssigned = Boolean.parseBoolean(row[3]);
        instance.acceptWorkOrder(order);
        assertEquals(expAvailableAfter, instance.getAvailabilityStatus());
        assertEquals(expAssigned, order.getTechnician() != null);
    }

    @Test
    public void testAcceptWorkOrder7() {
        System.out.println("acceptWorkOrder7");
        String[] row = TestData.row("technician_acceptWorkOrder.csv", 7);
        boolean available = Boolean.parseBoolean(row[1]);
        Technician instance = new Technician(1, "Carlos", "Electrical", available);
        WorkOrder order = new WorkOrder(1, "Replace motor", "High", "Pending", "2026-10-24");
        boolean expAvailableAfter = Boolean.parseBoolean(row[2]);
        boolean expAssigned = Boolean.parseBoolean(row[3]);
        instance.acceptWorkOrder(order);
        assertEquals(expAvailableAfter, instance.getAvailabilityStatus());
        assertEquals(expAssigned, order.getTechnician() != null);
    }

    @Test
    public void testAcceptWorkOrder8() {
        System.out.println("acceptWorkOrder8");
        String[] row = TestData.row("technician_acceptWorkOrder.csv", 8);
        boolean available = Boolean.parseBoolean(row[1]);
        Technician instance = new Technician(1, "Carlos", "Electrical", available);
        WorkOrder order = new WorkOrder(1, "Replace motor", "High", "Pending", "2026-10-24");
        boolean expAvailableAfter = Boolean.parseBoolean(row[2]);
        boolean expAssigned = Boolean.parseBoolean(row[3]);
        instance.acceptWorkOrder(order);
        assertEquals(expAvailableAfter, instance.getAvailabilityStatus());
        assertEquals(expAssigned, order.getTechnician() != null);
    }

    @Test
    public void testAcceptWorkOrder9() {
        System.out.println("acceptWorkOrder9");
        String[] row = TestData.row("technician_acceptWorkOrder.csv", 9);
        boolean available = Boolean.parseBoolean(row[1]);
        Technician instance = new Technician(1, "Carlos", "Electrical", available);
        WorkOrder order = new WorkOrder(1, "Replace motor", "High", "Pending", "2026-10-24");
        boolean expAvailableAfter = Boolean.parseBoolean(row[2]);
        boolean expAssigned = Boolean.parseBoolean(row[3]);
        instance.acceptWorkOrder(order);
        assertEquals(expAvailableAfter, instance.getAvailabilityStatus());
        assertEquals(expAssigned, order.getTechnician() != null);
    }

    @Test
    public void testAcceptWorkOrder10() {
        System.out.println("acceptWorkOrder10");
        String[] row = TestData.row("technician_acceptWorkOrder.csv", 10);
        boolean available = Boolean.parseBoolean(row[1]);
        Technician instance = new Technician(1, "Carlos", "Electrical", available);
        WorkOrder order = new WorkOrder(1, "Replace motor", "High", "Pending", "2026-10-24");
        boolean expAvailableAfter = Boolean.parseBoolean(row[2]);
        boolean expAssigned = Boolean.parseBoolean(row[3]);
        instance.acceptWorkOrder(order);
        assertEquals(expAvailableAfter, instance.getAvailabilityStatus());
        assertEquals(expAssigned, order.getTechnician() != null);
    }

}
