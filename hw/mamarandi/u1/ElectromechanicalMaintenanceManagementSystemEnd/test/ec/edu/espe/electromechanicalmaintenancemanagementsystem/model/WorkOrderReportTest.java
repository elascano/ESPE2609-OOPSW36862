package ec.edu.espe.electromechanicalmaintenancemanagementsystem.model;

import org.junit.Test;
import static org.junit.Assert.*;
import ec.edu.espe.electromechanicalmaintenancemanagementsystem.testutils.TestData;

public class WorkOrderReportTest {

    @Test
    public void testCreateReport1() {
        System.out.println("createReport1");
        String[] row = TestData.row("workorder_createReport.csv", 1);
        String description = row[1];
        String status = row[2];
        WorkOrder instance = new WorkOrder(1, description, "Low", status, "2026-10-24");
        String expContent = row[3];
        String expType = row[4];
        Report result = instance.createReport();
        assertEquals(expContent, result.getDataContent());
        assertEquals(expType, result.getReportType());
    }

    @Test
    public void testCreateReport2() {
        System.out.println("createReport2");
        String[] row = TestData.row("workorder_createReport.csv", 2);
        String description = row[1];
        String status = row[2];
        WorkOrder instance = new WorkOrder(1, description, "Low", status, "2026-10-24");
        String expContent = row[3];
        String expType = row[4];
        Report result = instance.createReport();
        assertEquals(expContent, result.getDataContent());
        assertEquals(expType, result.getReportType());
    }

    @Test
    public void testCreateReport3() {
        System.out.println("createReport3");
        String[] row = TestData.row("workorder_createReport.csv", 3);
        String description = row[1];
        String status = row[2];
        WorkOrder instance = new WorkOrder(1, description, "Low", status, "2026-10-24");
        String expContent = row[3];
        String expType = row[4];
        Report result = instance.createReport();
        assertEquals(expContent, result.getDataContent());
        assertEquals(expType, result.getReportType());
    }

    @Test
    public void testCreateReport4() {
        System.out.println("createReport4");
        String[] row = TestData.row("workorder_createReport.csv", 4);
        String description = row[1];
        String status = row[2];
        WorkOrder instance = new WorkOrder(1, description, "Low", status, "2026-10-24");
        String expContent = row[3];
        String expType = row[4];
        Report result = instance.createReport();
        assertEquals(expContent, result.getDataContent());
        assertEquals(expType, result.getReportType());
    }

    @Test
    public void testCreateReport5() {
        System.out.println("createReport5");
        String[] row = TestData.row("workorder_createReport.csv", 5);
        String description = row[1];
        String status = row[2];
        WorkOrder instance = new WorkOrder(1, description, "Low", status, "2026-10-24");
        String expContent = row[3];
        String expType = row[4];
        Report result = instance.createReport();
        assertEquals(expContent, result.getDataContent());
        assertEquals(expType, result.getReportType());
    }

    @Test
    public void testCreateReport6() {
        System.out.println("createReport6");
        String[] row = TestData.row("workorder_createReport.csv", 6);
        String description = row[1];
        String status = row[2];
        WorkOrder instance = new WorkOrder(1, description, "Low", status, "2026-10-24");
        String expContent = row[3];
        String expType = row[4];
        Report result = instance.createReport();
        assertEquals(expContent, result.getDataContent());
        assertEquals(expType, result.getReportType());
    }

    @Test
    public void testCreateReport7() {
        System.out.println("createReport7");
        String[] row = TestData.row("workorder_createReport.csv", 7);
        String description = row[1];
        String status = row[2];
        WorkOrder instance = new WorkOrder(1, description, "Low", status, "2026-10-24");
        String expContent = row[3];
        String expType = row[4];
        Report result = instance.createReport();
        assertEquals(expContent, result.getDataContent());
        assertEquals(expType, result.getReportType());
    }

    @Test
    public void testCreateReport8() {
        System.out.println("createReport8");
        String[] row = TestData.row("workorder_createReport.csv", 8);
        String description = row[1];
        String status = row[2];
        WorkOrder instance = new WorkOrder(1, description, "Low", status, "2026-10-24");
        String expContent = row[3];
        String expType = row[4];
        Report result = instance.createReport();
        assertEquals(expContent, result.getDataContent());
        assertEquals(expType, result.getReportType());
    }

    @Test
    public void testCreateReport9() {
        System.out.println("createReport9");
        String[] row = TestData.row("workorder_createReport.csv", 9);
        String description = row[1];
        String status = row[2];
        WorkOrder instance = new WorkOrder(1, description, "Low", status, "2026-10-24");
        String expContent = row[3];
        String expType = row[4];
        Report result = instance.createReport();
        assertEquals(expContent, result.getDataContent());
        assertEquals(expType, result.getReportType());
    }

    @Test
    public void testCreateReport10() {
        System.out.println("createReport10");
        String[] row = TestData.row("workorder_createReport.csv", 10);
        String description = row[1];
        String status = row[2];
        WorkOrder instance = new WorkOrder(1, description, "Low", status, "2026-10-24");
        String expContent = row[3];
        String expType = row[4];
        Report result = instance.createReport();
        assertEquals(expContent, result.getDataContent());
        assertEquals(expType, result.getReportType());
    }

}
